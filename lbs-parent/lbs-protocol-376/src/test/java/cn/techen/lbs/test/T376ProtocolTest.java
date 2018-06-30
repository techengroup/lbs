package cn.techen.lbs.test;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.Titles;
import cn.techen.lbs.protocol.impl.T376Proxy;
import junit.framework.TestCase;

public class T376ProtocolTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	static {
		Elements.getInstace().put("100:1:02:1", "DATE_TIME,6,xxxx,1");
		FnNames.getInstace().put("100:1:02:1", "Login");
		
		Titles.getInstace().put("100:1:02:1", "|            Clock           |");
	}
	
	public void test() {
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.LOGIN);
		config.runs().put("PRM", 1);
		config.units().add(new Date());
		
		T376Proxy proxy = new T376Proxy();
		try {
			log.info("Encode frame.");
			byte[] frame = proxy.encode(config);	
			proxy.decode(frame);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
//		str = str.replace(" ", "");
//		
//		try {
//			byte[] bytes = ProtocolUtil.hexString2Byte(str);
//			log.info("Decode frame.");	
//			proxy.decode(bytes);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
