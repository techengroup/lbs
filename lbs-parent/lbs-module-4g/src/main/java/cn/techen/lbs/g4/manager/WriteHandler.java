package cn.techen.lbs.g4.manager;

import cn.techen.lbs.g4.common.G4Context;
import cn.techen.lbs.g4.common.G4Util;
import cn.techen.lbs.g4.common.Status;
import cn.techen.lbs.mm.api.MTaskService;

public class WriteHandler {
		
	public void operate(G4Context context) throws Exception {
		if (context.getStatus() == Status.CONNECT) {
			obtain(context);
		}
	}	
	
	private void obtain(G4Context context) {
		byte[] data = context.getmTaskService().rpop(MTaskService.UPQUEUE_SEND);
		if (data != null && data.length > 0) {
			context.channel().writeAndFlush(G4Util.byteToBuf(data));
		}
	}
	
}
