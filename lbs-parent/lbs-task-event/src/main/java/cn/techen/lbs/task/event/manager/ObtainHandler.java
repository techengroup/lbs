package cn.techen.lbs.task.event.manager;

import cn.techen.lbs.task.event.common.EventContext;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.db.model.Report;

public class ObtainHandler extends AbstractHandler {

	@Override
	public void operate(EventContext context) throws Exception {
		if (State.FINISHED == context.getState()) {	
			Report report = context.getReport();
			if (report != null) {
				context.fireEncode(report);
			}
		}
	}
	
}
