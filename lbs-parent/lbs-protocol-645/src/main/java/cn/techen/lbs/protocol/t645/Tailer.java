package cn.techen.lbs.protocol.t645;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;

public class Tailer extends AbstractElement {
	
	public Tailer() {
		value = 0x16;
		len = 1;
		title = "Tail";
	}

	public void decode(AbstractFrame frame) throws Exception {
		int t = frame.process().queue.poll();
		if (value != t) throw new Exception("Unknown tailer value:" + t); 
	}

	public void encode(AbstractFrame frame) throws Exception {
		frame.process().vector.add((byte)value);
	}
	
}
