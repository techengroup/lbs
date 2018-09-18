package cn.techen.lbs.task.networking.manager;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.task.networking.common.Local;
import cn.techen.lbs.task.networking.common.NetContext;

public class EncodeHandler extends AbstractHandler {
	
	public EncodeHandler() {
		super();
	}

	@Override
	public void operate(NetContext context) throws Exception {
		if (context.getFrame() == null) {
			Meter meter = context.getMeter();
			
			ProtocolService protocolService = context.getProtocolManagerService().getProtocol(meter.getModuleprotocol());
			ProtocolConfig config = new DefaultProtocolConfig();
			config.setCommAddr(meter.running().getRoute()).setDir(DIR.CLIENT).setOperation(OPERATION.LOGIN);
			config.runs().put("CHANNEL", Global.lbs.getChannel());
			config.funcs().add("2");			
			config.units().add(meter.getId());
			
			byte[] frame = protocolService.encode(config);
			ProtocolFrame pFrame = new ProtocolFrame();
			pFrame.setWriteBytes(frame);
			if (meter.getGrade() > 0) {
				pFrame.setWriteTimes(Local.WRITETIMES_RELAY);
			} else {
				pFrame.setWriteTimes(Local.WRITETIMES);				
			}
			pFrame.setTimeout(Local.TIMEOUT);
			context.setFrame(pFrame);
		}
		
		getHandler().operate(context);
	}
	
}
