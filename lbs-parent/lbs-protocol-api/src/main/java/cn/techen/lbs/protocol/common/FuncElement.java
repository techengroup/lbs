package cn.techen.lbs.protocol.common;

import java.util.concurrent.ConcurrentHashMap;

public class FuncElement extends ConcurrentHashMap<String, String> {
	
	private static final long serialVersionUID = -5673784942367330581L;
	
	private static FuncElement funcElement = null;
	
	public static FuncElement getInstace() {
		if (funcElement == null) {
			synchronized (FuncElement.class) {
				if (funcElement == null) {
					funcElement = new FuncElement();
				}
			}
		}
		return funcElement;
	}

}
