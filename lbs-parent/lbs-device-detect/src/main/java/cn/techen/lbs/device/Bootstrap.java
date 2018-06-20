package cn.techen.lbs.device;

import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.device.common.DeviceContext;
import cn.techen.lbs.device.common.Local;
import cn.techen.lbs.device.manager.AbstractHandler;
import cn.techen.lbs.device.manager.ObtainHandler;
import cn.techen.lbs.device.manager.ReadHandler;

public class Bootstrap {
	private DeviceContext context;
	private AbstractHandler obtain;	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		Thread init = new Thread(new InitThread());
		init.start();
		
		Thread event = new Thread(new EventThread());
		event.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		read = new ReadHandler();
	}

	public void setContext(DeviceContext context) {
		this.context = context;
	}

	protected class InitThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.DETECTMILLIS);
					
					if (context.isLoad()) {
						LBS oLbs = context.getLbs();
						LBS lbs = context.getmLbsService().get();
						if (oLbs == null || !lbs.getModuleaddr().equals(oLbs.getModuleaddr()) ||
								!lbs.getLogicaddr().equals(oLbs.getLogicaddr()) || 
								lbs.getChannel() != oLbs.getChannel()) {
							context.setLbs(lbs);
							context.fireEncode();
						}
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
	
	protected class EventThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					obtain.operate(context);
					read.operate(context);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
}
