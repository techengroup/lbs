package cn.techen.lbs.business.manager;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.protocol.ProtocolConfig;

public class LogoutHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		context.setLogined(false);
	}
	
}
