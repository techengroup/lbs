package cn.techen.lbs.business.manager;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.sql.AbstractSQL;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class GetHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		ProtocolConfig respConnfig = new DefaultProtocolConfig();
		respConnfig.setCommAddr(config.getCommAddr()).setDir(DIR.SERVER)
			.setOperation(OPERATION.GET).runs().put("PRM", 0);
		respConnfig.runs().putAll(config.runs());	
		respConnfig.funcs().addAll(config.funcs());		
		
		for (String func : config.funcs()) {
			String[] at = func.split(":");
			String[] das = at[0].split(",");
			String[] dts = at[1].split(",");
			
			
			for (String pn : das) {
				for (String fn : dts) {
					String fnKey = config.funcKeys().get(pn + ":" + fn);
					if (fnKey != null && !fnKey.isEmpty()) {						
						String className = String.format("Fn%s", fnKey.replace(":", ""));
						AbstractSQL as = GlobalUtil.newSql(className);						
						respConnfig.units().addAll(context.getGeneralService().query(as.handle(pn, config.units())));
					}
				}
			}		
		}
		
		byte[] frame = context.getProtocolManagerService().getProtocol(Global.lbs.getProtocol()).encode(respConnfig);
		
		if (frame != null && frame.length > 0) {
			context.getmTaskService().lpush(MTaskService.UPQUEUE_SEND, frame);
		}
		
		changeEventIndex(context);
	}
	
	private void changeEventIndex(BusinessContext context) {
		int lastReportEventIndex = Integer.parseInt(Global.RunParams.get("LastReportEventIndex").toString());
		int currentReadEventIndex = Global.CurrentReadEventStart + Global.CurrentReadEventCount;
		
		if (lastReportEventIndex < currentReadEventIndex) {
			Global.RunParams.put("LastReportEventIndex", currentReadEventIndex);
			context.getParamService().updateValue("LastReportEventIndex", String.valueOf(currentReadEventIndex));			
		}
	}	
	
}
