package cn.techen.lbs.task.networking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.networking.common.Local;
import cn.techen.lbs.task.networking.common.NetContext;
import cn.techen.lbs.task.networking.manager.AbstractHandler;
import cn.techen.lbs.task.networking.manager.DecodeHandler;
import cn.techen.lbs.task.networking.manager.EncodeHandler;
import cn.techen.lbs.task.networking.manager.ObtainHandler;
import cn.techen.lbs.task.networking.manager.ReadHandler;
import cn.techen.lbs.task.networking.manager.WriteHandler;
import cn.techen.lbs.task.networking.manager.StoreHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
		
	private NetContext context;

	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		logger.info("Loading Unregister Meter Module is starting......");
		Thread load = new Thread(new LoadThread());
		load.start();
		
		initHandler();
		
		logger.info("LBS Networking Module is starting......");
		Thread net = new Thread(new NetThread());
		net.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		AbstractHandler encode = new EncodeHandler();
		AbstractHandler write = new WriteHandler();
		obtain.setHandler(encode);
		encode.setHandler(write);
		read = new ReadHandler();
		AbstractHandler decode = new DecodeHandler();
		AbstractHandler store = new StoreHandler();
		read.setHandler(decode);
		decode.setHandler(store);
	}

	public void setContext(NetContext context) {
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
							Long size = context.getmRegisterService().size();
							if (size == null || size <= 0) {
								List<Meter> meters = context.getMeterService().selectUnregister();
								if (meters == null || meters.size() <= 0) {
									logger.info("Loaded unregister meters[0]...");
								} else {
									context.getmRegisterService().lpush(meters);
									logger.info("Loaded unregister meters[{}]...", meters.size());
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error(GlobalUtil.getStackTrace(e));
				}				
			}
		}		
	}

	protected class NetThread implements Runnable {

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
					logger.error(GlobalUtil.getStackTrace(e));
					context.reset(true);
				}				
			}
		}		
	}
	
}
