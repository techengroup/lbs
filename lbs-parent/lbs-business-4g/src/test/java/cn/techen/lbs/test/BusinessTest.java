package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.business.common.BusinessUtil;
import cn.techen.lbs.db.common.AbstractSQL;
import junit.framework.TestCase;

public class BusinessTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		log.info("test");
	}

	public void testA() {
		try {
			AbstractSQL as = BusinessUtil.newSql("Fn1000043");
			log.info(as.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
