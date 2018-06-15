package cn.techen.lbs.protocol.lora.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.lora.LoraConfig;

public class BCD_STRING extends AbstractData {	
	
	public BCD_STRING(Integer length, String format, Integer asc, String tableColumn) {
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
		
		StringBuffer sb = new StringBuffer();
		sb.append(ProtocolUtil.bcd2Str(bytes[0]));
		sb.append(ProtocolUtil.bcd2Str(bytes[1]));
		
		content = sb.toString();
		desc = sb.toString();
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		LoraConfig config = ((LoraConfig) frame.config());
		content = config.unit().poll();
		desc = content.toString();
		desc = ProtocolUtil.zeroFill(len*2, desc);
		
		byte[] bcds = ProtocolUtil.str2Bcd(desc);
		for (int i = 0; i < len; i++) {			
			frame.process().vector.add(bcds[i]);
		}
	}

}
