package cn.techen.lbs.lora;

import java.util.Date;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.lora.mananger.AbstractHandler;
import cn.techen.lbs.lora.mananger.ObtainHandler;
import cn.techen.lbs.lora.mananger.ReadHandler;
import cn.techen.lbs.lora.mananger.WriteHandler;
import cn.techen.lbs.lora.mananger.StoreHandler;

public class Bootstrap {
	
	private LoraContext context;
	
	private AbstractHandler handler0;
	
	private AbstractHandler handler1;
	
	public void start() {
		initHandler();
		
		Thread obtain = new Thread(new ObtainThread());
		obtain.start();
		
		Thread read = new Thread(new ReadThread());
		read.start();
	}
	
	public void initHandler() {
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

	protected class ObtainThread implements Runnable {
		private Date lastTime = new Date();

		@Override
		public void run() {
			while (true) {
				try {
					long worktime = new Date().getTime() - lastTime.getTime();
					
					if (worktime < Local.WORKTIME) {
						Thread.sleep(Local.INTERVALMILLIS);						
					} else {
						Thread.sleep(Local.RESTMILLIS);						
						lastTime = new Date();
					}
					
					if (Global.DATAReady) {
						handler0.operate(context);			
					}
				} catch (Exception e) {
					e.printStackTrace();
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
					
					if (Global.DATAReady) {
						handler1.operate(context);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}

}
