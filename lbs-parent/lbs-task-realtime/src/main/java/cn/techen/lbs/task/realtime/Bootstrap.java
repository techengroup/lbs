package cn.techen.lbs.task.realtime;

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
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		logger.info("LBS transfer task is starting......");
		Thread transfer = new Thread(new TransferThread());
		transfer.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		read = new ReadHandler();
	}

	public void setContext(RealTimeContext context) {
		this.context = context;
	}

	protected class TransferThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.GISReady && Global.ChannelReady) {
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
