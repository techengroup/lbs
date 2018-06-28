package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class BCD_STRING extends AbstractData {	
	
	public BCD_STRING(String types) {
		dataTypes = types;
		len = Integer.parseInt(extract(dataTypes));
		bytes = new byte[len];
		format = extract(dataTypes);
		sort = Integer.parseInt(extract(dataTypes));
		name = extract(dataTypes);
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
			sb.append(ProtocolUtil.bcd2Str(bytes[i]));
			byteList.add(bytes[i]);
		}
		
		content = sb.toString();
		desc = sb.toString();
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString();
		desc = ProtocolUtil.zeroFill(len*2, desc);
		
		byte[] bytes = ProtocolUtil.str2Bcd(desc);
		for (int i = 0; i < len; i++) {				
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}

}
