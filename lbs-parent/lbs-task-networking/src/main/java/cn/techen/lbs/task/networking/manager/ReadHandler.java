package cn.techen.lbs.task.networking.manager;

import java.util.Date;

import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.task.networking.common.NetContext;
import cn.techen.lbs.protocol.FrameConfig.State;

public class ReadHandler extends AbstractHandler {

	public ReadHandler() {
		super();
	}

	@Override
	public void operate(NetContext context) throws Exception {
		if (State.RECIEVING == context.getState()) {
			ProtocolFrame frame = context.getmTaskService().rpop(MTaskService.QUEUE_RETURN + context.PRIORITY.value());
			if (frame != null) {
				frame.setrOutTime(new Date());
				context.setFrame(frame);
				getHandler().operate(context);				
			}
		}
	}

}
