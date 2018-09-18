package cn.techen.lbs.task.realtime.manager;

import cn.techen.lbs.task.realtime.common.RealTimeContext;

public abstract class AbstractHandler {

	public abstract void operate(RealTimeContext context) throws Exception;

}
