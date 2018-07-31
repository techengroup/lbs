package cn.techen.lbs.task.month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.task.month.common.Local;
import cn.techen.lbs.task.month.common.MonthContext;
import cn.techen.lbs.task.month.manager.AbstractHandler;
import cn.techen.lbs.task.month.manager.ObtainHandler;
import cn.techen.lbs.task.month.manager.ReadHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
		
	private MonthContext context;
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		logger.info("LBS Month Obtain Module is starting......");
		Thread load = new Thread(new LoadThread());
		load.start();
		
		logger.info("LBS Month Read Module is starting......");
		Thread month = new Thread(new MonthThread());
		month.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		read = new ReadHandler();
	}

	public void setContext(MonthContext context) {
		this.context = context;
	}
	
	protected class LoadThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.MONTHMILLIS);
					
					if (Global.GISReady) {
						if (context.months().isEmpty()) {
							context.load();
						}
					}
				} catch (Exception e) {
					logger.error(GlobalUtil.getStackTrace(e));
				}				
			}
		}		
	}

	protected class MonthThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.GISReady) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					logger.error(GlobalUtil.getStackTrace(e));
					context.reset();
				}				
			}
		}		
	}
	
}
