package cn.techen.lbs.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.business.common.Local;
import cn.techen.lbs.business.manager.BusinessProcesser;
import cn.techen.lbs.db.common.GlobalUtil;

public class Bootstrap {	
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private BusinessContext context;
	private BusinessProcesser businessProcesser;
	
	public void start() {
		businessProcesser = new BusinessProcesser();
		
		logger.info("Business Processer Module is starting......");	
		Thread business = new Thread(new BusinessThread());
		business.start();
	}

	public void setContext(BusinessContext context) {
		this.context = context;
	}
	
	protected class BusinessThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					businessProcesser.operate(context);
				} catch (Exception e) {
					logger.error(GlobalUtil.getStackTrace(e));
				}
				
			}
		}
	}
	
}
