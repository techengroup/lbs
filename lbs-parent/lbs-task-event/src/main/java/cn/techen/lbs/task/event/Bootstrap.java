package cn.techen.lbs.task.event;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.task.event.common.EventContext;
import cn.techen.lbs.task.event.common.Local;
import cn.techen.lbs.task.event.manager.AbstractHandler;
import cn.techen.lbs.task.event.manager.ObtainHandler;
import cn.techen.lbs.task.event.manager.ReadHandler;

public class Bootstrap {
		
	private EventContext context;
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		Thread event = new Thread(new EventThread());
		event.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		read = new ReadHandler();
	}

	public void setContext(EventContext context) {
		this.context = context;
	}

	protected class EventThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.GISReady) {
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
