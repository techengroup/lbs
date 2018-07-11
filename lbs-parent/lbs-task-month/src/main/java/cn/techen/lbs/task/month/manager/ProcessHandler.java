package cn.techen.lbs.task.month.manager;

import java.util.Date;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.db.sql.AbstractSQL;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.task.month.common.Local;
import cn.techen.lbs.task.month.common.MonthContext;

public class ProcessHandler {
	
	public void encode(MonthContext context, Month month)  throws Exception {		
		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(month.getProtocol());
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr(month.getCommaddr()).setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		config.funcs().add(month.getDataId());
		byte[] eventFrame = protocolService.encode(config);
		
		protocolService = context.getProtocolManagerService().getProtocol(month.getModuleprotocol());
		config = new DefaultProtocolConfig();
		config.setCommAddr(month.getRoute()).setDir(DIR.CLIENT).setOperation(OPERATION.TRANSPORT);
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

	public void decode(MonthContext context, ProtocolFrame frame)  throws Exception {
		Month month = context.getMonth();
		
		ProtocolConfig config = null;
		byte[] readBytes = frame.getReadBytes();
		if (readBytes != null && readBytes.length > 8) {
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(month.getModuleprotocol());
			config = protocolService.decode(readBytes);
			byte[] transBytes = (byte[]) config.units().poll();
			
			protocolService = context.getProtocolManagerService()
					.getProtocol(month.getProtocol());
			config = protocolService.decode(transBytes);		
		}
		
		store(context, frame, month, config);
	}
	
	public void exceptionCaught(MonthContext context, Throwable cause) {
		cause.printStackTrace();
    	context.reset();
    }
	
	private void write(MonthContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
		frame.setwInTime(new Date());
		context.setState(State.RECIEVING);
	}
	
	private void store(MonthContext context, ProtocolFrame frame, Month month, ProtocolConfig config)  throws Exception {
		if (config != null) {					
			for (String fn : config.funcs()) {
				String fnKey = config.funcKeys().get(fn);
				if (fnKey != null && !fnKey.isEmpty()) {						
					String className = String.format("Fn%s", fnKey.replace(":", ""));
					AbstractSQL as = GlobalUtil.newSql(className);					
					context.getGeneralService().save(as.handle(month.getId(), config.units()));
				}
			}
		}
		
		context.reset();
	}

}
