package cn.techen.lbs.task.month;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.task.month.common.Local;
import cn.techen.lbs.task.month.common.MonthContext;
import cn.techen.lbs.task.month.manager.AbstractHandler;
import cn.techen.lbs.task.month.manager.ObtainHandler;
import cn.techen.lbs.task.month.manager.ReadHandler;

public class Bootstrap {
		
	private MonthContext context;
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		Thread load = new Thread(new LoadThread());
		load.start();
		
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
					
					if (Global.DATAReady) {
						if (!GlobalUtil.isFirstDayOfMonth() && !GlobalUtil.isLastDayOfMonth()) {
							if (context.months().isEmpty()) {
								context.load();
							}
						} else {
							context.months().clear();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
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
					
					if (Global.DATAReady) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					e.printStackTrace();
					context.reset();
				}				
			}
		}		
	}
	
}
