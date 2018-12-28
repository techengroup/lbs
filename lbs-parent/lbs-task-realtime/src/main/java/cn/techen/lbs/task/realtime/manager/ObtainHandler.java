package cn.techen.lbs.task.realtime.manager;

import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.task.realtime.common.Local;
import cn.techen.lbs.task.realtime.common.RealTimeContext;

import java.util.Date;

public class ObtainHandler extends AbstractHandler {

	@Override
	public void operate(RealTimeContext context) throws Exception {
		if (State.FINISHED == context.getState()) {	
			ProtocolConfig config =  context.getConfig();
			if (config != null) {
				long d = new Date().getTime() - config.newTime().getTime();
				if (d <= Local.TIMEOUTMILLIS) { 			
					Object obj = config.units().poll();
					if (obj != null) {
						if (obj instanceof String) {
							String devAddr = obj.toString();
							context.fireEncode(devAddr);
						} else {
							byte[] frame = (byte[])obj;
							if (frame.length > 12) {
								context.fireEncode(frame);
							} else {
								context.reset();
							}
						}
					} else {
						context.reset();
					}
				} else {
					context.reset();
				}
			}
		}
	}
	
}
