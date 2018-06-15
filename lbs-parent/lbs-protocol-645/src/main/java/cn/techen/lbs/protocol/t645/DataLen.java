package cn.techen.lbs.protocol.t645;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;

public class DataLen extends AbstractElement {
	
	public DataLen() {
		value = 0;
		len = 1;
		title = "Len";
	}

	public void decode(AbstractFrame frame) throws Exception {
		value = ProtocolUtil.byte2Int(frame.process().queue.poll());
		if(value != (frame.process().queue.size() - 2))	throw new Exception("Unknown length value:" + value);
	}

	public void encode(AbstractFrame frame) throws Exception {
		value = frame.process().vector.size();
		frame.process().vector.add(0, (byte)value);
	}
	
}
