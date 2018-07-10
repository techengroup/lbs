package cn.techen.lbs.test;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.sql.AbstractSQL;
import junit.framework.TestCase;

public class BusinessTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		log.info("test");
	}

	public void testA() {
		try {
			AbstractSQL as = GlobalUtil.newSql("Fn1000043");
			Queue<Object> datas = new LinkedList<Object>();
			datas.add("10.10.1.1");
			datas.add("9010");
			datas.add("10.10.1.1");
			datas.add("9010");
			datas.add("cnnet");
			String sql = as.handle(0, datas);
			log.info(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
