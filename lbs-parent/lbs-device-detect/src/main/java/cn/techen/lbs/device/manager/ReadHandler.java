package cn.techen.lbs.device.manager;

import java.util.Date;

import cn.techen.lbs.device.common.DeviceContext;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.State;

public class ReadHandler extends AbstractHandler {

	public ReadHandler() {
		super();
	}

	@Override
	public void operate(DeviceContext context) throws Exception {
		if (State.RECIEVING == context.getState()) {
			ProtocolFrame frame = context.getmTaskService().rpop(MTaskService.QUEUE_RETURN + context.PRIORITY.value());
			if (frame != null) {
				frame.setrOutTime(new Date());
				context.fireDecode(frame);			
			}
		}
	}

}
