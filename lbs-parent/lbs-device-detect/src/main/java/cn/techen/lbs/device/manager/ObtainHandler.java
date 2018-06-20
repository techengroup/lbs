package cn.techen.lbs.device.manager;

import cn.techen.lbs.device.common.DeviceContext;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.State;

public class ObtainHandler extends AbstractHandler {
	
	public ObtainHandler() {
		super();
	}

	@Override
	public void operate(DeviceContext context) throws Exception {		
		if (State.FINISHED == context.getState()) {			
			ProtocolFrame frame = context.detectQueue().poll();
			if (frame != null) {
				context.fireWrite(frame);
			}
		}		
	}
	
}
