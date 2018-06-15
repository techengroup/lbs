package cn.techen.lbs.lora.mananger;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.protocol.ProtocolUtil;

public class ReadHandler extends AbstractHandler {
	private static final Logger logger = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);

	public ReadHandler() {
		super();
	}

	@Override
	public void operate(LoraContext context) throws Exception {
		byte[] readBytes = null;
		if (context.getFrame() != null) {
			readBytes = context.getFrame().getReadBytes();
			if (readBytes != null || timeout(context)) {
				context.setFlag(1);
			}
		}
		if (context.getReportFrame() != null) {
			if (context.getFlag() == 0) {
				context.setFlag(2);
			} else {
				context.setFlag(3);
			}
			readBytes = context.getReportFrame().getReadBytes();
		}
		
		if (context.getFlag() > 0) {
			logger.info("Read:\r\n{}", ProtocolUtil.byte2HexString(readBytes, true));
			getHandler().operate(context);
		}
	}

	private boolean timeout(LoraContext context) {
		Date wriTime = context.getFrame().getWriteTime();
		if (wriTime != null) {
			Date nowTime = new Date();			
			long diff = nowTime.getTime() - wriTime.getTime();
			
			if (diff > context.getTimeout()) {
				return true;
			}
		}
		
		return false;
	}
}
