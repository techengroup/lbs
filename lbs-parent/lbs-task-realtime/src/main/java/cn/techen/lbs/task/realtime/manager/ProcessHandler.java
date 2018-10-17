package cn.techen.lbs.task.realtime.manager;

import java.util.Date;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.realtime.common.Local;
import cn.techen.lbs.task.realtime.common.RealTimeContext;

public class ProcessHandler {
	
	public void encode(RealTimeContext context, byte[] bFrame)  throws Exception {	
		String commAddr = "";
		for (int i = 1; i < 7; i++) {		
			commAddr = ProtocolUtil.bcd2Str(bFrame[i]) + commAddr;
		}		
		Node node = context.getmNodeService().get(commAddr);
		
		if (node != null && !node.getRoute().isEmpty()) {
			String route = node.getRoute();
			
			if (route != null && !route.isEmpty()) {
				context.setCommAddr(commAddr);
				
				ProtocolService protocolService = context.getProtocolManagerService().getProtocol(Global.lbs.getModuleprotocol());
				ProtocolConfig config = new DefaultProtocolConfig();
				config.setCommAddr(route).setDir(DIR.CLIENT).setOperation(OPERATION.TRANSPORT);
				config.runs().put("CHANNEL", Global.lbs.getChannel());
				config.funcs().add("6");
				config.units().add(bFrame.length);
				config.units().add(bFrame);	
				byte[] realFrame = protocolService.encode(config);
				
				ProtocolFrame frame = new ProtocolFrame();
				frame.setWriteBytes(realFrame);
				frame.setWriteTimes(Local.WRITETIMES);
				frame.setTimeout(Local.TIMEOUT);
				
				write(context, frame);
			}
		} else {
			context.reset();
		}
	}

	public void decode(RealTimeContext context, ProtocolFrame frame)  throws Exception {	
		byte[] readBytes = frame.getReadBytes();
		if (readBytes != null && readBytes.length > 8) {
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(Global.lbs.getModuleprotocol());
			ProtocolConfig config = protocolService.decode(readBytes);
			byte[] transBytes = (byte[]) config.units().poll();			
			
			if (context.getCommAddr().equals(ProtocolUtil.getCommAddr(config.getCommAddr()))) {				
				protocolService = context.getProtocolManagerService()
						.getProtocol(Global.lbs.getProtocol());
				ProtocolConfig returnConfig = new DefaultProtocolConfig();
				returnConfig.setCommAddr(Global.lbs.getCommaddr()).setDir(DIR.SERVER)
					.setOperation(OPERATION.TRANSPORT).runs().put("PRM", 0);
				returnConfig.runs().putAll(context.getConfig().runs());
				returnConfig.funcs().addAll(context.getConfig().funcs());
				returnConfig.units().add(config.runs().get("RSSI"));
				transBytes = ProtocolUtil.subByteArray(transBytes, 2);
				returnConfig.units().add(transBytes.length);
				returnConfig.units().add(transBytes);
				byte[] returnFrame = context.getProtocolManagerService().getProtocol(Global.lbs.getProtocol()).encode(returnConfig);
				
				context.getmByteService().lpush(MTaskService.QUEUE_4G_SEND, returnFrame);
			}
		}
		
		context.reset();
	}
	
	public void exceptionCaught(RealTimeContext context, Throwable cause) {
		cause.printStackTrace();
    	context.reset();
    }
	
	private void write(RealTimeContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		frame.setwInTime(new Date());
		context.getmTaskService().lpush(MTaskService.QUEUE_LORA_SEND + context.PRIORITY.value(), frame);
		context.setState(State.RECIEVING);
	}

}
