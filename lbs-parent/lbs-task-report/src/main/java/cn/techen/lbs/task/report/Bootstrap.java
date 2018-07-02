package cn.techen.lbs.task.report;

import cn.techen.lbs.task.report.common.Local;
import cn.techen.lbs.task.report.common.ReportContext;
import cn.techen.lbs.task.report.manager.AbstractHandler;
import cn.techen.lbs.task.report.manager.ReportHandler;

public class Bootstrap {
	
	private ReportContext context;
	
	private AbstractHandler report;

	public void start() {
		report = new ReportHandler();
		
		Thread reportT = new Thread(new ReportThread());
		reportT.start();
	}

	public void setContext(ReportContext context) {
		this.context = context;
	}

	protected class ReportThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					report.operate(context);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
	
}
