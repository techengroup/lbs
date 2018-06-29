package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class BIT_STRING extends AbstractData {	
	
	public BIT_STRING(String types) {
		dataTypes = types;
		len = Integer.parseInt(extract(dataTypes));
		bytes = new byte[len];
		format = extract(dataTypes);
		sort = Integer.parseInt(extract(dataTypes));
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
			byteList.add(bytes[i]);
		}
		
		content = ProtocolUtil.byte2BinaryString(bytes, false);
		desc = ProtocolUtil.byte2BinaryString(bytes, true);
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString();
		
		int value = Integer.valueOf(desc, 2);
		String str = ProtocolUtil.int2HexString(value);
		desc = ProtocolUtil.zeroFill(len*2, str);
		
		bytes = ProtocolUtil.hexString2Byte(desc);
		for (int i = 0; i < len; i++) {			
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}

}
