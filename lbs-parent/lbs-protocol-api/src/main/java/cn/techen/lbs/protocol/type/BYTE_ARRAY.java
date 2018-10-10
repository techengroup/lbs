package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class BYTE_ARRAY extends AbstractData {	
	
	private int byteLen = 0;
	
	public BYTE_ARRAY(String types) {
		dataTypes = types;
		byteLen = Integer.parseInt(extract(dataTypes));
		format = extract(dataTypes);
		sort = Integer.parseInt(extract(dataTypes));
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		for (int i = 0; i < byteLen; i++) {
			byte b = frame.process().queue.poll();
			len += (b & 0xff) << (8 * i);
			byteList.add(b);			
		}
		
		bytes = new byte[len];
		
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
			byteList.add(bytes[i]);
		}
		
		content = bytes;
		desc = ProtocolUtil.byte2HexString(bytes, true);
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		len = Integer.parseInt(frame.config().units().poll().toString());
		content = frame.config().units().poll();
		bytes = (byte[])content;
		desc = ProtocolUtil.byte2HexString(bytes, true);
		
		for (int i = 0; i < byteLen; i++) {
			byte b = (byte) ((len << (8 * i)) & 0xff);
			frame.process().vector.add(b);
			byteList.add(b);
		}
		
		for (Byte b : bytes) {
			frame.process().vector.add(b);
			byteList.add(b);
		}
	}

}
