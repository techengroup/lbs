package cn.techen.lbs.test;

import java.awt.event.MouseWheelEvent;
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
//		
//		Titles.getInstace().put("100:1:02:1", "|            Clock           |");
		
		Elements.getInstace().put("100:1:02:3", "DATE_TIME,6,xxxx,1");
		FnNames.getInstace().put("100:1:02:3", "Heartbeat");
//		
//		Titles.getInstace().put("100:1:02:3", "|            Clock           |");

		Elements.getInstace().put("100:1:04:3", "STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,ASCII,16,xxxx,1");
		Elements.getInstace().put("100:0:0A:3", "STRUCT,5,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,IP_STRING,4,%s.%s.%s.%s,1,OCT_STRING,2,xxxx,0,ASCII,16,xxxx,1");
		Elements.getInstace().put("100:1:04:90", "STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0");
		Elements.getInstace().put("100:0:0A:90", "STRUCT,2,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0");
		Elements.getInstace().put("100:1:04:91", "OCT_STRING,1,%d,0");
		Elements.getInstace().put("100:0:0A:91", "OCT_STRING,1,%d,0");
		Elements.getInstace().put("100:1:04:89", "ADDRESS,4,XXXX,0");
		Elements.getInstace().put("100:0:0A:89", "ADDRESS,4,XXXX,0");
		Elements.getInstace().put("100:1:04:10", "ARRAY,2,STRUCT,10,OCT_STRING,3,%s,0,OCT_STRING,3,%s,0,OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,BCD_STRING,6,%s,0,"
				+ "OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0");
		Elements.getInstace().put("100:0:0A:10", "ARRAY,2,STRUCT,10,OCT_STRING,3,%s,0,OCT_STRING,3,%s,0,OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,BCD_STRING,6,%s,0,"
				+ "OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0");
//		Elements.getInstace().put("100:1:04:89","STRUCT,2,BCD_STRING,2,XXXX,0,OCT_STRING,2,%d,0");
//		Elements.getInstace().put("100:0:0A:89","STRUCT,2,BCD_STRING,2,XXXX,0,OCT_STRING,2,%d,0");
		
		Elements.getInstace().put("100:1:0D:177", "STRUCT,7,DATE_TIME,2,MMyy,1,DATE_TIME,5,mmHHddMMyy,1,OCT_STRING,1,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0,BCD_STRING,5,%s,0");
		Elements.getInstace().put("100:0:0D:177", "DATE_TIME,2,MMyy,1");
		FnNames.getInstace().put("100:1:04:3", "IP Port APN");
		FnNames.getInstace().put("100:0:0A:3", "IP Port APN");
		FnNames.getInstace().put("100:1:04:90", "LOCATION");
		FnNames.getInstace().put("100:0:0A:90", "LOCATION");
		FnNames.getInstace().put("100:1:04:91", "CHANNEL");
		FnNames.getInstace().put("100:0:0A:91", "CHANNEL");
		FnNames.getInstace().put("100:1:04:89", "ADDRESS");
		FnNames.getInstace().put("100:0:0A:89", "ADDRESS");
		FnNames.getInstace().put("100:1:04:10", "METER FILES");
		FnNames.getInstace().put("100:0:0A:10", "METER FILES");
		FnNames.getInstace().put("100:1:0D:177", "MONTH FREEZE");
		FnNames.getInstace().put("100:0:0D:177", "METER FREEZE");
		
		Titles.getInstace().put("100:1:04:3", "IP");
	}
	
//	public void test() {
////		ProtocolConfig config = new DefaultProtocolConfig();
////		config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.LOGIN);
////		config.runs().put("PRM", 1);
////		config.units().add(new Date());
//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.HEARTBEAT);
//		config.runs().put("PRM", 1);
//		config.units().add(new Date());
//		
//		T376Proxy proxy = new T376Proxy();
//		try {
//			log.info("Encode frame.");
//			byte[] frame = proxy.encode(config);	
//			proxy.decode(frame);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
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
//  }
//	public void testIP() {
//
//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("0:3");
//		config.runs().put("PRM", 1);
//		config.units().add("28.23.232.23");
//		config.units().add("9012");
//		config.units().add("28.23.232.23");
//		config.units().add("234");
//		config.units().add("CMNET");
//		
//		T376Proxy proxy = new T376Proxy();
//		try {
//			log.info("Encode frame.");
//			byte[] frame = proxy.encode(config);	
//			proxy.decode(frame);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
////		str = str.replace(" ", "");
////		
////		try {
////			byte[] bytes = ProtocolUtil.hexString2Byte(str);
////			log.info("Decode frame.");	
////			proxy.decode(bytes);			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}
	
//	public void testLocation() {
//
//		ProtocolConfig config = new DefaultProtocolConfig();
//		//config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.SET);
//		config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("0:90");
//		config.units().add("120.0723150821");
//		config.units().add("30.0723150821");
//	
//		
//		T376Proxy proxy = new T376Proxy();
//		try {
//			log.info("Encode frame.");
//			byte[] frame = proxy.encode(config);	
//			proxy.decode(frame);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
////		str = str.replace(" ", "");
////		
////		try {
////			byte[] bytes = ProtocolUtil.hexString2Byte(str);
////			log.info("Decode frame.");	
////			proxy.decode(bytes);			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}
//	public void testChannel() {
//
//		ProtocolConfig config = new DefaultProtocolConfig();
//		//config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.SET);
//		config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("0:91");
//		config.units().add("4");
//	
//
//		T376Proxy proxy = new T376Proxy();
//		try {
//			log.info("Encode frame.");
//			byte[] frame = proxy.encode(config);	
//			proxy.decode(frame);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
////		str = str.replace(" ", "");
////		
////		try {
////			byte[] bytes = ProtocolUtil.hexString2Byte(str);
////			log.info("Decode frame.");	
////			proxy.decode(bytes);			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}
//	public void testAddress() {
//
//		ProtocolConfig config = new DefaultProtocolConfig();
//		//config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.SET);
//		config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("0:89");
//		config.units().add("12340408");
//	
//
//		T376Proxy proxy = new T376Proxy();
//		try {
//			log.info("Encode frame.");
//			byte[] frame = proxy.encode(config);	
//			proxy.decode(frame);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
////		str = str.replace(" ", "");
////		
////		try {
////			byte[] bytes = ProtocolUtil.hexString2Byte(str);
////			log.info("Decode frame.");	
////			proxy.decode(bytes);			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}
	
	public void testMonthFreeze() {

		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.GET);
		config.runs().put("CONTROL", "0D");
		//config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		config.funcs().add("1:177");
		config.units().add(new Date());
		config.units().add(new Date());
		config.units().add(4);
		config.units().add("12034");
		config.units().add("12034");
		config.units().add("12034");
		config.units().add("12034");
		config.units().add("12034");
		config.units().add("12034");
	
		
	

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
//	public void testFile() {
//
//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.SET);
//		//config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("0:10");
//		config.units().add("15");
//		config.units().add("000001");
//		config.units().add("000001");
//		config.units().add("01");
//		config.units().add("01");
//		config.units().add("000000000001");
//		config.units().add("01");
//		config.units().add("01");
//		config.units().add("01");
//	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//		config.units().add("000002");
//		config.units().add("02");
//		config.units().add("02");
//		config.units().add("000000000002");
//		config.units().add("02");
//		config.units().add("02");
//		config.units().add("02");
//	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000003");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000004");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000005");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000006");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000007");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000008");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000009");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000010");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000011");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000012");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000013");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000014");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        config.units().add("000002");
//  		config.units().add("000002");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("000000000015");
//  		config.units().add("02");
//  		config.units().add("02");
//  		config.units().add("02");
//  	    config.units().add("120.0723150821");
//        config.units().add("30.0723150821");
//        
//	
//
//		T376Proxy proxy = new T376Proxy();
//		try {
//			log.info("Encode frame.");
//			byte[] frame = proxy.encode(config);	
//			proxy.decode(frame);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
////		str = str.replace(" ", "");
////		
////		try {
////			byte[] bytes = ProtocolUtil.hexString2Byte(str);
////			log.info("Decode frame.");	
////			proxy.decode(bytes);			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}


}
