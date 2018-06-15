package cn.techen.lbs.protocol.t645.common;

import java.util.HashMap;
import java.util.Map;

public class T645FN {	
	private static Map<String, String> fnMap = new HashMap<String, String>();
	
	private static Map<String, String> fnExMap = new HashMap<String, String>();
	
	static {
		fnMap.put("9110:1", "OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY0");
		fnMap.put("9111:1", "OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY1");
		fnMap.put("9112:1", "OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY2");
		fnMap.put("9113:1", "OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY3");
		fnMap.put("9114:1", "OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY4");
		fnMap.put("911F:1", "OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY"
				+ ":OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY1"
				+ ":OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY2"
				+ ":OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY3"
				+ ":OCT_STRING,4,xxxxxx.xx,1,DATA_ENERGY_MONTH-ACTIVE_ENERGY4");
		fnMap.put("C429:1", "BIT_STRING,4,XXXXXXXX,1,EventCode");
		
		fnExMap.put("9110", "|  Active Energy0  |");
		fnExMap.put("9111", "|  Active Energy1  |");
		fnExMap.put("9112", "|  Active Energy2  |");
		fnExMap.put("9113", "|  Active Energy3  |");
		fnExMap.put("9113", "|  Active Energy4  |");
		fnExMap.put("911F", "|  Active Energy0  |  Active Energy1  |  Active Energy2  |  Active Energy3  |  Active Energy4  |");
	}
	
	public static String get(String key) {
		return fnMap.get(key);
	}
	
	public static String getEx(String key) {
		return fnExMap.get(key);
	}
}
