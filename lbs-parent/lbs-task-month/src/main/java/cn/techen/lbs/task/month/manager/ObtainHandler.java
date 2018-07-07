package cn.techen.lbs.task.month.manager;

import cn.techen.lbs.task.month.common.MonthContext;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.db.model.Meter;

public class ObtainHandler extends AbstractHandler {

	@Override
	public void operate(MonthContext context) throws Exception {
		if (State.FINISHED == context.getState()) {	
			Meter month = context.getMonth();
			if (month != null) {
				context.fireEncode(month);
			}
		}
	}
	
}
