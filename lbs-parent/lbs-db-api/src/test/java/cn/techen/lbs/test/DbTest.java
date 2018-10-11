package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.sql.AbstractSQL;
import junit.framework.TestCase;

public class DbTest extends TestCase {
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
		
		 
	}
}
