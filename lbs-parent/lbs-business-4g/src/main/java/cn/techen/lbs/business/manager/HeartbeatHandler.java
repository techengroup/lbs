package cn.techen.lbs.business.manager;

import java.util.Date;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class HeartbeatHandler extends AbstractHandler {

	@Override
	public void operate(BusinessContext context, ProtocolConfig config) throws Exception {
		config = new DefaultProtocolConfig();		
		OPERATION op = null;
		
		if (!context.isLogined()) {
			op = OPERATION.LOGIN;
		} else {
			op = OPERATION.HEARTBEAT;
			config.units().add(new Date());
			
			int start = Integer.parseInt(Global.RunParams.get("LastReportEventIndex").toString());
			int count = context.getGeneralService().selectEventCount();
			
			int rank = count / 65536;
			if (rank > 0) {
				Global.EventRecoderOverAmount = count % 65536;
			} else if (rank > 1) {
				context.getGeneralService().deleteEventRank0();
			}
			
			int rest = count % 65536;
			int diff = rest - start;
			int amount = diff;
			if (diff < 0) {
				amount = 65536 + diff;
			}
			if (amount > 20) amount = 20;
			
			if (amount > 0) {
				config.runs().put("ACD", 1);
				config.units().add(amount);
				config.units().add(start);
			}
		}
		
		config.setCommAddr(Global.lbs.getCommaddr()).setDir(DIR.SERVER)
			.setOperation(op).runs().put("PRM", 1);
		byte[] frame = context.getProtocolManagerService().getProtocol(Global.lbs.getProtocol()).encode(config);
		
		if (frame != null && frame.length > 0) {
			context.getmTaskService().lpush(MTaskService.UPQUEUE_SEND, frame);
		}
	}	
	
}
