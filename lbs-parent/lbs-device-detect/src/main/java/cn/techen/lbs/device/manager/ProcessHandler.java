package cn.techen.lbs.device.manager;

import java.util.Date;

import cn.techen.lbs.db.model.Global;
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
		byte[] rBytes = frame.getReadBytes();
		if (rBytes != null) {
			ProtocolConfig config = context.getProtocolManagerService()
					.getProtocol(context.getLbs().getLoraprotocol()).decode(frame.getReadBytes());
			if (config != null) {					
				Object obj = config.dataUnit().get(0);
				if (obj != null) {
					int result  = Integer.parseInt(obj.toString());
					if (result == 1) {
						Global.LoraReady = true;
						context.setState(State.FINISHED);
					} else {
						rewrite(context, frame);
					}
				}				
			} else {
				rewrite(context, frame);
			}
		} else {
			rewrite(context, frame);
		}
	}

	public void encode(DeviceContext context, Integer fn, LBS lbs)  throws Exception {		
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setDir(DIR.CLIENT).setOperation(OPERATION.SET);
		config.dataId().add(String.valueOf(fn));
		if (fn >= 1) config.dataUnit().add(lbs.getModuleaddr());
		if (fn >= 2) config.dataUnit().add(lbs.getLogicaddr());
		if (fn >= 3) config.dataUnit().add(lbs.getChannel());		
		
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
	
	private void rewrite(DeviceContext context, ProtocolFrame frame) throws Exception {
		frame.increaseRetryTimes();
		if (frame.getRetryTimes() < 10) {
			write(context, frame);
		} else {
			context.setState(State.FINISHED);
		}
	}

}