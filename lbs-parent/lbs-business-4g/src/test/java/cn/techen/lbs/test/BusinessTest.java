package cn.techen.lbs.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.sql.AbstractSQL;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import junit.framework.TestCase;

public class BusinessTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		log.info("test");
	}

	public void testA() {
		try {
			AbstractSQL as = GlobalUtil.newSql("Fn10000491");
			Queue<Object> datas = new LinkedList<Object>();
			datas.add(3);
			String sql = as.handle(0, datas);
			log.info(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	public void testB() {
		try {
			String con = "10011101";

			List<String> vals = new ArrayList<String>();
			String[] fs = "3:5".split(":");
			int start = 0;
			int end = 0;
			for (String f : fs) {
				end = start + Integer.parseInt(f);
				vals.add(con.substring(start, end));
				start = end;
			}
			
			String content = String.join(":", vals.toArray(new String[0]));
			log.info(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
