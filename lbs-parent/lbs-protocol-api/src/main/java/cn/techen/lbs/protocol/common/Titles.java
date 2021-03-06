package cn.techen.lbs.protocol.common;

import java.util.concurrent.ConcurrentHashMap;

public class Titles extends ConcurrentHashMap<String, String> {
	
	private static final long serialVersionUID = 5536396383097503302L;
	
	private static Titles titles = null;
	
	public static Titles getInstace() {
		if (titles == null) {
			synchronized (Titles.class) {
				if (titles == null) {
					titles = new Titles();
				}
			}
		}
		return titles;
	}

}
