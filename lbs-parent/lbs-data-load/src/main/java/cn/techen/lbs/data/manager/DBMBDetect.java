package cn.techen.lbs.data.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.mm.api.MBaseService;

public class DBMBDetect implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private GeneralService generalService;	
	private MBaseService mBaseService;
	
	@Override
	public void run() {
		while (true) {
			try {				
				start();
				
				Thread.sleep(Local.INTERVALMILLIS);
			} catch (Exception e) {	
				logger.error(Global.getStackTrace(e));
			}
		}
	}
	
	public void start() throws Exception {
		Global.DBReady = generalService.isConnected();
		logger.info("DB connect status:{}.", Global.DBReady);
		Global.MBReady = mBaseService.isConnected();
		logger.info("MB connect status:{}.", Global.MBReady);
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public void setmBaseService(MBaseService mBaseService) {
		this.mBaseService = mBaseService;
	}
	
}
