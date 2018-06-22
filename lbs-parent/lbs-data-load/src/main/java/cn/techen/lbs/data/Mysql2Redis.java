package cn.techen.lbs.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.db.api.FuncElementService;
import cn.techen.lbs.db.api.FuncService;
import cn.techen.lbs.db.api.LbsService;
import cn.techen.lbs.db.api.MeterService;
import cn.techen.lbs.db.model.Global;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.mm.api.MLbsService;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MRegisterService;
import cn.techen.lbs.mm.api.MRelayService;
import cn.techen.lbs.protocol.common.Func;
import cn.techen.lbs.protocol.common.FuncElement;

public class Mysql2Redis implements Runnable {
	private static final Logger log = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);
	private LbsService lbsService;
	private MeterService meterService;	
	private FuncService funcService;
	private FuncElementService funcElementService;
	private MBaseService mBaseService;
	private MLbsService mLbsService;
	private MMeterService mMeterService;
	private MRegisterService mRegisterService;
	private MRelayService mRelayService;

	private int count = 0;
	
	@Override
	public void run() {
		while (true) {
			try {
				if (count > 0) 
					Thread.sleep(Local.INTERVALMILLIS);
				
				start();
				
				count++;
				if (count > 100000) count = 1;
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			} catch (Exception e) {			
				log.error(e.getMessage());
			}
		}
	}
	
	public void start() throws Exception {
		LBS lbs = null;
		List<Meter> meters = null;
		List<Meter> unregisterMeters = null;
		List<Meter> relays = null;
		Map<String, String> funcs = null;
		Map<String, String> funcElements = null;
		
		if (count == 0) {
			mBaseService.flushDB();
			
			Local.LASTTIME = new Date();
			
			lbs = lbsService.selectByKey(0);
			meters = meterService.selectAll();
			unregisterMeters = meterService.selectUnregister();
			relays = meterService.selectRelay();
			funcs = funcService.selectAll();
			funcElements = funcElementService.selectAll();
		} else {
			Date nowTime = new Date();
			
			lbs = lbsService.selectByTime(Local.LASTTIME);
			meters = meterService.selectByTime(Local.LASTTIME);
			unregisterMeters = meterService.selectUnregisterByTime(Local.LASTTIME);
			funcs = funcService.selectByTime(Local.LASTTIME);
			funcElements = funcElementService.selectByTime(Local.LASTTIME);
			
			Local.LASTTIME = nowTime;
		}
		
		if (lbs == null) {
			log.warn("There is no any lbs in the database...");
		} else {
			mLbsService.set(lbs);
			log.info("Load lbs amount is {} from database.", 1);
		}
		
		if (meters == null || meters.size() <= 0) {
			log.warn("There is no any meter in the database...");
		} else {
			mMeterService.put(meters);
			log.info("Load meter amount is {} from database.", meters.size());
		}	
		
		if (unregisterMeters == null || unregisterMeters.size() <= 0) {
			log.info("There is no any unregister meter in the database...");
		} else {
			mRegisterService.lpush(unregisterMeters);
			log.info("Load unregister meter amount is {} from database.", unregisterMeters.size());
		}
		
		if (relays == null || relays.size() <= 0) {
			log.info("There is no any relay in the database...");
		} else {
			mRelayService.put(relays);
			log.info("Load relay amount is {} from database.", relays.size());
		}	
		
		if (funcs == null || funcs.size() <= 0) {
			log.warn("There is no any protocol function in the database...");
		} else {
			Func.getInstance().putAll(funcs);
		}
		
		if (funcElements == null || funcElements.size() <= 0) {
			log.warn("There is no any protocol function element in the database...");
		} else {
			FuncElement.getInstace().putAll(funcElements);
		}
		
		Global.DBReady = true;
	}
	
	public void setLbsService(LbsService lbsService) {
		this.lbsService = lbsService;
	}
	
	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
	}

	public void setFuncService(FuncService funcService) {
		this.funcService = funcService;
	}

	public void setFuncElementService(FuncElementService funcElementService) {
		this.funcElementService = funcElementService;
	}

	public void setmBaseService(MBaseService mBaseService) {
		this.mBaseService = mBaseService;
	}

	public void setmLbsService(MLbsService mLbsService) {
		this.mLbsService = mLbsService;
	}
	
	public void setmMeterService(MMeterService mMeterService) {
		this.mMeterService = mMeterService;
	}

	public void setmRegisterService(MRegisterService mRegisterService) {
		this.mRegisterService = mRegisterService;
	}
	
	public void setmRelayService(MRelayService mRelayService) {
		this.mRelayService = mRelayService;
	}
	
}
