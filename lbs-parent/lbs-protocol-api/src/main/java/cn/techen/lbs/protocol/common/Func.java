package cn.techen.lbs.protocol.common;

import java.util.concurrent.ConcurrentHashMap;

public class Func extends ConcurrentHashMap<String, String> {
	
	private static final long serialVersionUID = 803552693696491218L;
	
	private static Func func;
	
	public static Func getInstance() {
		if (func == null) {
			synchronized (Func.class) {
				if (func == null) {
					func = new Func();
				}
			}
		}
		return func;
	}

}
