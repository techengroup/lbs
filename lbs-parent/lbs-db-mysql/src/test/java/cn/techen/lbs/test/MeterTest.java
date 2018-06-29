package cn.techen.lbs.test;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.mysql.common.Local;
import cn.techen.lbs.db.mysql.impl.MeterServiceImpl;
import junit.framework.TestCase;

public class MeterTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger(Local.PROJECT);
	
	@SuppressWarnings("null")
	public void testGetMeter() {
		MeterServiceImpl meterServiceImpl = new MeterServiceImpl();
		List<Meter> meters = meterServiceImpl.selectByTime(new Date());
//		Sector sector = meterServiceImpl.selectQuantity(1, 0);
		
		if (null != meters) {
			log.info("There is no any meter{}.", 1);
			
		} else {
			log.info("Size: {} ", meters.size() );
		}
						
		log.info("There is no any sector{}.", "");		
	}

}
