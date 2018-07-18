package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class EC extends AbstractElement  {
	
	private int count;	
	private int start;
	private byte[] bytes;
	
	public EC() {
		len = 4;
		bytes = new byte[len];
		title= "EC";
		desc= "Event Couter";
	}
	
	@Override
	public void decode(AbstractFrame frame) throws Exception {
		
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		count = Integer.parseInt(frame.config().units().poll().toString());
		start = Integer.parseInt(frame.config().units().poll().toString());
		
		byte[] bytes = new byte[len];
		bytes[0] = (byte) (count & 0x0F);
		bytes[1] = (byte) ((count >> 8) & 0x0F);
		bytes[0] = (byte) (count & 0x0F);
		bytes[1] = (byte) ((count >> 8) & 0x0F);
		
		for (int i = 0; i < len; i++) {
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H [%s]\r\n");
		sb.append("%23s| %d | %d |");
		return String.format(sb.toString()
				, ProtocolUtil.byte2HexString(bytes, true), desc	
				, "", count, start);
	}

}
