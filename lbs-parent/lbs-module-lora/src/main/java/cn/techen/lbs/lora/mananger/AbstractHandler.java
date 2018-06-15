package cn.techen.lbs.lora.mananger;

import cn.techen.lbs.lora.common.LoraContext;

public abstract class AbstractHandler {
	
	private AbstractHandler handler;
	
	public abstract void operate(LoraContext context) throws Exception;

	public AbstractHandler getHandler() {
		return handler;
	}

	public void setHandler(AbstractHandler handler) {
		this.handler = handler;
	}
	
}
