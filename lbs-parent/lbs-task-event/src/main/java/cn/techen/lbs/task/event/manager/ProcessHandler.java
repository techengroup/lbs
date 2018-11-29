package cn.techen.lbs.task.event.manager;

import java.util.Date;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Node;
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
		Node node = context.getmNodeService().get(report.getCommaddr());
		
		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(node.getProtocol());
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr(report.getCommaddr()).setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		config.funcs().add("C42A");
		byte[] eventFrame = protocolService.encode(config);
		
		protocolService = context.getProtocolManagerService().getProtocol(node.getModuleprotocol());
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
		Node node = context.getmNodeService().get(context.getReport().getCommaddr());
		
		ProtocolConfig config = null;
		byte[] readBytes = frame.getReadBytes();
		if (readBytes != null && readBytes.length > 8) {
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(node.getModuleprotocol());
			config = protocolService.decode(readBytes);
			byte[] transBytes = (byte[]) config.units().poll();
			transBytes = ProtocolUtil.subByteArray(transBytes, 2);
			
			protocolService = context.getProtocolManagerService()
					.getProtocol(node.getProtocol());
			config = protocolService.decode(transBytes);		
		}
		
		store(context, frame, node, config);
	}
	
	public void exceptionCaught(EventContext context, Throwable cause) {
		cause.printStackTrace();
    	context.reset();
    }
	
	private void write(EventContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		frame.setwInTime(new Date());
		context.getmTaskService().lpush(MTaskService.QUEUE_LORA_SEND + context.PRIORITY.value(), frame);
		context.setState(State.RECIEVING);
	}
	
	private void store(EventContext context, ProtocolFrame frame, Node node, ProtocolConfig config)  throws Exception {
		if (config != null) {	
			//==网路状况不好时，可能有串包,处理如下==
			String commAddr = ProtocolUtil.getCommAddr(config.getCommAddr());
			int nodeId = context.getmNodeService().get(commAddr).getId();
			//==网路状况不好时，可能有串包,处理如下==
			
			int answer = Integer.parseInt(config.runs().get("ANSWER").toString());
			
			if (answer == 0) {//应答成功
				for (String fn : config.funcs()) {
					String fnKey = config.funcKeys().get(fn);
					if (fnKey != null && !fnKey.isEmpty()) {						
						String className = String.format("Fn%s", fnKey.replace(":", ""));
						AbstractSQL as = Global.newSql(className);						
						context.getGeneralService().save(as.handle(nodeId, config.units()));
					}
				}	
				
				//==网路状况不好时，可能有串包,处理如下==
				if (!node.getCommaddr().equals(commAddr)) {
					context.getReportService().updateFail(node.getId(), 0);
				}
				//==网路状况不好时，可能有串包,处理如下==
			} else {//应答异常
				context.getReportService().updateFail(nodeId, 2);
				//==网路状况不好时，可能有串包,处理如下==
				if (!node.getCommaddr().equals(commAddr)) {
					context.getReportService().updateFail(node.getId(), 2);
				}
				//==网路状况不好时，可能有串包,处理如下==
			}

			context.reset();
		} else {
			frame.increaseRetryTimes();
			int mod = frame.getRetryTimes() % 2;
			if (mod != 0) {
				write(context, frame);
			} else {
				context.getReportService().updateFail(node.getId(), 0);
				context.reset();
			}
		}

	}

}
