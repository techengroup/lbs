package cn.techen.lbs.protocol.lora.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.lora.LoraConfig;

public class OCT_STRING extends AbstractData {

	public OCT_STRING(Integer length, String format, Integer asc, String tableColumn) {
		len = length;
		bytes = new byte[length];
		this.format = format;
		this.asc = asc;
		this.tableColumn = tableColumn;
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		int value = 0;
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
			value = (value << (i * 8)) + (bytes[i] & 0xFF);
		}

		content = String.valueOf(value);
		desc = String.valueOf(value);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		LoraConfig config = ((LoraConfig) frame.config());
		content = config.unit().poll();
		desc = content.toString();
		desc = Integer.toHexString(Integer.parseInt(desc));
		desc = ProtocolUtil.zeroFill(len*2, desc);

		byte[] octs = ProtocolUtil.hexString2Byte(desc);
		for (int i = 0; i < len; i++) {
			frame.process().vector.add(octs[i]);
		}
	}

}
