package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.impl.LoraProxy;
import junit.framework.TestCase;

public class LoraProtocolTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("00000003,170903400023").setDir(DIR.CLIENT).setOperation(OPERATION.NET);
		config.dataId().add("2");
		config.dataUnit().add("0003");
		
		LoraProxy loraProxy = new LoraProxy();
		try {
			log.info("Encode frame.");
			byte[] frame = loraProxy.encode(config);
			log.info("Decode frame");
			loraProxy.decode(frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
