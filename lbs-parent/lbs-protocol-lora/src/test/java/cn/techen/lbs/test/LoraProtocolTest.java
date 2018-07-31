package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.common.Titles;
import cn.techen.lbs.protocol.impl.LoraProxy;
import junit.framework.TestCase;

public class LoraProtocolTest extends TestCase {
	private static Logger log = LoggerFactory.getLogger(LoraProtocolTest.class);
	
	static {
		Elements.getInstace().put("0:0:1:2", "BCD_STRING,2,xxxx,1,Short address");

		Elements.getInstace().put("2:5:1", "BIT_STRING,4,XXXXXXXX,1,EventCode");
		
		Elements.getInstace().put("3:1:1", "BCD_STRING,6,XXXXXXXXXXXX,1,ModuleAddr");
		Elements.getInstace().put("3:2:1", "BCD_STRING,2,XXXX,1,LogicAddr");
		Elements.getInstace().put("3:3:1", "OCT_STRING,1,XX,1,Channel");
		Elements.getInstace().put("3:4:1", "BCD_STRING,9,XXXXXXXXXXXX,1,ModuleAddr:BCD_STRING,2,XXXX,1,LogicAddr:OCT_STRING,1,XX,1,Channel");
		
		Elements.getInstace().put("4:1:0", "BCD_STRING,6,XXXXXXXXXXXX,1,ModuleAddr");
		Elements.getInstace().put("4:2:0", "BCD_STRING,2,XXXX,1,LogicAddr");
		Elements.getInstace().put("4:3:0", "OCT_STRING,1,XX,1,Channel");
		Elements.getInstace().put("4:4:0", "BCD_STRING,9,XXXXXXXXXXXX,1,ModuleAddr:BCD_STRING,2,XXXX,1,LogicAddr:OCT_STRING,1,XX,1,Channel");
		
		Elements.getInstace().put("4:1:1", "OCT_STRING,1,XX,1,Result");
		Elements.getInstace().put("4:2:1", "OCT_STRING,1,XX,1,Result");
		Elements.getInstace().put("4:3:1", "OCT_STRING,1,XX,1,Result");
		Elements.getInstace().put("4:4:1", "OCT_STRING,1,XX,1,Result");
		
		Titles.getInstace().put("2", "Short address");
	}
	
	public void test() {
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("00000003,170903400023").setDir(DIR.CLIENT).setOperation(OPERATION.LOGIN);
		config.funcs().add("2");
		config.units().add("0003");
		
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
	
	public void testA() {
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("00000003,170903400023").setDir(DIR.CLIENT).setOperation(OPERATION.REPORT);
//		config.funcs().add("2");
//		config.units().add("0003");
		
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
	
	public void testC() {
		String str = "123EEEeeawvw";
		boolean b = ProtocolUtil.isAllE(str);
		log.info("Is all E:{}", b);
	}
	
}
