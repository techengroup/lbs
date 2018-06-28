package cn.techen.lbs.protocol.common;

import java.util.concurrent.ConcurrentHashMap;

public class Elements extends ConcurrentHashMap<String, String> {
	
	private static final long serialVersionUID = -5673784942367330581L;
	
	private static Elements elements = null;
	
	public static Elements getInstace() {
		if (elements == null) {
			synchronized (Elements.class) {
				if (elements == null) {
					elements = new Elements();
				}
			}
		}
		return elements;
	}

}
