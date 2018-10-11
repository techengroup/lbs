package cn.techen.lbs.lora.mananger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.lora.common.LoraRxtx;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class ReadHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	private List<Byte> bList = new ArrayList<Byte>();

	public ReadHandler() {
		super();
	}

	@Override
	public void operate(LoraContext context) throws Exception {		
		byte[] data = LoraRxtx.getInstance().read();
		
		if (data != null && data.length > 0) {			
			logger.debug(ProtocolUtil.byte2HexString(data, true));
			
			bList.addAll(ProtocolUtil.byte2List(data));		
			byte[] frame = ProtocolUtil.list2byte(bList);		
			int valid = context.getProtocolManagerService().getProtocol(Local.PROTOCOL).valid(frame);
			
			if (valid < 2) bList.clear();
			
			if (valid == 1) {			
				logger.info("Read： {}B \r\n{}", frame.length, ProtocolUtil.byte2HexString(frame, true));
				
				ProtocolConfig config = context.getProtocolManagerService().getProtocol(Local.PROTOCOL).decode(frame);
				if (config.getOperation() == OPERATION.REPORT) {
					ProtocolFrame reportFrame = new ProtocolFrame();
					reportFrame.setCommAddr(config.getCommAddr());
					reportFrame.setReadTime(new Date());
					reportFrame.setReadBytes(frame);
					reportFrame.setrInTime(new Date());				
					
					ProtocolConfig config0 = new DefaultProtocolConfig();
					config0.setCommAddr(config.getCommAddr()).setDir(DIR.CLIENT).setOperation(OPERATION.REPORT);
					byte[] frame0 = context.getProtocolManagerService().getProtocol(Local.PROTOCOL).encode(config0);
					reportFrame.setWriteBytes(frame0);
					
					LoraRxtx.getInstance().write(frame0);
					reportFrame.setWriteTime(new Date());
					
					context.getmTaskService().lpush(MTaskService.QUEUE_RETURN + Priority.REPORT.value(), reportFrame);
				} else {
					if (context.getFrame() != null) {	
						context.getFrame().setReadTime(new Date());
						context.getFrame().setReadBytes(frame);	
						getHandler().operate(context);
					}
					
					if (context.getFrame().isTimeout()) {
						getHandler().operate(context);
					}
				}
			}
		}		
	}
	
}
