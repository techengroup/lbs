package cn.techen.lbs.business;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.business.common.Local;
import cn.techen.lbs.business.manager.BusinessProcesser;

public class Bootstrap {	
	
	private BusinessContext context;
	private BusinessProcesser businessProcesser;
	
	public void start() {
		businessProcesser = new BusinessProcesser();
		
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
					e.printStackTrace();
				}
				
			}
		}
	}
	
}
