package cn.techen.lbs.protocol.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FnNames extends ConcurrentHashMap<String, String> {
	
	private static final long serialVersionUID = -5673784942367330581L;
	
	private static FnNames fnNames = null;
	
	public static FnNames getInstace() {
		if (fnNames == null) {
			synchronized (FnNames.class) {
				if (fnNames == null) {
					fnNames = new FnNames();
				}
			}
		}
		return fnNames;
	}
	
	public String get(String[] keys) {
		List<String> vals = new ArrayList<String>();
		for (String key : keys) {
			vals.add(get(key));
		}
		return String.join(",", vals.toArray(new String[0]));
	}

}
