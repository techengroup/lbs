package cn.techen.lbs.task.report.manager;

import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.report.common.ReportContext;

public class ObtainHandler extends AbstractHandler {

	@Override
	public void operate(ReportContext context) throws Exception {	
		if (State.FINISHED == context.getState()) {	
			ProtocolFrame eventFrame = context.eventQueue().poll();
			if (eventFrame != null) {
				context.fireWrite(eventFrame);
			}
		}
	}
	
}
