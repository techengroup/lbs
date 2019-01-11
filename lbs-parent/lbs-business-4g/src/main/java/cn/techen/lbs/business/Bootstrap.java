package cn.techen.lbs.business;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.business.common.Local;
import cn.techen.lbs.business.manager.BusinessProcesser;
import cn.techen.lbs.db.common.Global;

public class Bootstrap {	
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private BusinessContext context;
	private final BusinessProcesser businessProcesser = new BusinessProcesser();
	private final ScheduledExecutorService singleSchedule = Executors.newSingleThreadScheduledExecutor();
	
	public void start() {		
		logger.info("4G Business processer deamon is starting......");	
		singleSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {
					businessProcesser.operate(context);
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}
			}
		}, Local.INTERVALMILLIS, Local.INTERVALMILLIS, TimeUnit.MILLISECONDS);
	}

	public void setContext(BusinessContext context) {
		this.context = context;
	}
	
}
