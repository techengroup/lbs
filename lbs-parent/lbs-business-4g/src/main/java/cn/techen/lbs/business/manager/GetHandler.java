package cn.techen.lbs.business.manager;

import java.util.List;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.protocol.ProtocolConfig;

public class GetHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		int afn = Integer.parseInt(config.userData().get("AFN").toString());
		List<String> dadts = config.dataId();
		for (String dadt : dadts) {
			String[] at = dadt.split(":");
			String das = at[0];
			String dts = at[1];
			
			System.out.println(afn + das + dts);
		}
		
	}	
	
}
