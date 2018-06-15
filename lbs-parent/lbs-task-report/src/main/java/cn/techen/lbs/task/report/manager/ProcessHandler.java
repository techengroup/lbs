package cn.techen.lbs.task.report.manager;

import java.util.Date;

import cn.techen.lbs.db.model.Event;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.task.report.common.Local;
import cn.techen.lbs.task.report.common.ReportContext;

public class ProcessHandler {
	
	public void report(ReportContext context, ProtocolFrame reportFrame)  throws Exception {
		String commAddr = getRealAddr(reportFrame.getCommAddr());
		ProtocolFrame eventFrame = encode0(context, commAddr);
		context.eventQueue().add(eventFrame);
		
		Report report = new Report();
		store(context, report);	
	}
	
	public void decode(ReportContext context, ProtocolFrame frame)  throws Exception {
		ProtocolConfig protocolConfig = decode0(context, frame);
		if (protocolConfig != null) {					
			Event event = new Event();
			store(context, event);
		} else {
			frame.increaseRetryTimes();
			int mod = frame.getRetryTimes() % 3;
			if (mod != 0) {
				context.eventQueue().add(frame);
			} else {
				context.suspendQueue().add(frame);
			}
		}
	}
	
	public void write(ReportContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
		frame.setwInTime(new Date());
		context.setState(State.RECIEVING);
	}
	
	public void exceptionCaught(ReportContext context, Throwable cause) {
    	context.setState(State.FINISHED);
    }

	private ProtocolFrame encode0(ReportContext context, String commAddr)  throws Exception {	
		Meter meter = context.getmMeterService().get(commAddr);
		
		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(meter.getProtocol());
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr(commAddr).setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		byte[] frame = protocolService.encode(config);
		
		protocolService = context.getProtocolManagerService().getProtocol(meter.getModuleprotocol());
		config = new DefaultProtocolConfig();
		config.setCommAddr(commAddr).setDir(DIR.CLIENT).setOperation(OPERATION.TRANSPORT);
		config.dataUnit().add(frame);		
		frame = protocolService.encode(config);
		
		ProtocolFrame eventFrame = new ProtocolFrame();
		eventFrame.setPriority(context.PRIORITY);
		eventFrame.setWriteBytes(frame);
		eventFrame.setWriteTimes(Local.WRITETIMES);
		
		return eventFrame;	
	}
	
	private ProtocolConfig decode0(ReportContext context, ProtocolFrame frame)  throws Exception {	
		ProtocolConfig protocolConfig = null;
		byte[] readBytes = frame.getReadBytes();
		if (readBytes != null && readBytes.length > 8) {
			Meter meter = context.getmMeterService().get(getRealAddr(frame.getCommAddr()));
			
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(meter.getModuleprotocol());
			protocolConfig = protocolService.decode(readBytes);			
			byte[] transBytes = (byte[]) protocolConfig.dataUnit().get(0);
			
			protocolService = context.getProtocolManagerService()
					.getProtocol(meter.getProtocol());
			protocolConfig = protocolService.decode(transBytes);
			
			Event event = new Event();
			store(context, event);
		}
		
		return protocolConfig;	
	}
	
	private String getRealAddr(String commAddr) {
		String[] addrs = commAddr.split(",");
		return addrs[addrs.length-1];
	}
	
	private void store(ReportContext context, Object entity)  throws Exception {
		if (entity instanceof Report) {
			
		} else if (entity instanceof Event) {
			
		}
		context.setState(State.FINISHED);
	}

}
