package cn.techen.lbs.protocol.lora.common;

import java.util.HashMap;
import java.util.Map;

public class LoraFN {	
	private static Map<String, String> fnMap = new HashMap<String, String>();
	
	private static Map<String, String> fnExMap = new HashMap<String, String>();
	
	static {
		fnMap.put("1:0", "BCD_STRING,6,XXXXXXXXXXXX,1,ModuleAddr");
		fnMap.put("2:0", "BCD_STRING,2,XXXX,1,LogicAddr");
		fnMap.put("3:0", "OCT_STRING,1,XX,1,Channel");
		fnMap.put("4:0", "BCD_STRING,9,XXXXXXXXXXXX,1,ModuleAddr:BCD_STRING,2,XXXX,1,LogicAddr:OCT_STRING,1,XX,1,Channel");
		fnMap.put("5:0", "BIT_STRING,4,XXXXXXXX,1,EventCode");
		
		fnExMap.put("1", "| Long address  |");
		fnExMap.put("2", "| Short address |");
		fnExMap.put("3", "|    Channel    |");
		fnExMap.put("4", "| Long address  | Short address |    Channel    |");
		fnExMap.put("5", "|   Event Code  |");
	}
	
	public static String get(String key) {
		return fnMap.get(key);
	}
	
	public static String getEx(String key) {
		return fnExMap.get(key);
	}
}
