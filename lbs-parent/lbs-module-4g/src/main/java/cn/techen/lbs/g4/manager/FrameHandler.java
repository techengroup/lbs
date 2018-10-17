package cn.techen.lbs.g4.manager;

import cn.techen.lbs.g4.common.G4Context;
import cn.techen.lbs.mm.api.MTaskService;

public class FrameHandler {
	
	public void login(G4Context context) throws Exception {
		byte[] frame = new byte[1];
		frame[0] = 20;
		store(context, frame);
	}
	
	public void heartBeat(G4Context context) throws Exception {
		byte[] frame = new byte[1];
		frame[0] = 21;
		store(context, frame);
	}
	
	public void read(G4Context context, byte[] frame) throws Exception {
		if (context.channel().channel().isActive()) {
			if (frame != null && frame.length > 8) {
				store(context, frame);
			}
		}
	}
	
	public void logout(G4Context context) throws Exception {
		byte[] frame = new byte[1];
		frame[0] = 22;
		store(context, frame);
	}
	
	private void store(G4Context context, byte[] frame) {
		context.getmTaskService().lpush(MTaskService.QUEUE_4G_RECEIVE, frame);
	}
	
}
