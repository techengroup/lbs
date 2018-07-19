package cn.techen.lbs.protocol.t645;

import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.AbstractElement;

public class Header extends AbstractElement {
	
	public Header() {
		value = 0x68;
		len = 1;
		title = "Head";
	}
	
	public void decode(AbstractFrame frame) throws Exception {
		int h = 0;
		for (int i = 0; i < 3; i++) {
			h = frame.process().queue.poll();
			if (h == value) {
				break;
			}
		}
		if (value != h) throw new IllegalArgumentException("Unknown header value:" + h); 
		
	}

	public void encode(AbstractFrame frame) throws Exception {
		frame.process().vector.add(0, (byte)value);
	}
	
}
