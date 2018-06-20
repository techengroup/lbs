package cn.techen.lbs.device.manager;

import java.util.Date;

import cn.techen.lbs.db.model.LBS;
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
		ProtocolConfig config = context.getProtocolManagerService()
				.getProtocol(context.getLbs().getLoraprotocol()).decode(frame.getReadBytes());
		if (config != null) {					
//			Object obj = config.dataUnit().get(0);
			
			context.setState(State.FINISHED);
		} else {
			frame.increaseRetryTimes();
			int mod = frame.getRetryTimes() % 3;
			if (mod != 0) {
				write(context, frame);
			} else {
				context.setState(State.FINISHED);
			}
		}
	}

	public void encode(DeviceContext context, LBS lbs)  throws Exception {		
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setDir(DIR.CLIENT).setOperation(OPERATION.SET);
		config.dataId().add("4");
		config.dataUnit().add(lbs.getModuleaddr());
		config.dataUnit().add(lbs.getLogicaddr());
		config.dataUnit().add(lbs.getChannel());		
		
		byte[] frame = context.getProtocolManagerService().getProtocol(lbs.getLoraprotocol()).encode(config);
		ProtocolFrame pFrame = new ProtocolFrame();
		pFrame.setPriority(context.PRIORITY);
		pFrame.setWriteBytes(frame);
		pFrame.setWriteTimes(Local.WRITETIMES);
		
		context.detectQueue().add(pFrame);
	}
	
	public void write(DeviceContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
		frame.setwInTime(new Date());
		context.setState(State.RECIEVING);
	}
	
	public void exceptionCaught(DeviceContext context, Throwable cause) {
		cause.printStackTrace();
    	context.setState(State.FINISHED);
    }

}
