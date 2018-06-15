package cn.techen.lbs.protocol.lora.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.lora.LoraConfig;

public class BIT_STRING extends AbstractData {	
	
	public BIT_STRING(Integer length, String format, Integer asc, String tableColumn) {
		len = length;
		bytes = new byte[length];	
		this.format = format;
		this.asc = asc;
		this.tableColumn = tableColumn;
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
		}
		
		content = ProtocolUtil.byte2BinaryString(bytes, false);
		desc = ProtocolUtil.byte2BinaryString(bytes, true);;
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		LoraConfig config = ((LoraConfig) frame.config());
		content = config.unit().poll();
		desc = content.toString();
		
		int value = Integer.valueOf(desc, 2);
		String str = ProtocolUtil.int2HexString(value);
		desc = ProtocolUtil.zeroFill(len*2, str);
		
		byte[] bits = ProtocolUtil.hexString2Byte(desc);
		for (int i = 0; i < len; i++) {			
			frame.process().vector.add(bits[i]);
		}
	}

}
