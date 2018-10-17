package cn.techen.lbs.task.realtime.manager;

import java.util.Date;

import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.task.realtime.common.RealTimeContext;

public class ReadHandler extends AbstractHandler {

	@Override
	public void operate(RealTimeContext context) throws Exception {
		if (State.RECIEVING == context.getState()) {
			ProtocolFrame frame = context.getmTaskService().rpop(MTaskService.QUEUE_LORA_RECEIVE + context.PRIORITY.value());
			if (frame != null) {
				frame.setrOutTime(new Date());
				context.fireDecode(frame);			
			}
		}
	}

}
