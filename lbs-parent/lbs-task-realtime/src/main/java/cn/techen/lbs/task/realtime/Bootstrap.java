package cn.techen.lbs.task.realtime;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.task.realtime.common.Local;
import cn.techen.lbs.task.realtime.common.RealTimeContext;
import cn.techen.lbs.task.realtime.manager.AbstractHandler;
import cn.techen.lbs.task.realtime.manager.ObtainHandler;
import cn.techen.lbs.task.realtime.manager.ReadHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
		
	private RealTimeContext context;	
	private final AbstractHandler obtain = new ObtainHandler();	
	private final AbstractHandler read = new ReadHandler();
	private final ScheduledExecutorService singleSchedule = Executors.newSingleThreadScheduledExecutor();

	public void start() {		
		logger.info("Realtime transfer deamon is starting......");
		singleSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {					
					if (Global.ChannelReady && Global.LoraReady) {
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

	public void setContext(RealTimeContext context) {
		this.context = context;
	}
	
}
