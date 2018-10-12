package cn.techen.lbs.task.event;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.event.common.EventContext;
import cn.techen.lbs.task.event.common.Local;
import cn.techen.lbs.task.event.manager.AbstractHandler;
import cn.techen.lbs.task.event.manager.ObtainHandler;
import cn.techen.lbs.task.event.manager.ReadHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
		
	private EventContext context;
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		logger.info("Load report event is starting......");
		Thread load = new Thread(new LoadThread());
		load.start();
		
		initHandler();
		
		logger.info("LBS event task is starting......");
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
	
	protected class LoadThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.LOADMILLIS);
					
					if (context.getState() == State.FINISHED) {
						Long size = context.getmReportService().size();
						if (size == null || size <= 0) {
							List<Report> reports = context.getReportService().selectAll();
							if (reports == null || reports.size() <= 0) {
								logger.info("Loaded event report meters[0]...");
							} else {
								context.getmReportService().lpush(reports);
								logger.info("Loaded event report meters[{}]...", reports.size());
							}
						}
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
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
					
					if (Global.LoraReady) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
					context.reset();
				}				
			}
		}		
	}
	
}
