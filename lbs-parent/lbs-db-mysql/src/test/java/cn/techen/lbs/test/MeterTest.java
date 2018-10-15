package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.mysql.common.Local;
import junit.framework.TestCase;

public class MeterTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger(Local.PROJECT);
	
	public void testGetMeter() {
//		MeterServiceImpl meterServiceImpl = new MeterServiceImpl();
//		List<Meter> meters = meterServiceImpl.selectByTime(new Date());
////		Sector sector = meterServiceImpl.selectQuantity(1, 0);
//		
//		if (null != meters) {
//			log.info("There is no any meter{}.", 1);
//			
//		} else {
//			log.info("Size: {} ", meters.size() );
//		}
//						
//		log.info("There is no any sector{}.", "");	
//		
//		NodeServiceImpl nodeServiceImpl = new NodeServiceImpl();
//		List<Node> nodes = nodeServiceImpl.selectUnregister();
//		log.info("Node list size is {}.", nodes.size());
//		nodeServiceImpl.saveFailSingle(1, "11", 0, "0/1", "0,1", new Date(), new Date(), 0);
	}
	
	public void testA() {
		String sql = String.format("update PRM_METER set distance=%f, angle=%f, sector=%d, districtx=%d, districty=%d, mdfon=NOW() where id=%d"
				, 1.2342342, 1.1234234f, 1, 100, 1000, 1);		
		log.info(sql);
	}

}
