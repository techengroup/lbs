package cn.techen.lbs.task.networking.manager;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.task.networking.common.Local;
import cn.techen.lbs.task.networking.common.NetContext;

public class DecodeHandler extends AbstractHandler {

	public DecodeHandler() {
		super();
	}

	@Override
	public void operate(NetContext context) throws Exception {
		byte[] readBytes = context.getFrame().getReadBytes();
		Meter meter = context.getMeter();
		if (readBytes != null && readBytes.length > 8) {					
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(context.getMeter().getModuleprotocol());
			ProtocolConfig protocolConfig = protocolService.decode(readBytes);
			int rssi = Integer.parseInt(protocolConfig.runs().get("RSSI").toString());	
			meter.setSignal(rssi);
			
			if (Local.SFACTOR <= rssi) {				
				meter.running().success();
			} else {
				meter.running().fail();
			}						
		} else {
			meter.running().fail();
		}	
		
		getHandler().operate(context);
	}

}
