package cn.techen.lbs.device.manager;

import cn.techen.lbs.device.common.DeviceContext;

public abstract class AbstractHandler {
	
	private AbstractHandler handler;
	
	public abstract void operate(DeviceContext context) throws Exception;

	public AbstractHandler getHandler() {
		return handler;
	}

	public void setHandler(AbstractHandler handler) {
		this.handler = handler;
	}
	
}
