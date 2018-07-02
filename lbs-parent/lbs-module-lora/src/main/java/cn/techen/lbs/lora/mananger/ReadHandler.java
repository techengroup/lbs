package cn.techen.lbs.lora.mananger;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class ReadHandler extends AbstractHandler {
	private static final Logger logger = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);

	public ReadHandler() {
		super();
	}

	@Override
	public void operate(LoraContext context) throws Exception {
		if (context.getFrame() != null) {
			byte[] readBytes = context.getFrame().getReadBytes();
			if (readBytes != null || timeout(context)) {
				if (readBytes != null) {
					logger.info("\r\nRead {}B {}", readBytes.length, ProtocolUtil.byte2HexString(readBytes, true));
				}
				getHandler().operate(context);
			}
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
