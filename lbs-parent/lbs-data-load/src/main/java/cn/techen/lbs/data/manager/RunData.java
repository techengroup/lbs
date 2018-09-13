package cn.techen.lbs.data.manager;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.db.api.FnService;
import cn.techen.lbs.db.api.LbsService;
import cn.techen.lbs.db.api.MeterService;
import cn.techen.lbs.db.api.ParamService;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.model.Fn;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Param;
import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MRelayService;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.Titles;

public class RunData implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private LbsService lbsService;
	private ParamService paramService;
	private FnService fnService;
	private MeterService meterService;	
	private MBaseService mBaseService;
	private MMeterService mMeterService;
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
				logger.error(GlobalUtil.getStackTrace(e));
			} catch (Exception e) {	
				logger.error(GlobalUtil.getStackTrace(e));
			}
		}
	}
	
	public void start() throws Exception {
		LBS lbs = null;
		List<Fn> fns = null;
		List<Param> params = null;
		List<Meter> meters = null;
		List<Meter> relays = null;
		
		
		if (count == 0) {
			mBaseService.flushDB();
			
			Local.LASTTIME = new Date();
			
			lbs = lbsService.selectByKey(0);
			params = paramService.selectAll();
			fns = fnService.selectAll();
			meters = meterService.selectAll();
			relays = meterService.selectRelay();
			
			if (lbs == null) {
				logger.error("There is no any lbs in the database...");
				return;
			}
			if (params == null || params.size() <= 0) {
				logger.error("There is no any run param in the database...");
				return;
			}
			if (fns == null || fns.size() <= 0) {
				logger.error("There is no any protocol function in the database...");
				return;
			}
			
			load(lbs, params, fns, meters, relays);
			
			Global.DATAReady = true;
		} else {
			Date nowTime = new Date();
			
			lbs = lbsService.selectByTime(Local.LASTTIME);
			fns = fnService.selectByTime(Local.LASTTIME);
			meters = meterService.selectByTime(Local.LASTTIME);
			params = paramService.selectByTime(Local.LASTTIME);
			
			Local.LASTTIME = nowTime;
			
			load(lbs, params, fns, meters, null);
		}
	}
	
	private void load(LBS lbs, List<Param> params, List<Fn> fns, List<Meter> meters, List<Meter> relays) {

		if (lbs != null) {			
			if (lbs.getChannel() != null) {
				Global.ChannelReady = true;
			}
			if (lbs.getLongitude() != null && lbs.getLatitude() != null) {
				Global.GISReady = true;
			}
			Global.lbs = lbs;
			logger.info("LBS parameters have been modified...");
		}
		
		if (params != null && params.size() > 0) {
			for (Param param : params) {
				Global.RunParams.put(param.getKey(), param.getValue());
			}
			logger.info("Load run parameter[{}] from database...", params.size());
		}	
		
		if (fns != null && fns.size() > 0) {
			for (Fn fn : fns) {
				String key = fn.getProtocol() + ":" + fn.getDirection() + ":" + fn.getOperation() + ":" + fn.getFunction();
				FnNames.getInstace().put(key, (fn.getName() == null) ? "" : fn.getName());				
				Elements.getInstace().put(key, (fn.getElements() == null) ? "" : fn.getElements());
				Titles.getInstace().put(key, (fn.getTitles() == null) ? "" : fn.getTitles());
			}
			logger.info("Load protocol function[{}] from database...", fns.size());
		}	
		
		if (meters != null && meters.size() > 0) {
			mMeterService.put(meters);
			logger.info("Load meter[{}] from database...", meters.size());
		}
		
		if (relays != null && relays.size() > 0) {
			mRelayService.put(relays);
			logger.info("Load relay[{}] from database...", relays.size());
		}
	}
	
	public void setLbsService(LbsService lbsService) {
		this.lbsService = lbsService;
	}

	public void setParamService(ParamService paramService) {
		this.paramService = paramService;
	}

	public void setFnService(FnService fnService) {
		this.fnService = fnService;
	}
	
	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
	}

	public void setmBaseService(MBaseService mBaseService) {
		this.mBaseService = mBaseService;
	}
	
	public void setmMeterService(MMeterService mMeterService) {
		this.mMeterService = mMeterService;
	}
	
	public void setmRelayService(MRelayService mRelayService) {
		this.mRelayService = mRelayService;
	}
	
}
