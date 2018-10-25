package cn.techen.lbs.task.event;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	private AbstractHandler obtain = new ObtainHandler();	
	private AbstractHandler read = new ReadHandler();
	private final ScheduledExecutorService loadSchedule = Executors.newSingleThreadScheduledExecutor();
	private final ScheduledExecutorService eventSchedule = Executors.newSingleThreadScheduledExecutor();

	public void start() {
		logger.info("Load report event is starting......");
		loadSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {					
					if (context.getState() == State.FINISHED) {
						Long size = context.getmReportService().size(null);
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
		}, Local.LOADMILLIS, Local.LOADMILLIS, TimeUnit.MILLISECONDS);
		
		logger.info("Event deamon is starting......");
		eventSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.LoraReady) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					context.reset();
					logger.error(Global.getStackTrace(e));
				}
			}
		}, Local.INTERVALMILLIS, Local.INTERVALMILLIS, TimeUnit.MILLISECONDS);
	}

	public void setContext(EventContext context) {
		this.context = context;
	}
	
}
