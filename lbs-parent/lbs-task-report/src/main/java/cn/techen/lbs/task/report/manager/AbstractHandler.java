package cn.techen.lbs.task.report.manager;

import cn.techen.lbs.task.report.common.ReportContext;

public abstract class AbstractHandler {
	
	private AbstractHandler handler;
	
	public abstract void operate(ReportContext context) throws Exception;

	public AbstractHandler getHandler() {
		return handler;
	}

	public void setHandler(AbstractHandler handler) {
		this.handler = handler;
	}
	
}
