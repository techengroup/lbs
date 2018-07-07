package cn.techen.lbs.task.month.manager;

import cn.techen.lbs.task.month.common.MonthContext;

public abstract class AbstractHandler {

	public abstract void operate(MonthContext context) throws Exception;

}
