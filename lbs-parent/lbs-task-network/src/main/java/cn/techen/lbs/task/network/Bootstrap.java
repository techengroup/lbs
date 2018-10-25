package cn.techen.lbs.task.network;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.mm.api.MNodeService;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.network.common.Local;
import cn.techen.lbs.task.network.common.NetContext;
import cn.techen.lbs.task.network.manager.AbstractHandler;
import cn.techen.lbs.task.network.manager.ObtainHandler;
import cn.techen.lbs.task.network.manager.ReadHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
		
	private NetContext context;
	private AbstractHandler obtain = new ObtainHandler();	
	private AbstractHandler read = new ReadHandler();
	private final ScheduledExecutorService loadSchedule = Executors.newSingleThreadScheduledExecutor();
	private final ScheduledExecutorService netSchedule = Executors.newSingleThreadScheduledExecutor();

	public void start() {		
		logger.info("Load unregister node is starting......");
		loadSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {					
					if (context.getState() == State.FINISHED) {
						Long size = context.getmNodeService().size(MNodeService.DB_NODE_UNREGISTER);
						if (size == null || size <= 0) {
							List<Node> nodes = context.getNodeService().selectUnregister();
							if (nodes == null || nodes.size() <= 0) {
								logger.info("Loaded unregister node[0]...");
							} else {
								context.getmNodeService().lpush(nodes);
								logger.info("Loaded unregister node[{}]...", nodes.size());
							}
						}
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}
			}
		}, Local.LOADMILLIS, Local.LOADMILLIS, TimeUnit.MILLISECONDS);
		
		logger.info("Network deamon is starting......");		
		netSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {					
					if (Global.LoraReady) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					context.reset();
					logger.error(Global.getStackTrace(e));
				}	
			}
		}, Local.INTERVALMILLIS, Local.INTERVALMILLIS, TimeUnit.MILLISECONDS);
	}

	public void setContext(NetContext context) {
		this.context = context;
	}
	
}
