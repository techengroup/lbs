package cn.techen.lbs.lora.mananger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class ReadHandler extends AbstractHandler {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);

	public ReadHandler() {
		super();
	}

	@Override
	public void operate(LoraContext context) throws Exception {
		if (context.getFrame() != null) {
			byte[] readBytes = context.getFrame().getReadBytes();
			if (readBytes != null) {
				logger.info("Read： {}B \r\n{}", readBytes.length, ProtocolUtil.byte2HexString(readBytes, true));
				getHandler().operate(context);
			}
			
			if (context.getFrame().isTimeout()) {
				getHandler().operate(context);
			}
		}
	}
	
}
