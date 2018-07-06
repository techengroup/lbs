package cn.techen.lbs.task.event.manager;

import java.util.Date;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.task.event.common.EventContext;
import cn.techen.lbs.task.event.common.Local;

public class ProcessHandler {
	
	public void encode(EventContext context, Report report)  throws Exception {		
		Meter meter = context.getmMeterService().get(report.getCommaddr());
		
		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(meter.getProtocol());
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr(report.getCommaddr()).setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		config.funcs().add("C42A");
		byte[] eventFrame = protocolService.encode(config);
		
		protocolService = context.getProtocolManagerService().getProtocol(meter.getModuleprotocol());
		config = new DefaultProtocolConfig();
		config.setCommAddr(report.getRoute()).setDir(DIR.CLIENT).setOperation(OPERATION.TRANSPORT);
		config.funcs().add("6");
		config.units().add(eventFrame.length);
		config.units().add(eventFrame);	
		eventFrame = protocolService.encode(config);
		
		ProtocolFrame frame = new ProtocolFrame();
		frame.setPriority(context.PRIORITY);
		frame.setWriteBytes(eventFrame);
		frame.setWriteTimes(Local.WRITETIMES);
		
		write(context, frame);
	}

	public void decode(EventContext context, ProtocolFrame frame)  throws Exception {
		ProtocolConfig protocolConfig = null;
		byte[] readBytes = frame.getReadBytes();
		if (readBytes != null && readBytes.length > 8) {
			Meter meter = context.getmMeterService().get(context.getReport().getCommaddr());
			
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(meter.getModuleprotocol());
			protocolConfig = protocolService.decode(readBytes);
			byte[] transBytes = (byte[]) protocolConfig.units().poll();
			
			protocolService = context.getProtocolManagerService()
					.getProtocol(meter.getProtocol());
			protocolConfig = protocolService.decode(transBytes);		
		}
		
		if (protocolConfig != null) {					
			store(context, protocolConfig);
		} else {
			frame.increaseRetryTimes();
			int mod = frame.getRetryTimes() % 3;
			if (mod != 0) {
				write(context, frame);
			} else {
//				context.eventQueue().add(frame);
				context.setState(State.FINISHED);
			}
		}
	}
	
	public void exceptionCaught(EventContext context, Throwable cause) {
		cause.printStackTrace();
    	context.setState(State.FINISHED);
    }
	
	private void write(EventContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
		frame.setwInTime(new Date());
		context.setState(State.RECIEVING);
	}
	
	private void store(EventContext context, ProtocolConfig protocolConfig)  throws Exception {
		if (protocolConfig != null) {
			
		}

		context.setState(State.FINISHED);
	}

}
