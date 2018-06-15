package cn.techen.lbs.protocol.lora;

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
		desc = String.valueOf(value);
		if(value != (frame.getBytes().length - 4 ))	throw new Exception("Unknown length value:" + value);
	}

	public void encode(AbstractFrame frame) throws Exception {
		value = frame.process().vector.size();
		desc = String.valueOf(value);
		frame.process().vector.add(0, (byte)value);
	}
	
}
