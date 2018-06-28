package cn.techen.lbs.business.manager;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class LoginHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, LBS lbs, ProtocolConfig config) throws Exception {
		config = new DefaultProtocolConfig();
		config.setCommAddr(lbs.getCommaddr()).setDir(DIR.SERVER)
			.setOperation(OPERATION.LOGIN).runs().put("PRM", 1);
		byte[] frame = context.getProtocolManagerService().getProtocol(lbs.getProtocol()).encode(config);
		
		if (frame != null && frame.length > 0) {
			context.getmTaskService().lpush(MTaskService.UPQUEUE_SEND, frame);			
		}
	}
	
}
