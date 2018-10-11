package cn.techen.lbs.lora;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.channel.rxtx.RxtxDeviceAddress;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.lora.common.LoraRxtx;
import cn.techen.lbs.lora.mananger.AbstractHandler;
import cn.techen.lbs.lora.mananger.ObtainHandler;
import cn.techen.lbs.lora.mananger.ReadHandler;
import cn.techen.lbs.lora.mananger.WriteHandler;
import cn.techen.lbs.lora.mananger.StoreHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private LoraContext context;
	
	private AbstractHandler handler0;
	
	private AbstractHandler handler1;
	
	public void start() {
		initHandler();
		
		logger.info("LBS Lora is detecting lora serialport......");
		Thread detect = new Thread(new DetectThread());
		detect.start();
		
		logger.info("LBS Lora obtain Module is starting......");
		Thread obtain = new Thread(new ObtainThread());
		obtain.start();
		
		logger.info("LBS Lora read Module is starting......");
		Thread read = new Thread(new ReadThread());
		read.start();
	}
	
	private void initHandler() {
		AbstractHandler obtainHandler = new ObtainHandler();		
		AbstractHandler writeHandler = new WriteHandler();
		AbstractHandler readHandler = new ReadHandler();
		AbstractHandler storeHandler = new StoreHandler();
		obtainHandler.setHandler(writeHandler);
		readHandler.setHandler(storeHandler);
		handler0 = obtainHandler;
		handler1 = readHandler;
	}

	public void setContext(LoraContext context) {
		this.context = context;
	}
	
	protected class DetectThread implements Runnable {
		
		@Override
		public void run() {
			while (true) {				
				try {
					if (!LoraRxtx.getInstance().isReady()) {
						try {
							LoraRxtx.getInstance().connect(new RxtxDeviceAddress(Global.RunParams.get("LoraCOM").toString()));
						} catch (Exception e) {
							logger.error(Global.getStackTrace(e));
						}
					}
					
					Thread.sleep(Local.DETECTMILLIS);
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
	}

	protected class ObtainThread implements Runnable {
		private Date lastTime = new Date();

		@Override
		public void run() {
			while (true) {
				try {
					long worktime = new Date().getTime() - lastTime.getTime();
					
					if (worktime < Local.WORKMILLIS) {
						Thread.sleep(Local.INTERVALMILLIS);						
					} else {
						Thread.sleep(Local.RESTMILLIS);						
						lastTime = new Date();
					}
					
					if (LoraRxtx.getInstance().isReady() && Global.DATAReady) {
						handler0.operate(context);			
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
	}
	
	protected class ReadThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (LoraRxtx.getInstance().isReady() && Global.DATAReady) {
						handler1.operate(context);
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
	}

}
