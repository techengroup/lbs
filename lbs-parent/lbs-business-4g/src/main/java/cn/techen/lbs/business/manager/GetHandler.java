package cn.techen.lbs.business.manager;

import java.util.List;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.common.Func;

public class GetHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, LBS lbs, ProtocolConfig config) throws Exception {
		int afn = Integer.parseInt(config.userData().get("AFN").toString());
		int dir = config.getDir().value();
		
		List<String> dadts = config.dataId();
		for (String dadt : dadts) {
			String[] at = dadt.split(":");
			String[] das = at[0].split(",");
			String[] dts = at[1].split(",");
			
			for (String da : das) {
				for (String dt : dts) {
					String code = lbs.getProtocol() + ":" + afn + ":" + dt + ":" + dir;
					String sqlTemplate = Func.getInstance().get(code);
				}
			}
			
		}
		
	}	
	
}
