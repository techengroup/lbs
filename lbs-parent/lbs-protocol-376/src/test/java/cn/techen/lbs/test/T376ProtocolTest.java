package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.impl.T376Proxy;
import junit.framework.TestCase;

public class T376ProtocolTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.LOGIN);
		config.userData().put("PRM", 1);
		
		T376Proxy proxy = new T376Proxy();
		try {
			log.info("Encode frame.");
			byte[] frame = proxy.encode(config);	
			proxy.decode(frame);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
		str = str.replace(" ", "");
		
		try {
			byte[] bytes = ProtocolUtil.hexString2Byte(str);
			log.info("Decode frame.");	
			proxy.decode(bytes);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
