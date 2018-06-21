package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.business.reflect.ReflectionHelper;
import junit.framework.TestCase;

public class BusinessTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		try {
			Object object = ReflectionHelper.newInstance("cn.techen.lbs.db.model.LBS");
			log.info(object.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testA() {
		 
	}
}
