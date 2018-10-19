package cn.techen.lbs.task.report;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.task.report.common.Local;
import cn.techen.lbs.task.report.common.ReportContext;
import cn.techen.lbs.task.report.manager.AbstractHandler;
import cn.techen.lbs.task.report.manager.ReportHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private ReportContext context;	
	private final AbstractHandler report = new ReportHandler();	
	private final ScheduledExecutorService singleSchedule = Executors.newSingleThreadScheduledExecutor();

	public void start() {		
		logger.info("Event report deamon is starting......");
		singleSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {					
					report.operate(context);
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}
			}
		}, Local.INTERVALMILLIS, Local.INTERVALMILLIS, TimeUnit.MILLISECONDS);
	}

	public void setContext(ReportContext context) {
		this.context = context;
	}
	
}
