package cn.techen.lbs.task.month;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.db.common.DataConfig.ENERGY;
import cn.techen.lbs.task.month.common.Local;
import cn.techen.lbs.task.month.common.MonthContext;
import cn.techen.lbs.task.month.manager.AbstractHandler;
import cn.techen.lbs.task.month.manager.ObtainHandler;
import cn.techen.lbs.task.month.manager.ReadHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
		
	private MonthContext context;	
	private AbstractHandler obtain = new ObtainHandler();	
	private AbstractHandler read = new ReadHandler();
	private final ScheduledExecutorService loadSchedule = Executors.newSingleThreadScheduledExecutor();
	private final ScheduledExecutorService monthSchedule = Executors.newSingleThreadScheduledExecutor();

	public void start() {		
		logger.info("Load no month data is starting......");
		loadSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {					
					if (context.getState() == State.FINISHED) {
						Long size = context.getmMonthService().size(null);
						if (size == null || size <= 0) {
							Date time = new Date();
							String ms = Global.date2String(time, "yyyy-MM-01");
							time = Global.string2Date(ms, "yyyy-MM-01");
							
							List<Month> actives = context.getMonthService().selectMonth(ENERGY.ACTIVE, time);//正向有功
							if (actives == null || actives.size() <= 0) {
								logger.info("Loaded monthly active energy meters[0]...");
							} else {
								context.getmMonthService().lpush(actives);
								logger.info("Loaded monthly active energy meters[{}]...", actives.size());				
							}
						}
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}
				
			}
		}, Local.LOADMILLIS, Local.LOADMILLIS, TimeUnit.MILLISECONDS);
		
		logger.info("Month deamon is starting......");
		monthSchedule.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {					
					if (Global.ChannelReady && Global.LoraReady) {
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
	
	public void setContext(MonthContext context) {
		this.context = context;
	}
	
}
