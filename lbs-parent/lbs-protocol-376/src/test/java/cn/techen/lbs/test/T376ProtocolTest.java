package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.ProtocolUtil;
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
		Elements.getInstace().put("100:1:0A:10", "ARRAY,2,STRUCT,10,OCT_STRING,3,%s,0,OCT_STRING,3,%s,0,OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,BCD_STRING,6,%s,0,"
				+ "OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,OCT_STRING,1,%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0,LOCATION,7,%s%s.%s%s%s%s%s,0");
		Elements.getInstace().put("100:0:0A:10", "ARRAY,2,OCT_STRING,3,%s,0");
		Elements.getInstace().put("100:0:0A:200", "ARRAY,2,OCT_STRING,3,%s,0");
//		Elements.getInstace().put("100:1:04:89","STRUCT,2,BCD_STRING,2,XXXX,0,OCT_STRING,2,%d,0");
//		Elements.getInstace().put("100:0:0A:89","STRUCT,2,BCD_STRING,2,XXXX,0,OCT_STRING,2,%d,0");
		
		Elements.getInstace().put("100:1:0D:177", "STRUCT,8,DATE_TIME,2,MMyy,1,DATE_TIME,5,mmHHddMMyy,1,OCT_STRING,1,%s,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0,BCD_STRING,5,3:2,0");
		Elements.getInstace().put("100:0:0D:177", "DATE_TIME,2,MMyy,1");
		Elements.getInstace().put("100:1:0D:178", "STRUCT,8,DATE_TIME,2,MMyy,1,DATE_TIME,5,mmHHddMMyy,1,OCT_STRING,1,%s,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0,BCD_STRING,4,3:1,0");
		Elements.getInstace().put("100:0:0D:178", "DATE_TIME,2,MMyy,1");
		Elements.getInstace().put("100:1:0E:1", "ARRAY,2,STRUCT,4,OCT_STRING,1,xx,0,BCD_STRING,6,6:0,0,DATE_TIME,6,ssmmHHddMMyy,1,OCT_STRING,16,xx,0");
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
		FnNames.getInstace().put("100:0:0A:200", "REPEATER FILES");
		FnNames.getInstace().put("100:1:0D:177", "MONTH FREEZE");
		FnNames.getInstace().put("100:0:0D:177", "METER FREEZE");
		
		Elements.getInstace().put("100:0:10:1", "BYTE_ARRAY,2,xx,1");
		Elements.getInstace().put("100:1:10:1", "STRUCT,2,OCT_STRING,1,xx,0,BYTE_ARRAY,1,xx,1");
		FnNames.getInstace().put("100:0:10:1", "Transfer Data");
		FnNames.getInstace().put("100:1:10:1", "Transfer Data");
		
		Titles.getInstace().put("100:1:04:3", "IP");
	}
	
//public void test() {
//	ProtocolConfig config = new DefaultProtocolConfig();
//	config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.LOGIN);
//	config.runs().put("PRM", 1);
//	config.units().add(new Date());
////ProtocolConfig config = new DefaultProtocolConfig();
////config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.HEARTBEAT);
////config.runs().put("PRM", 1);
////config.units().add(new Date());
////
//	T376Proxy proxy = new T376Proxy();
//	try {
//		log.info("Encode frame.");
//		byte[] frame = proxy.encode(config);	
//		proxy.decode(frame);			
//	} catch (Exception e) {
//		e.printStackTrace();
//	}

//String str = "78 32 00 32 00 78 0b 00 00 00 03 02 00 61 00 00 01 00 72 16";
//str = str.replace(" ", "");
//
//try {
//	byte[] bytes = ProtocolUtil.hexString2Byte(str);
//	log.info("Decode frame.");	
//	proxy.decode(bytes);			
//} catch (Exception e) {
//	e.printStackTrace();
////}
//}
	
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

//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr("000000003").setDir(DIR.SERVER).setOperation(OPERATION.GET);
//		config.runs().put("CONTROL", "0D");
//		//config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("1:177");
//		config.units().add(new Date());
//		config.units().add(new Date());
//		config.units().add(4);
//		config.units().add("12034");
//		config.units().add("1203.4");
//		config.units().add("120.34");
//		config.units().add("120.34");
//		config.units().add("1203.4");		
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
	
	
//	public void test02() {
//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr("000000006").setDir(DIR.SERVER).setOperation(OPERATION.GET);
//		config.runs().put("CONTROL", "0E");
//		//config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("0:1");
//		config.units().add(1);
//		config.units().add(11);
//		config.units().add("000000000014");
//		config.units().add(new Date());
//		config.units().add("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");		
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
//		String string = "78 52 02 52 02 78 4b 00 00 06 00 02 0d 70 02 03 "
//		 +"00 01 16 07 18 02 03 00 02 16 07 18 04 03 00 01 " 
//		 +"16 07 18 04 03 00 02 16 07 18 08 03 00 01 16 07 "
//		 +"18 08 03 00 02 16 07 18 20 03 00 01 16 07 18 20 "
//		 +"03 00 02 16 07 18 80 03 00 01 16 07 18 80 03 00 "
//		 +"02 16 07 18 01 04 00 01 16 07 18 01 04 00 02 16 "
//		 +"07 18 02 04 00 01 16 07 18 02 04 00 02 16 07 18 "
//		 +"04 04 00 01 16 07 18 04 04 00 02 16 07 18 08 04 "
//		 +"00 01 16 07 18 08 04 00 02 16 07 18 10 04 00 01 "
//		 +"16 07 18 10 04 00 02 16 07 18 f2 16"; 
//		
//		string = string.replace(" ", "");
//		
//		try {
//			proxy.decode(ProtocolUtil.hexString2Byte(string));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		String string1 = "78 3b 0b 3b 0b 78 8b 00 00 06 00 00 0d 60 02 03 " + 
//				" 00 01 16 07 18 26 14 26 07 18 04 00 10 00 00 00 " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee 02 03 00 02 16 07 18 26 14 26 07 18 " + 
//				" 04 ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee 04 03 00 01 16 ee ee ee ee ee ee " + 
//				" ee 04 ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee 04 03 00 02 16 " + 
//				" ee ee ee ee ee ee ee 04 ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee 08 03 00 01 " + 
//				" 16 ee ee ee ee ee ee ee 04 ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee 08 03 00 02 16 ee ee ee ee ee ee ee 04 ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee 20 03 00 01 16 ee ee ee ee ee ee ee 04 " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee 20 03 00 02 16 ee ee " + 
//				" ee ee ee ee ee 04 ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee 00 04 00 01 16 ee " + 
//				" ee ee ee ee ee ee 04 ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" 00 04 00 02 16 ee ee ee ee ee ee ee 04 ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee 01 04 00 01 16 ee ee ee ee ee ee ee 04 ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee 01 04 00 02 16 ee ee ee ee " + 
//				" ee ee ee 04 ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee 02 04 00 01 16 ee ee ee " + 
//				" ee ee ee ee 04 ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee 02 04 " + 
//				" 00 02 16 ee ee ee ee ee ee ee 04 ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee 04 " + 
//				" 04 00 01 16 07 18 27 14 26 07 18 04 00 51 63 00 " + 
//				" 00 ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee 04 04 00 02 16 07 18 27 14 26 07 " + 
//				" 18 04 ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee 08 04 00 01 16 ee ee ee ee ee " + 
//				" ee ee 04 ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee 08 04 00 02 " + 
//				" 16 ee ee ee ee ee ee ee 04 ee ee ee ee ee ee ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee 10 04 00 " + 
//				" 01 16 07 18 27 14 26 07 18 04 00 00 00 00 00 ee " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee 10 04 00 02 16 07 18 27 14 26 07 18 04 " + 
//				" ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee ee " + 
//				" ee ee ee ee 08 16 "; 
//				
//				string1 = string1.replace(" ", "");
//				
//				try {
//					proxy.decode(ProtocolUtil.hexString2Byte(string1));
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		
//		
//	}
	
	public void test10() {
	
		String r = "78 a2 02 a2 02 78 4b 00 00 06 00 02 0a 72 00 00"
				+ " 00 80 18 33 00 00 00 00 01 00 00 02 00 00 03 00"
				+ " 00 04 00 00 05 00 00 06 00 00 07 00 00 08 00 00"
				+ " 09 00 00 0a 00 00 0b 00 00 0c 00 00 0d 00 00 0e"
				+ " 00 00 0f 00 00 10 00 00 11 00 00 12 00 00 13 00"
				+ " 00 14 00 00 15 00 00 16 00 00 17 00 00 18 00 00"
				+ " 19 00 00 1a 00 00 1b 00 00 1c 00 00 1d 00 00 1e"
				+ " 00 00 1f 00 00 20 00 00 21 00 00 22 00 00 23 00"
				+ " 00 24 00 00 25 00 00 26 00 00 27 00 00 28 00 00"
				+ " 29 00 00 2a 00 00 2b 00 00 2c 00 00 2d 00 00 2e"
				+ " 00 00 2f 00 00 30 00 00 31 00 00 32 00 00 95 16";
		
		
	//		 78 8f 01 8f 01 78 8b 00 00 06 00 00 0a 66 00 00 
//		 00 00 19 03 00 01 00 00 01 00 00 21 00 67 45 23 
//		 01 00 00 00 00 00 32 12 20 01 00 00 00 11 42 80
//		 00 02 00 00 02 00 00 21 00 21 43 65 07 00 00 00
//		 00 00 23 31 20 01 00 00 00 21 21 30 00 03 00 00 
//		 03 00 00 21 00 88 77 23 01 00 00 00 00 00 43 41 
//		 20 01 00 00 00 11 23 30 00 77 16
//		 78 8f 01 8f 01 78 8b 00 00 06 00 00 0a 6b 00 00 |x....x.......k..|
//		 |00000010| 00 00 19 03 00 01 00 00 01 00 00 21 00 67 45 23 |...........!.gE#|
//		 |00000020| 01 00 00 00 00 00 32 12 20 01 00 00 00 11 42 80 |......2. .....B.|
//		 |00000030| 00 02 00 00 02 00 00 21 00 21 43 65 07 00 00 00 |.......!.!Ce....|
//		 |00000040| 00 00 23 31 20 01 00 00 00 21 21 30 00 03 00 00 |..#1 ....!!0....|
//		 |00000050| 03 00 00 21 00 88 77 23 01 00 00 00 00 00 43 41 |...!..w#......CA|
//		 |00000060| 20 01 00 00 00 11 23 30 00 7c 16
		
//		int dt1 = 0;
//		int dt2 = 0;
//		
//		String[] dts = new String[] {"200"};
//
//		for (int i = 0; i < dts.length; i++) {
//			String d = dts[i];
//			int t = Integer.parseInt(d);
//			int x = (int) Math.floor((double)t / 8);
//			int y = t % 8;
//			if (y == 0) {
//				dt1 = (int) Math.pow(2, 7);
//				dt2 = x - 1;
//			} else {
//				dt1 = (int) Math.pow(2, (y-1));
//				dt2 = x;
//			}			
//		}
//		log.info(String.valueOf(dt1));
//		log.info(String.valueOf(dt2));
		
//		78 7e 00 7e 00 78 4b 00 00 06 00 02 10 7c 00 00 |x~.~.xK......|..|
//		|00000010| 00 01 00 10 00 68 14 00 00 00 00 00 68 11 04 33 |.....h......h..3|
//		|00000020| 33 c3 43 65 16 d0 16
		
//		String r = "78 7e 00 7e 00 78 4b 00 00 06 00 02 10 7c 00 00 "
//				+ "00 01 00 10 00 68 14 00 00 00 00 00 68 11 04 33 "
//				+ "33 c3 43 65 16 d0 16";
		r = r.replace(" ", "");
		
		T376Proxy proxy = new T376Proxy();
		try {
			proxy.decode(ProtocolUtil.hexString2Byte(r));
			log.info("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
//	public void test03() {
//		int start = 22;
//		byte b = (byte) (start & 0xFF);
//		log.info("===================================={}", b);
//		
//		String trans = "68 29 05 00 02 13 00 00 00 00 00 00 06 98 07 99 60 00 58 98 07 99 60 00 51 06 10 68 51 00 60 99 07 98 68 11 04 33 33 c3 43 3a 16 2a 16";
//		trans = trans.replace(" ", "");
//		
//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr("000000006").setDir(DIR.CLIENT).setOperation(OPERATION.TRANSPORT);
//		//config.setCommAddr("000000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		config.funcs().add("0:1");
//		config.units().add(45);
//		config.units().add(ProtocolUtil.hexString2Byte(trans));
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
//		ProtocolConfig returnConfig = new DefaultProtocolConfig();
//		returnConfig.setCommAddr("000000006").setDir(DIR.SERVER)
//			.setOperation(OPERATION.TRANSPORT).runs().put("PRM", 0);
//		returnConfig.runs().put("SEQ", 1);
//		returnConfig.funcs().add("0:1");
//		returnConfig.units().add(80);
//		returnConfig.units().add(45);
//		returnConfig.units().add(ProtocolUtil.hexString2Byte(trans));
//		
//		try {
//			log.info("Encode frame.");
//			byte[] frame = proxy.encode(returnConfig);	
//			proxy.decode(frame);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
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
