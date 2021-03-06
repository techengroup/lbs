package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class OCT_STRING extends AbstractData {

	public OCT_STRING(String types) {
		dataTypes = types;
		len = Integer.parseInt(extract(dataTypes));
		bytes = new byte[len];
		format = extract(dataTypes);
		sort = Integer.parseInt(extract(dataTypes));
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		int value = 0;
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
			byteList.add(bytes[i]);
		}
		
		if (sort == 0) {
			bytes = ProtocolUtil.switchBytes(bytes);
		}
		
		for (int i = 0; i < len; i++) {
			value = (value << (i * 8)) + (bytes[i] & 0xFF);
		}

		content = value;
		desc = String.valueOf(value);
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString();
		
		if (ProtocolUtil.isAllE(desc)) {
			bytes = ProtocolUtil.hexString2Byte(desc);
		} else {
			String desc0 = Integer.toHexString(Integer.parseInt(desc));
			desc0 = ProtocolUtil.zeroFill(len*2, desc0);	
			bytes = ProtocolUtil.hexString2Byte(desc0);
		}
		
		if (sort == 0) {
			bytes = ProtocolUtil.switchBytes(bytes);
		}
		
		for (int i = 0; i < len; i++) {
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}

}
