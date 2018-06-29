package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class IP_STRING extends AbstractData{

	public IP_STRING(String types) {
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
		String[] strs = new String[len];
		for (int i = 0; i < strs.length; i++) {
			strs[i] = String.valueOf(Byte.toUnsignedInt(bytes[i]));
		}		
		content = String.format(format, (Object[])strs);
		desc = String.valueOf(content);
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString();
		
		String[] ips = desc.split("\\.");
		for (int i = 0; i < ips.length; i++) {
			bytes[i] = ProtocolUtil.int2Byte(Integer.parseInt(ips[i]));
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}

}
