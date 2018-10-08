package cn.techen.lbs.task.network.manager;

import cn.techen.lbs.task.network.common.NetContext;

public abstract class AbstractHandler {
	
	private AbstractHandler handler;
	
	public abstract void operate(NetContext context) throws Exception;

	public AbstractHandler getHandler() {
		return handler;
	}

	public void setHandler(AbstractHandler handler) {
		this.handler = handler;
	}
	
}
