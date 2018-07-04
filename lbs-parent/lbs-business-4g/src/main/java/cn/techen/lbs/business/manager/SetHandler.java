package cn.techen.lbs.business.manager;

import java.util.ArrayList;
import java.util.List;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.sql.AbstractSQL;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class SetHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		ProtocolConfig respConnfig = new DefaultProtocolConfig();
		respConnfig.setCommAddr(config.getCommAddr()).setDir(DIR.SERVER)
			.setOperation(OPERATION.CONFIRM).runs().put("PRM", 0);
		respConnfig.runs().putAll(config.runs());
		
		for (String func : config.funcs()) {
			String[] at = func.split(":");
			String[] das = at[0].split(",");
			String[] dts = at[1].split(",");
			
			List<String> result = new ArrayList<String>();
			
			for (String pn : das) {
				for (String fn : dts) {
					String fnKey = config.funcKeys().get(pn + ":" + fn);
					if (fnKey != null && !fnKey.isEmpty()) {						
						String className = String.format("Fn%s", fnKey.replace(":", ""));
						AbstractSQL as = GlobalUtil.newSql(className);
						int r = context.getGeneralService().save(as.handle(pn, config.units()));						
						result.add(String.valueOf((r > 0) ? 1 : 2));
					}
				}
			}
			respConnfig.funcs().add(at[0] + ":" + String.join(",", result.toArray(new String[0])));			
		}
		
		byte[] frame = context.getProtocolManagerService().getProtocol(Global.lbs.getProtocol()).encode(respConnfig);
		
		if (frame != null && frame.length > 0) {
			context.getmTaskService().lpush(MTaskService.UPQUEUE_SEND, frame);			
		}
	}
	
}

