package cn.techen.lbs.lora.mananger;

import java.util.Date;

import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;

public class ObtainHandler extends AbstractHandler {

	public ObtainHandler() {
		super();
	}

	@Override
	public void operate(LoraContext context) throws Exception {
		if (context.getFrame() == null) {
			if (hasTask(context)) {
				getHandler().operate(context);
			}
		} 
	}	
	
	private boolean hasTask(LoraContext context) {
		Priority[] ps = Priority.values();
		for (int i = (ps.length - 1); i >= 0; i--) {
			ProtocolFrame frame = context.getmTaskService().rpop(MTaskService.QUEUE_LORA_SEND + ps[i].value());
			if (frame != null) {
				frame.setwOutTime(new Date());
				context.setFrame(frame);
				context.setPriority(ps[i]);
				return true;
			}
		}
		return false;
	}

}
