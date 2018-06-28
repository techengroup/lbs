package cn.techen.lbs.test;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.Titles;
import cn.techen.lbs.protocol.impl.LoraProxy;
import junit.framework.TestCase;

public class LoraProtocolTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
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
		config.setCommAddr("00000003,170903400023").setDir(DIR.CLIENT).setOperation(OPERATION.NET);
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
		 String format = "ARRAY,STRUCT,4,OCT_STRING,2,XXX,0,AA";
		 
		 List<String> fStrings = Arrays.asList(format.split(","));
		 
		 List<String> fList = fStrings.subList(0, 4);
		 
		 log.info("format：{}", fList.size());
		 
		 log.info("format：{}", format.substring(0, 5));
		 
		 String[] dts = format.split(",");		
			
		 format = Arrays.copyOfRange(dts, 4, dts.length).toString();
		 
		 extract(format);
		 
		 log.info("format：{}", format);
		 
		 int pos = format.indexOf(",");
		 
		 String format0 = format.substring(0, pos);
		 
		 log.info("format:{}", format0);
		 
		 format = format.substring(pos+1);
		 
		 log.info("format:{}", format);
	}
	
	private void extract(String format) {
		format = format.substring(6);
		
	}
}
