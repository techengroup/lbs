package cn.techen.lbs.task.report.manager;

import java.util.Date;

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
		if (commAddr != null && !commAddr.equals("")) {
			Meter meter = context.getmMeterService().get(commAddr);
			
			ProtocolFrame eventFrame = encode0(context, reportFrame, meter);
			context.eventQueue().add(eventFrame);
			
			storeReport(context, reportFrame, meter);
		}		
	}
	
	public void decode(ReportContext context, ProtocolFrame frame)  throws Exception {
		ProtocolConfig protocolConfig = decode0(context, frame);
		if (protocolConfig != null) {					
			storeEvent(context, protocolConfig);
			context.setState(State.FINISHED);
		} else {
			frame.increaseRetryTimes();
			int mod = frame.getRetryTimes() % 3;
			if (mod != 0) {
				write(context, frame);
			} else {
				context.eventQueue().add(frame);
				context.setState(State.FINISHED);
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
		cause.printStackTrace();
    	context.setState(State.FINISHED);
    }

	private ProtocolFrame encode0(ReportContext context, ProtocolFrame reportFrame, Meter meter)  throws Exception {		
		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(meter.getProtocol());
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr(meter.getCommaddr()).setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		byte[] frame = protocolService.encode(config);
		
		protocolService = context.getProtocolManagerService().getProtocol(meter.getModuleprotocol());
		config = new DefaultProtocolConfig();
		config.setCommAddr(reportFrame.getCommAddr()).setDir(DIR.CLIENT).setOperation(OPERATION.TRANSPORT);
		config.funcs().add("6");
		config.units().add(frame.length);
		config.units().add(frame);		
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
			byte[] transBytes = (byte[]) protocolConfig.units().poll();
			
			protocolService = context.getProtocolManagerService()
					.getProtocol(meter.getProtocol());
			protocolConfig = protocolService.decode(transBytes);		
		}
		
		return protocolConfig;	
	}
	
	private String getRealAddr(String commAddr) {
		String[] addrs = commAddr.split(",");
		return addrs[addrs.length-1];
	}
	
	private void storeReport(ReportContext context, ProtocolFrame reportFrame, Meter meter)  throws Exception {
		Report report = new Report();
		report.setMeterid(meter.getId());
		report.setCommaddr(meter.getCommaddr());
		report.setRoute(reportFrame.getCommAddr());
		report.setReporttime(reportFrame.getNewTime());
		
		context.getReportService().save(report);
	}
	
	private void storeEvent(ReportContext context, ProtocolConfig protocolConfig)  throws Exception {
		
		
	}

}
