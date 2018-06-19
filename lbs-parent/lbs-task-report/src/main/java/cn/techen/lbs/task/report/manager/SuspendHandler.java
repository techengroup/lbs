package cn.techen.lbs.task.report.manager;

import java.util.Date;

import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.task.report.common.ReportContext;

public class SuspendHandler extends AbstractHandler {

	@Override
	public void operate(ReportContext context) throws Exception {	
		ProtocolFrame frame = context.suspendQueue().poll();
		if (frame != null) {
			Date newTime = frame.getrOutTime();
			long diff = new Date().getTime() - newTime.getTime();
			
			if (diff < (12 * 3600 * 1000)) {//one day
				frame.reset();
				context.eventQueue().add(frame);
			}
		}
	}
	
}
