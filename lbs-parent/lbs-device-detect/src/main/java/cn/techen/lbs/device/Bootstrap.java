package cn.techen.lbs.device;

import cn.techen.lbs.db.common.Global;
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
										
					if (Global.ChannelReady) {
						if (!Global.LoraReady) {
							context.fireEncode();
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
