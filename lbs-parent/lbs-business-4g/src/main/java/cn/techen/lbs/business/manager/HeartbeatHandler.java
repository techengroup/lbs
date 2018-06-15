package cn.techen.lbs.business.manager;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class HeartbeatHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		OPERATION op = OPERATION.HEARTBEAT;
		if (!context.isLogined()) {
			op = OPERATION.LOGIN;
		}
		config = new DefaultProtocolConfig();
		config.setCommAddr(context.getmLbsService().get().getCommaddr()).setDir(DIR.SERVER)
			.setOperation(op).userData().put("PRM", 1);
		byte[] frame = context.getProtocolManagerService().getProtocol(100).encode(config);
		
		if (frame != null && frame.length > 0) {
			context.getmTaskService().lpush(MTaskService.UPQUEUE_SEND, frame);
		}
	}	
	
}
