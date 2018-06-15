package cn.techen.lbs.test;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.g4.Bootstrap;
import cn.techen.lbs.g4.common.G4Context;
import junit.framework.TestCase;

public class G4Test extends TestCase {
	private static Logger logger = (Logger) LoggerFactory.getLogger("Lora-Lora");
	
	public void test() {
		Map<Integer, String> handlerMap = new HashMap<Integer, String>();
		handlerMap.put(21, "AA");
		
		byte[] bs = new byte[] {21};
		String aString = handlerMap.get((int)bs[0]);
		
		logger.info(aString);
		
		String host = "127.0.0.1";
		String newHost = "127.0.0.1";
		
		if (host.equals(newHost)) {
			logger.info("equal");
		} else {
			logger.info("not equal");
		}
		
		
		G4Context context = new G4Context();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.setContext(context);
		bootstrap.start();
		logger.info("End");
	}
}
