package cn.techen.lbs.lora.mananger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.techen.lbs.channel.rxtx.RxtxChannel;
import cn.techen.lbs.channel.rxtx.RxtxChannelHandler;
import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;

public class LoraChannelHandler implements RxtxChannelHandler {
	
	private LoraContext context;
	private List<Byte> bList = new ArrayList<Byte>();
	
	public LoraChannelHandler(LoraContext context) {
		this.context = context;
	}

	@Override
	public void channelRead(RxtxChannel channel, byte[] data) throws Exception {
		System.out.println("===============================================");
		System.out.println(ProtocolUtil.byte2HexString(data, true));
		System.out.println("===============================================");
		bList.addAll(ProtocolUtil.byte2List(data));		
		byte[] frame = ProtocolUtil.list2byte(bList);		
		int valid = context.getProtocolManagerService().getProtocol(Local.PROTOCOL).valid(frame);
		
		if (valid < 2) bList.clear();
		
		if (valid == 1) {			
			ProtocolConfig config = context.getProtocolManagerService().getProtocol(Local.PROTOCOL).decode(frame);
			if (config.getOperation() == OPERATION.REPORT) {
				ProtocolFrame reportFrame = new ProtocolFrame();
				reportFrame.setPriority(Priority.REPORT);
				reportFrame.setCommAddr(config.getCommAddr());
				reportFrame.setReadTime(new Date());
				reportFrame.setReadBytes(frame);
				reportFrame.setrInTime(new Date());				
				
				ProtocolConfig config0 = new DefaultProtocolConfig();
				config0.setCommAddr(config.getCommAddr()).setDir(DIR.CLIENT).setOperation(OPERATION.REPORT);
				byte[] frame0 = context.getProtocolManagerService().getProtocol(Local.PROTOCOL).encode(config0);
				reportFrame.setWriteBytes(frame0);
				
				channel.write(frame0);
				reportFrame.setWriteTime(new Date());
				
				context.getmTaskService().lpush(MTaskService.QUEUE_RETURN + Priority.REPORT.value(), reportFrame);
			} else {
				if (context.getFrame() != null) {	
					context.getFrame().setReadTime(new Date());
					context.getFrame().setReadBytes(frame);	
				}
			}
		}
	}

	@Override
	public void channelInactive(RxtxChannel channel) throws Exception {
		channel.disconnect();
	}

	@Override
	public void exceptionCaught(RxtxChannel channel, Throwable cause) throws Exception {
		channel.disconnect();
	}

}
