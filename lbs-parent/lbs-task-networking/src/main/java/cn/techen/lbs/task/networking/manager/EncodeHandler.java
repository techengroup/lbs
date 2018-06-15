package cn.techen.lbs.task.networking.manager;

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
			config.setCommAddr(meter.running().getRoute()).setDir(DIR.CLIENT).setOperation(OPERATION.NET);
			config.dataId().add("2");
			config.dataUnit().add(meter.getLogicaddr());
			
			byte[] frame = protocolService.encode(config);
			ProtocolFrame pFrame = new ProtocolFrame();
			pFrame.setPriority(context.PRIORITY);
			pFrame.setWriteBytes(frame);
			pFrame.setWriteTimes(Local.WRITETIMES);
			context.setFrame(pFrame);
		}
		
		getHandler().operate(context);
	}
	
}
