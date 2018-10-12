package cn.techen.lbs.task.event.manager;

import java.util.Date;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.db.sql.AbstractSQL;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.ProtocolUtil;
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
		config.runs().put("CHANNEL", Global.lbs.getChannel());
		config.funcs().add("6");
		config.units().add(eventFrame.length);
		config.units().add(eventFrame);	
		eventFrame = protocolService.encode(config);
		
		ProtocolFrame frame = new ProtocolFrame();
		frame.setWriteBytes(eventFrame);
		frame.setWriteTimes(Local.WRITETIMES);
		frame.setTimeout(Local.TIMEOUT);
		
		write(context, frame);
	}

	public void decode(EventContext context, ProtocolFrame frame)  throws Exception {
		Meter meter = context.getmMeterService().get(context.getReport().getCommaddr());
		
		ProtocolConfig config = null;
		byte[] readBytes = frame.getReadBytes();
		if (readBytes != null && readBytes.length > 8) {
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(meter.getModuleprotocol());
			config = protocolService.decode(readBytes);
			byte[] transBytes = (byte[]) config.units().poll();
			
			protocolService = context.getProtocolManagerService()
					.getProtocol(meter.getProtocol());
			config = protocolService.decode(transBytes);		
		}
		
		store(context, frame, meter, config);
	}
	
	public void exceptionCaught(EventContext context, Throwable cause) {
		cause.printStackTrace();
    	context.reset();
    }
	
	private void write(EventContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		frame.setwInTime(new Date());
		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
		context.setState(State.RECIEVING);
	}
	
	private void store(EventContext context, ProtocolFrame frame, Meter meter, ProtocolConfig config)  throws Exception {
		if (config != null) {	
			//==网路状况不好时，可能有串包,处理如下==
			String commAddr = ProtocolUtil.getCommAddr(config.getCommAddr());
			int meterId = context.getmMeterService().get(commAddr).getId();
			//==网路状况不好时，可能有串包,处理如下==
			
			for (String fn : config.funcs()) {
				String fnKey = config.funcKeys().get(fn);
				if (fnKey != null && !fnKey.isEmpty()) {						
					String className = String.format("Fn%s", fnKey.replace(":", ""));
					AbstractSQL as = Global.newSql(className);						
					context.getGeneralService().save(as.handle(meterId, config.units()));
				}
			}
			
			//==网路状况不好时，可能有串包,处理如下==
			if (!meter.getCommaddr().equals(commAddr)) {
				context.getReportService().updateFail(meter.getId());
			}
			//==网路状况不好时，可能有串包,处理如下==

			context.reset();
		} else {
			frame.increaseRetryTimes();
			int mod = frame.getRetryTimes() % 3;
			if (mod != 0) {
				write(context, frame);
			} else {
				context.getReportService().updateFail(meter.getId());
				context.reset();
			}
		}

	}

}
