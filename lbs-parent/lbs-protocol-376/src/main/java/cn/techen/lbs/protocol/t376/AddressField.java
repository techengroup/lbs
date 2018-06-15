package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;

public class AddressField extends AbstractElement {
	
	private byte[] bytes = new byte[5];
	private String addr;	
	private byte flag = 0;
	
	public AddressField() {
		len=5;
		title="Addr";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		int value = 0x00;
		
		bytes[0] = frame.process().queue.poll();
		bytes[1] = frame.process().queue.poll();
		bytes[2] = frame.process().queue.poll();
		bytes[3] = frame.process().queue.poll();
		bytes[4] = frame.process().queue.poll();
		
		addr = ProtocolUtil.bcd2Str(bytes[0]);
		addr += ProtocolUtil.bcd2Str(bytes[1]);
		value = bytes[2];
		value = (value << 8) + bytes[3];
		addr += ProtocolUtil.zeroFill(5, value);
		flag = bytes[4];
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {				
		T376Config t376Config = ((T376Config) frame.config());
		addr = t376Config.getCommAddr();
		addr = ProtocolUtil.padLeft(addr, '0', 9);
		String addr1 = addr.substring(4);
		int val = Integer.parseInt(addr1, 16);
		String addr0 = addr.substring(0, 4);		
		byte[] b = ProtocolUtil.str2Bcd(addr0);
		
		bytes[0] = b[0];
		bytes[1] = b[1];
		bytes[2] = ProtocolUtil.int2Byte(val>>8);
		bytes[3] = ProtocolUtil.int2Byte(val);
		bytes[4] = flag;
		
		frame.process().vector.add(0, bytes[4]);
		frame.process().vector.add(0, bytes[3]);
		frame.process().vector.add(0, bytes[2]);		
		frame.process().vector.add(0, bytes[1]);
		frame.process().vector.add(0, bytes[0]);
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H  %s");
		return String.format(sb.toString()
				, ProtocolUtil.byte2HexString(bytes, true), addr);
	}
}
