package cn.techen.lbs.business.manager;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.protocol.ProtocolConfig;

public abstract class AbstractHandler {
	
	public abstract void operate(BusinessContext context, LBS lbs, ProtocolConfig config) throws Exception;
	
}
