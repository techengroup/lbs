package cn.techen.lbs.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.sql.AbstractSQL;
import junit.framework.TestCase;

public class LoadTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		log.info("test");
	}

	public void testA() {
		try {
			AbstractSQL as = Global.newSql("Fn1000043");	
			log.info(as.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	public void testGIS() {		
		double angle = Local.angle(30.313249, 120.06611, 30.336866, 120.116157);
		log.info("Angle:{}", angle);
		
		double angle1 = Local.angle(30.313249, 120.06611, 30.32517, 120.115528);
		log.info("Angle:{}", angle1);
	}
}
