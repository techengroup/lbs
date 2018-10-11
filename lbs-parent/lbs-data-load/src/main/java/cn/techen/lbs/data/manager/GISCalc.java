package cn.techen.lbs.data.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.db.api.MeterService;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Meter;

public class GISCalc implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private MeterService meterService;
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(Local.INTERVALMILLIS);
				
				if (Global.GISReady) start();
			} catch (InterruptedException e) {
				logger.error(Global.getStackTrace(e));
			} catch (Exception e) {		
				logger.error(Global.getStackTrace(e));
			}
		}
	}
	
	public void start() throws Exception {
		List<Meter> meters = meterService.selectGIS();
		logger.info("Get GIS meter[{}] need caculate from database...", meters.size());
		
		for (Meter meter : meters) {
			double distance = Local.distance(Global.lbs.getLatitude(), Global.lbs.getLongitude(), meter.getLatitude(), meter.getLongitude());		
			float angle = (float) Local.angle(Global.lbs.getLatitude(), Global.lbs.getLongitude(), meter.getLatitude(), meter.getLongitude());
			int sector = Local.sector(angle);
			int districtX = Local.districtX(distance);
			int districtY = Local.districtY(angle);
			
			meter.setDistance(distance);
			meter.setAngle(angle);
			meter.setSector(sector);
			meter.setDistrictX(districtX);
			meter.setDistrictY(districtY);
		}
		
		if (meters != null && meters.size() > 0) {
			meterService.updateGIS(meters);
		}		
	}
	
	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
	}
	
}
