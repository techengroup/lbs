package cn.techen.lbs.task.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.task.report.common.Local;
import cn.techen.lbs.task.report.common.ReportContext;
import cn.techen.lbs.task.report.manager.AbstractHandler;
import cn.techen.lbs.task.report.manager.ReportHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private ReportContext context;
	
	private AbstractHandler report;

	public void start() {
		report = new ReportHandler();
		
		logger.info("LBS Event Report Module is starting......");
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
					logger.error(GlobalUtil.getStackTrace(e));
				}				
			}
		}		
	}
	
}
