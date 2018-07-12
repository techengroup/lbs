package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class ADDRESS extends AbstractData{

	public ADDRESS(String types) {
		dataTypes = types;
		len = Integer.parseInt(extract(dataTypes));
		bytes = new byte[len];
		format = extract(dataTypes);
		sort = Integer.parseInt(extract(dataTypes));
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		String addr;
		int value = 0x00;	
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
			byteList.add(bytes[i]);
		}
	
		addr = ProtocolUtil.bcd2Str(bytes[1]);
		addr += ProtocolUtil.bcd2Str(bytes[0]);
		value = bytes[3];
		value = (value << 8) + bytes[2];
		addr += ProtocolUtil.zeroFill(4, value);
		content = addr;
        desc = addr;
        frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString();
		
		desc = ProtocolUtil.padLeft(desc, '0', 8);
		String addr1 = desc.substring(4);
		int val = Integer.parseInt(addr1, 16);
		String addr0 = desc.substring(0, 4);		
		byte[] b = ProtocolUtil.str2Bcd(addr0);
		
		bytes[0] = b[1];
		bytes[1] = b[0];
		bytes[2] = ProtocolUtil.int2Byte(val);
		bytes[3] = ProtocolUtil.int2Byte(val>>8);

		for (int i = 0; i < len; i++) {
			frame.process().vector.add(bytes[i]);
		}
	}

}

