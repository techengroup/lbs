package cn.techen.lbs.business.manager;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class SetHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		ProtocolConfig config0 = new DefaultProtocolConfig();
		config0.setCommAddr(config.getCommAddr()).setDir(DIR.SERVER)
			.setOperation(OPERATION.CONFIRM).userData().put("PRM", 0);
		config0.userData().putAll(config.userData());
		config0.dataId().add("0:1");
		
		byte[] frame = context.getProtocolManagerService().getProtocol(100).encode(config0);
		
		if (frame != null && frame.length > 0) {
			context.getmTaskService().lpush(MTaskService.UPQUEUE_SEND, frame);			
		}
	}	
	
}

