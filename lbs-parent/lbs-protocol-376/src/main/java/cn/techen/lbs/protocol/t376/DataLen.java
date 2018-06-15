package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;

public class DataLen extends AbstractElement {
	
	private byte[] bytes = new byte[4];
	private int length;
	
	public DataLen() {
		value = 0;
		len = 4;
		title = "Len";
	}

	public void decode(AbstractFrame frame) throws Exception {
		bytes[0] = frame.process().queue.poll();
		bytes[1] = frame.process().queue.poll();
		bytes[2] = frame.process().queue.poll();
		bytes[3] = frame.process().queue.poll();
		
		value = ProtocolUtil.byte2Int(bytes[0]);
		value = (ProtocolUtil.byte2Int(bytes[1]) << 8) + value;
		String b = Integer.toBinaryString(value);
		b = b.substring(0, b.length()-2);
		length = Integer.parseInt(b, 2);
		desc = String.valueOf(length);
		if(length != (frame.getBytes().length - 8 ))	throw new Exception("Unknown length value:" + value);
	}

	public void encode(AbstractFrame frame) throws Exception {
		length = frame.process().vector.size() - 1;
		value = length;
		String b = Integer.toBinaryString(value) + "11";
		value = Integer.parseInt(b, 2);
		desc = String.valueOf(length);
		
		bytes[0] = (byte)((value >> 8) & 0xFF);
		bytes[1] = (byte)(value & 0xFF);
		bytes[2] = (byte)((value >> 8) & 0xFF);
		bytes[3] = (byte)(value & 0xFF);
		
		frame.process().vector.add(0, bytes[0]);
		frame.process().vector.add(0, bytes[1]);
		frame.process().vector.add(0, bytes[2]);
		frame.process().vector.add(0, bytes[3]);
	}
	
	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H  %d");
		return String.format(sb.toString()
				, ProtocolUtil.byte2HexString(bytes, true), length);
	}
	
}
