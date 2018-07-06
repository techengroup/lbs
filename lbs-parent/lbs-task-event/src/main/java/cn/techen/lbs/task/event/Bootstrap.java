package cn.techen.lbs.task.event;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.task.event.common.EventContext;
import cn.techen.lbs.task.event.common.Local;
import cn.techen.lbs.task.event.manager.AbstractHandler;

public class Bootstrap {
		
	private EventContext context;
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		Thread net = new Thread(new NetThread());
		net.start();
	}
	
	private void initHandler() {
		
	}

	public void setContext(EventContext context) {
		this.context = context;
	}

	protected class NetThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.DATAReady) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					e.printStackTrace();
					context.reset();
				}				
			}
		}		
	}
	
}
