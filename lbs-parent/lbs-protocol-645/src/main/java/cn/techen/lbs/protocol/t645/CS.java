package cn.techen.lbs.protocol.t645;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;

public class CS extends AbstractElement {	
	
	public CS() {
		len = 1;
		title = "CS";
	}
	
	public void decode(AbstractFrame frame) throws Exception {
		value = Byte.toUnsignedInt(frame.process().queue.poll());
		byte cs = 0x00;
		byte[] f = frame.getBytes();
		for (int i = 0; i < (f.length - 2); i++) {
			if (i < 2 && f[i] == -2) continue;				
			cs = (byte) ((cs + f[i]) & 0xFF);
		}
		desc = String.valueOf(value);
		if (value != Byte.toUnsignedInt(cs)) throw new IllegalArgumentException("CS is invalid.");
	}

	public void encode(AbstractFrame frame) throws Exception {
		value = 0x00;
		for (Byte b : frame.process().vector) {
			value = (byte) ((value + b) & 0xFF);
		}

		value = Byte.toUnsignedInt((byte) value);
		desc = String.valueOf(value);
		frame.process().vector.add((byte) value);
	}
	
}
