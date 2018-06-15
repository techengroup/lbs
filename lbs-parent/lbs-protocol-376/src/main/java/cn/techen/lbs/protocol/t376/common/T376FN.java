package cn.techen.lbs.protocol.t376.common;

import java.util.HashMap;
import java.util.Map;

public class T376FN extends HashMap<String, String> {
	private static final long serialVersionUID = -2807947219791856991L;

	private static Map<String, String> fnMap = new HashMap<String, String>();

	static {
		// fnMap.put("2:1:1", "DateTime_E,0,ssmmHH ddMMyy E,0");
		fnMap.put("2:3:1", "DateTime,6,ssmmHH ddMMyy E,1,DateTime");
		fnMap.put("4:3:0", "IP,4,%d.%d.%d.%d,1,PRM_LBS-IP:OCT_STING,2,XXXXX,1,PRM_LBS-Port:IP,4,%d.%d.%d.%d,1,PRM_LBS-IP1:OCT_STING,2,XXXXX,1,PRM_LBS-Port1:ASCII,16,xxxxxxxx,1,PRM_LBS-APN");
		// fnMap.put("0:1:1", "DateTime_E,0,ssmmHH ddMMyy E,1");
		// fnMap.put("0:2:1", "DateTime_E,0,ssmmHH ddMMyy E,1");
	}

	public static String get(String key) {
		return fnMap.get(key);
	}

}
