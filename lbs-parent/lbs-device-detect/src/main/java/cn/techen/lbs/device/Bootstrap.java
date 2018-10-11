package cn.techen.lbs.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.device.common.DeviceContext;
import cn.techen.lbs.device.common.Local;
import cn.techen.lbs.device.manager.AbstractHandler;
import cn.techen.lbs.device.manager.ObtainHandler;
import cn.techen.lbs.device.manager.ReadHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private DeviceContext context;
	private AbstractHandler obtain;	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		logger.info("LBS Device Detect Module is starting......");	
		Thread detect = new Thread(new DetectThread());
		detect.start();
		
		logger.info("LBS Device Reset Module is starting......");
		Thread reset = new Thread(new ResetThread());
		reset.start();
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
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
	}
	
	protected class ResetThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					obtain.operate(context);
					read.operate(context);
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
	}
}
