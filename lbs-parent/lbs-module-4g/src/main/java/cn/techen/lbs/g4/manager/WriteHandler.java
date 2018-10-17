package cn.techen.lbs.g4.manager;

import cn.techen.lbs.g4.common.G4Context;
import cn.techen.lbs.g4.common.G4Util;
import cn.techen.lbs.mm.api.MTaskService;

public class WriteHandler {
		
	public void operate(G4Context context) throws Exception {
		if (context.channel() != null && context.channel().channel().isActive()) {
			obtain(context);
		}
	}	
	
	private void obtain(G4Context context) {
		byte[] data = context.getmTaskService().rpop(MTaskService.QUEUE_4G_SEND);
		if (data != null && data.length > 0) {
			context.channel().writeAndFlush(G4Util.byteToBuf(data));
		}
	}
	
}
