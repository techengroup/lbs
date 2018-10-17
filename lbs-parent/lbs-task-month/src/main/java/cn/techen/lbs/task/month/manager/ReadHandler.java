package cn.techen.lbs.task.month.manager;

import java.util.Date;

import cn.techen.lbs.task.month.common.MonthContext;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolFrame;

public class ReadHandler extends AbstractHandler {

	@Override
	public void operate(MonthContext context) throws Exception {
		if (State.RECIEVING == context.getState()) {
			ProtocolFrame frame = context.getmTaskService().rpop(MTaskService.QUEUE_LORA_RECEIVE + context.PRIORITY.value());
			if (frame != null) {
				frame.setrOutTime(new Date());
				context.fireDecode(frame);			
			}
		}
	}

}
