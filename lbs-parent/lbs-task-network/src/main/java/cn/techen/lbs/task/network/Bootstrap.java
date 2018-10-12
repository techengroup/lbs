package cn.techen.lbs.task.network;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.network.common.Local;
import cn.techen.lbs.task.network.common.NetContext;
import cn.techen.lbs.task.network.manager.AbstractHandler;
import cn.techen.lbs.task.network.manager.ObtainHandler;
import cn.techen.lbs.task.network.manager.ReadHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
		
	private NetContext context;

	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		logger.info("Load unregister node is starting......");
		Thread load = new Thread(new LoadThread());
		load.start();		
		
		logger.info("LBS network task is starting......");
		Thread net = new Thread(new NetThread());
		net.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		read = new ReadHandler();
	}

	public void setContext(NetContext context) {
		this.context = context;
	}
	
	protected class LoadThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.LOADMILLIS);
					
					if (context.getState() == State.FINISHED) {
						Long size = context.getmNodeService().size();
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
		}		
	}

	protected class NetThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.LoraReady) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
					context.reset();
				}				
			}
		}		
	}
	
}
