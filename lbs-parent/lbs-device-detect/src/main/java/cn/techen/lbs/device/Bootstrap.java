package cn.techen.lbs.device;

import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.device.common.DeviceContext;
import cn.techen.lbs.device.common.Local;
import cn.techen.lbs.device.manager.AbstractHandler;
import cn.techen.lbs.device.manager.ObtainHandler;
import cn.techen.lbs.device.manager.ReadHandler;
import cn.techen.lbs.global.Global;

public class Bootstrap {
	private DeviceContext context;
	private AbstractHandler obtain;	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		Thread detect = new Thread(new DetectThread());
		detect.start();
		
		Thread device = new Thread(new DeviceThread());
		device.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		read = new ReadHandler();
	}

	public void setContext(DeviceContext context) {
		this.context = context;
	}

	protected class DetectThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.DETECTMILLIS);
										
					if (Global.DBReady) {
						int fn = 0;
						LBS oLbs = context.getLbs();
						LBS lbs = context.getmLbsService().get();
						if (oLbs == null) {
							fn = 4;
						} else {
							if (!lbs.getModuleaddr().equals(oLbs.getModuleaddr()) && 
									lbs.getLogicaddr().equals(oLbs.getLogicaddr()) && lbs.getChannel().equals(oLbs.getChannel())) {
								fn = 1;
							} else if (!lbs.getLogicaddr().equals(oLbs.getLogicaddr()) && 
									lbs.getModuleaddr().equals(oLbs.getModuleaddr()) && lbs.getChannel().equals(oLbs.getChannel())) {
								fn = 2;
							} else if (!lbs.getChannel().equals(oLbs.getChannel()) && 
									lbs.getModuleaddr().equals(oLbs.getModuleaddr()) && lbs.getLogicaddr().equals(oLbs.getLogicaddr())) {
								fn = 3;
							} else {
								fn = 4;
							}								
						}
						if (fn > 0) {
							Global.LoraReady = false;
							context.setLbs(lbs);
							context.fireEncode(fn);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
	
	protected class DeviceThread implements Runnable {

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
