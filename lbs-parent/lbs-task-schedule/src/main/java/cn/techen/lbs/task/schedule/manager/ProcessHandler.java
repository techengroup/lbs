package cn.techen.lbs.task.schedule.manager;

public class ProcessHandler {
	
//	public void report(ScheduleContext context, ProtocolFrame reportFrame)  throws Exception {
//		String commAddr = getRealAddr(reportFrame.getCommAddr());	
//		if (commAddr != null && !commAddr.equals("")) {
//			Meter meter = context.getmMeterService().get(commAddr);
//			response(context, meter);
//			
//			
//			ProtocolFrame eventFrame = encode0(context, reportFrame, meter);
//			context.eventQueue().add(eventFrame);
//			
//			storeReport(context, reportFrame, meter);
//		}		
//	}
//	
//	public void response(ScheduleContext context, Meter meter) throws Exception {
//		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(meter.getModuleprotocol());
//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr(meter.getCommaddr()).setDir(DIR.CLIENT).setOperation(OPERATION.REPORT);
//		byte[] frame = protocolService.encode(config);
//		
//		ProtocolFrame reportFrame = new ProtocolFrame();
//		reportFrame.setPriority(context.PRIORITY);
//		reportFrame.setWriteBytes(frame);
//		reportFrame.setWriteTimes(Local.WRITETIMES);
//		
//		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), reportFrame);
//		reportFrame.setwInTime(new Date());
//	}
//	
//	public void decode(ScheduleContext context, ProtocolFrame frame)  throws Exception {
//		ProtocolConfig protocolConfig = decode0(context, frame);
//		if (protocolConfig != null) {					
//			storeEvent(context, protocolConfig);
//			context.setState(State.FINISHED);
//		} else {
//			frame.increaseRetryTimes();
//			int mod = frame.getRetryTimes() % 3;
//			if (mod != 0) {
//				write(context, frame);
//			} else {
//				context.eventQueue().add(frame);
//				context.setState(State.FINISHED);
//			}
//		}
//	}
//	
//	public void write(ScheduleContext context, ProtocolFrame frame)  throws Exception {
//		context.setState(State.SENDING);
//		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
//		frame.setwInTime(new Date());
//		context.setState(State.RECIEVING);
//	}
//	
//	public void exceptionCaught(ScheduleContext context, Throwable cause) {
//		cause.printStackTrace();
//    	context.setState(State.FINISHED);
//    }
//
//	private ProtocolFrame encode0(ScheduleContext context, ProtocolFrame reportFrame, Meter meter)  throws Exception {		
//		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(meter.getProtocol());
//		ProtocolConfig config = new DefaultProtocolConfig();
//		config.setCommAddr(meter.getCommaddr()).setDir(DIR.CLIENT).setOperation(OPERATION.GET);
//		byte[] frame = protocolService.encode(config);
//		
//		protocolService = context.getProtocolManagerService().getProtocol(meter.getModuleprotocol());
//		config = new DefaultProtocolConfig();
//		config.setCommAddr(reportFrame.getCommAddr()).setDir(DIR.CLIENT).setOperation(OPERATION.TRANSPORT);
//		config.funcs().add("6");
//		config.units().add(frame.length);
//		config.units().add(frame);		
//		frame = protocolService.encode(config);
//		
//		ProtocolFrame eventFrame = new ProtocolFrame();
//		eventFrame.setPriority(context.PRIORITY);
//		eventFrame.setWriteBytes(frame);
//		eventFrame.setWriteTimes(Local.WRITETIMES);
//		
//		return eventFrame;	
//	}
//	
//	private ProtocolConfig decode0(ScheduleContext context, ProtocolFrame frame)  throws Exception {	
//		ProtocolConfig protocolConfig = null;
//		byte[] readBytes = frame.getReadBytes();
//		if (readBytes != null && readBytes.length > 8) {
//			Meter meter = context.getmMeterService().get(getRealAddr(frame.getCommAddr()));
//			
//			ProtocolService protocolService = context.getProtocolManagerService()
//					.getProtocol(meter.getModuleprotocol());
//			protocolConfig = protocolService.decode(readBytes);
//			byte[] transBytes = (byte[]) protocolConfig.units().poll();
//			
//			protocolService = context.getProtocolManagerService()
//					.getProtocol(meter.getProtocol());
//			protocolConfig = protocolService.decode(transBytes);		
//		}
//		
//		return protocolConfig;	
//	}
//	
//	private String getRealAddr(String commAddr) {
//		String[] addrs = commAddr.split(",");
//		return addrs[addrs.length-1];
//	}
//	
//	private void storeReport(ScheduleContext context, ProtocolFrame reportFrame, Meter meter)  throws Exception {
//		Report report = new Report();
//		report.setMeterid(meter.getId());
//		report.setCommaddr(meter.getCommaddr());
//		report.setRoute(reportFrame.getCommAddr());
//		report.setReporttime(reportFrame.getNewTime());
//		
//		context.getReportService().save(report);
//	}
//	
//	private void storeEvent(ScheduleContext context, ProtocolConfig protocolConfig)  throws Exception {
//		
//		
//	}

}
