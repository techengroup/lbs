package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.impl.T645Proxy;
import junit.framework.TestCase;

public class T645ProtocolTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("00000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		config.dataId().add("9110");
		
		T645Proxy t654Proxy = new T645Proxy();
		try {
			log.info("Encode frame.");
			byte[] frame = t654Proxy.encode(config);
			log.info("Decode frame.");
			t654Proxy.decode(frame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
