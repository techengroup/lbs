package cn.techen.lbs.task.networking.manager;

import java.util.Date;

import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.task.networking.common.NetContext;
import cn.techen.lbs.protocol.FrameConfig.State;

public class WriteHandler extends AbstractHandler {
	
	public WriteHandler() {
		super();
	}

	@Override
	public void operate(NetContext context) throws Exception {
		context.setState(State.SENDING);
		ProtocolFrame frame = context.getFrame();
		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
		frame.setwInTime(new Date());
		context.setState(State.RECIEVING);
	}
	
}
