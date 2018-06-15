package cn.techen.lbs.task.report.manager;

import java.util.Date;

import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.task.report.common.ReportContext;
import cn.techen.lbs.protocol.FrameConfig.State;

public class ReadHandler extends AbstractHandler {

	@Override
	public void operate(ReportContext context) throws Exception {
		if (State.RECIEVING == context.getState()) {
			ProtocolFrame frame = context.getmTaskService().rpop(MTaskService.QUEUE_RETURN + context.PRIORITY.value());
			if (frame != null) {
				frame.setrOutTime(new Date());
				context.fireDecode(frame);			
			}
		}
	}

}
