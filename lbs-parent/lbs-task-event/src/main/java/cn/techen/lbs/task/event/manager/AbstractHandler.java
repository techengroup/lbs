package cn.techen.lbs.task.event.manager;

import cn.techen.lbs.task.event.common.EventContext;

public abstract class AbstractHandler {

	public abstract void operate(EventContext context) throws Exception;

}
