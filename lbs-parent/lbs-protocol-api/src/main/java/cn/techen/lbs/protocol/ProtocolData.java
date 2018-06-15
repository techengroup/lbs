package cn.techen.lbs.protocol;

import java.util.HashMap;

/**
 * ProtocolData
 * @author ZY
 * in and out
 * Key:Value    objs    : funcs                 values
 * Example      0,1,2,3 : 0,1,2,3               Aarray:2:Struct:2:OctString:2:0190:OctString:2:0191...
 */
public class ProtocolData extends HashMap<String, String> {

	private static final long serialVersionUID = -7834972449339092234L;	
	
	public String get(String key) {
		return super.get(key);
	}
	
}
