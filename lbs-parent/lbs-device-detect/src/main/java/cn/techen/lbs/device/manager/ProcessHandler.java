package cn.techen.lbs.device.manager;

import java.util.Date;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.device.common.DeviceContext;
import cn.techen.lbs.device.common.Local;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class ProcessHandler {
	
	public void decode(DeviceContext context, ProtocolFrame frame)  throws Exception {
		byte[] rBytes = frame.getReadBytes();
		if (rBytes != null) {
			ProtocolConfig config = context.getProtocolManagerService()
					.getProtocol(Global.lbs.getModuleprotocol()).decode(frame.getReadBytes());
			if (config != null) {					
				Object obj = config.units().poll();
				if (obj != null) {
					int result  = Integer.parseInt(obj.toString());
					if (result == 1) {
						Global.LoraReady = true;
					}
				}				
			}
		}
		
		context.setState(State.FINISHED);
	}

	public void encode(DeviceContext context)  throws Exception {		
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setDir(DIR.CLIENT).setOperation(OPERATION.SET);
		config.funcs().add(String.valueOf(3));
		config.units().add(Global.lbs.getChannel());	
		
		byte[] frame = context.getProtocolManagerService().getProtocol(Global.lbs.getModuleprotocol()).encode(config);
		ProtocolFrame pFrame = new ProtocolFrame();
		pFrame.setWriteBytes(frame);
		pFrame.setWriteTimes(Local.WRITETIMES);
		
		context.detectQueue().add(pFrame);
	}
	
	public void write(DeviceContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		context.getmTaskService().lpush(MTaskService.QUEUE_LORA_SEND + context.PRIORITY.value(), frame);
		frame.setwInTime(new Date());
		context.setState(State.RECIEVING);
	}
	
	public void exceptionCaught(DeviceContext context, Throwable cause) {
		cause.printStackTrace();
    	context.setState(State.FINISHED);
    }

}
