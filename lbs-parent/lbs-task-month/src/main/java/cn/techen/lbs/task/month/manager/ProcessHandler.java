package cn.techen.lbs.task.month.manager;

import java.util.Date;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Month;
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
		
		store(context, frame, config);
	}
	
	public void exceptionCaught(MonthContext context, Throwable cause) {
		cause.printStackTrace();
    	context.reset();
    }
	
	private void write(MonthContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		frame.setwInTime(new Date());
		context.getmTaskService().lpush(MTaskService.QUEUE_SEND + context.PRIORITY.value(), frame);
		context.setState(State.RECIEVING);
	}
	
	private void store(MonthContext context, ProtocolFrame frame, ProtocolConfig config)  throws Exception {
		if (config != null) {
			//==网路状况不好时，可能有串包,处理如下== 冻结和事件的处理方式有点区别 == 
			String commAddr = ProtocolUtil.getCommAddr(config.getCommAddr());
			int nodeId = context.getmNodeService().get(commAddr).getId();
			//==网路状况不好时，可能有串包,处理如下== 冻结和事件的处理方式有点区别 ==
						
			for (String fn : config.funcs()) {
				String fnKey = config.funcKeys().get(fn);
				if (fnKey != null && !fnKey.isEmpty()) {						
					String className = String.format("Fn%s", fnKey.replace(":", ""));
					AbstractSQL as = Global.newSql(className);					
					context.getGeneralService().save(as.handle(nodeId, config.units()));
				}
			}
			
			context.reset();
		} else {
			frame.increaseRetryTimes();
			int mod = frame.getRetryTimes() % 2;
			if (mod != 0) {
				write(context, frame);
			} else {
				context.reset();
			}
		}		
	}

}
