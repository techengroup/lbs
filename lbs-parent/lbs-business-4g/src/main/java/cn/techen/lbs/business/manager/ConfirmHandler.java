package cn.techen.lbs.business.manager;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.protocol.ProtocolConfig;

public class ConfirmHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		if (!context.isLogined()) {
			int flag = Integer.parseInt(config.runs().get("LoginCon").toString());
			if (flag == 1) {
				context.setLogined(true);
			}
		}		
	}	
	
}
