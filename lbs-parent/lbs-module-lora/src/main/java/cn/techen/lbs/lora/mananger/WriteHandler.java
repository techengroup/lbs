package cn.techen.lbs.lora.mananger;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.lora.common.LoraRxtx;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class WriteHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	public WriteHandler() {
		super();
	}

	@Override
	public void operate(LoraContext context) throws Exception {
		if (context.getFrame() != null) {		
			for (int i = 0; i < context.getFrame().getWriteTimes(); i++) {
				if (context.getFrame().getReadBytes() != null) break;				
				if (i > 0) Thread.sleep(2000);
				
				if (context.getFrame() != null) {
					send(context);
				} else {
					break;
				}
			}
		}
	}
	
	private void send(LoraContext context) throws Exception {
		byte[] writeBytes = context.getFrame().getWriteBytes();
		LoraRxtx.getInstance().write(writeBytes);
		context.getFrame().setWriteTime(new Date());
		logger.info("Write: {}B \r\n{}", writeBytes.length, ProtocolUtil.byte2HexString(writeBytes, true));
	}

}
