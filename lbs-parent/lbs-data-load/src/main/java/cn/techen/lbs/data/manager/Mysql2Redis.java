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
import cn.techen.lbs.db.api.ReportService;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Fn;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Param;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MRegisterService;
import cn.techen.lbs.mm.api.MRelayService;
import cn.techen.lbs.mm.api.MReportService;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.Titles;

public class Mysql2Redis implements Runnable {
	private static final Logger log = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);
	private LbsService lbsService;
	private MeterService meterService;	
	private FnService fnService;
	private ReportService reportService;
	private ParamService paramService;
	private MBaseService mBaseService;
	private MMeterService mMeterService;
	private MRegisterService mRegisterService;
	private MRelayService mRelayService;
	private MReportService mReportService;

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
				log.error(e.getCause().getMessage());
			} catch (Exception e) {	
				log.error(e.getCause().getMessage());
			}
		}
	}
	
	public void start() throws Exception {
		LBS lbs = null;
		List<Meter> meters = null;
		List<Meter> unregisterMeters = null;
		List<Meter> relays = null;
		List<Fn> fns = null;
		List<Report> reports = null;
		List<Param> params = null;
		
		if (count == 0) {
			mBaseService.flushDB();
			
			Local.LASTTIME = new Date();
			
			lbs = lbsService.selectByKey(0);
			fns = fnService.selectAll();
			meters = meterService.selectAll();
			unregisterMeters = meterService.selectUnregister();
			relays = meterService.selectRelay();
			reports = reportService.selectAll();
			params = paramService.selectAll();
			
			if (lbs == null) {
				log.error("There is no any lbs in the database...");
				return;
			}
			if (fns == null || fns.size() <= 0) {
				log.error("There is no any protocol function in the database...");
				return;
			}
			if (params == null || params.size() <= 0) {
				log.error("There is no any run param in the database...");
				return;
			}
			
			load(lbs, fns, meters, unregisterMeters, relays, reports, params);
			
			Global.DATAReady = true;
		} else {
			Date nowTime = new Date();
			
			lbs = lbsService.selectByTime(Local.LASTTIME);
			fns = fnService.selectByTime(Local.LASTTIME);
			meters = meterService.selectByTime(Local.LASTTIME);
			unregisterMeters = meterService.selectUnregisterByTime(Local.LASTTIME);
			reports = reportService.selectByTime(Local.LASTTIME);
			params = paramService.selectByTime(Local.LASTTIME);
			
			Local.LASTTIME = nowTime;
			
			load(lbs, fns, meters, unregisterMeters, null, reports, params);
		}
	}
	
	private void load(LBS lbs, List<Fn> fns, List<Meter> meters
			, List<Meter> unregisterMeters, List<Meter> relays, List<Report> reports, List<Param> params) {

		if (lbs != null) {			
			if (Global.lbs == null || !Global.lbs.getChannel().equals(lbs.getChannel())) {
				Global.LoraReady = false;
			}
			if (lbs.getLongitude() != null && lbs.getLatitude() != null) {
				Global.GISReady = true;
			}
			Global.lbs = lbs;
			log.info("LBS parameters have been modified...");
		}
		
		if (fns != null && fns.size() > 0) {
			for (Fn fn : fns) {
				String key = fn.getProtocol() + ":" + fn.getDirection() + ":" + fn.getOperation() + ":" + fn.getFunction();
				FnNames.getInstace().put(key, (fn.getName() == null) ? "" : fn.getName());				
				Elements.getInstace().put(key, (fn.getElements() == null) ? "" : fn.getElements());
				Titles.getInstace().put(key, (fn.getTitles() == null) ? "" : fn.getTitles());
			}
			log.info("Load protocol function[{}] in the database...", fns.size());
		}	
		
		if (params != null && params.size() > 0) {
			for (Param param : params) {
				Global.RunParams.put(param.getKey(), param.getValue());
			}
			log.info("Load run parameter[{}] in the database...", params.size());
		}	
		
		if (meters != null && meters.size() > 0) {
			mMeterService.put(meters);
			log.info("Load meter[{}] from database...", meters.size());
		}	
		
		if (unregisterMeters != null && unregisterMeters.size() > 0) {
			mRegisterService.lpush(unregisterMeters);
			log.info("Load unregister meter[{}] from database...", unregisterMeters.size());
		}
		
		if (relays != null && relays.size() > 0) {
			mRelayService.put(relays);
			log.info("Load relay[{}] from database...", relays.size());
		}
		
		if (reports != null && reports.size() > 0) {
			mReportService.lpush(reports);
			log.info("Load report event[{}] from database...", reports.size());
		}
	}
	
	public void setLbsService(LbsService lbsService) {
		this.lbsService = lbsService;
	}
	
	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
	}

	public void setFnService(FnService fnService) {
		this.fnService = fnService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void setParamService(ParamService paramService) {
		this.paramService = paramService;
	}

	public void setmBaseService(MBaseService mBaseService) {
		this.mBaseService = mBaseService;
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

	public void setmReportService(MReportService mReportService) {
		this.mReportService = mReportService;
	}
	
}
