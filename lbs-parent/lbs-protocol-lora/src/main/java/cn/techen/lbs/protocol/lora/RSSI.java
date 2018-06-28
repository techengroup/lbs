package cn.techen.lbs.protocol.lora;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class RSSI extends AbstractElement {

	public RSSI() {
		len = 1;
		title = "RSSI";
	}

	public void decode(AbstractFrame frame) throws Exception {
		value = ProtocolUtil.byte2Int(frame.process().queue.poll());
		desc = String.valueOf(value);
		LoraConfig loraConfig = (LoraConfig) frame.config();
		loraConfig.setRSSI(value);
		loraConfig.runs().put(title, value);
	}

	public void encode(AbstractFrame frame) throws Exception {
		LoraConfig loraConfig = (LoraConfig) frame.config();
		if (loraConfig.getRSSI() == null) 
			value = 0;
		else 
			value = loraConfig.getRSSI();
		desc = String.valueOf(value);
		frame.process().vector.add(0, (byte) value);
	}

}
