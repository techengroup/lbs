package cn.techen.lbs.task.month;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		logger.info("Load monthly energy is starting......");
		Thread load = new Thread(new LoadThread());
		load.start();
		
		logger.info("LBS monthly energy task is starting......");
		Thread month = new Thread(new MonthThread());
		month.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		read = new ReadHandler();
	}

	public void setContext(MonthContext context) {
		this.context = context;
	}
	
	protected class LoadThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.LOADMILLIS);
					
					if (Global.GISReady && Global.ChannelReady) {
						if (context.getState() == State.FINISHED) {
							Long size = context.getmMonthService().size();
							if (size == null || size <= 0) {
								load();
							}
						}
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
		
		private void load() throws ParseException {
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
//			context.getmMonthService().lpush(context.getMeterService().selectMonth(ENERGY.NEGATIVE, time));//正向无功
		}

	}

	protected class MonthThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.GISReady && Global.ChannelReady) {
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
