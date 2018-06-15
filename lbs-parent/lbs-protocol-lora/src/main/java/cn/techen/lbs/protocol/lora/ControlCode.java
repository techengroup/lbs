package cn.techen.lbs.protocol.lora;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.lora.LoraConfig.Control;
import cn.techen.lbs.protocol.lora.LoraConfig.DIR;

public class ControlCode extends AbstractElement {

	public ControlCode() {
		len = 1;
		title = "Ctrl";
	}

	public void decode(AbstractFrame frame) throws Exception {
		value = ProtocolUtil.byte2Int(frame.process().queue.poll());
		int value0 = value & 0x0F;
		desc = Control.valueOf(value0).descOf();
		LoraConfig loraConfig = (LoraConfig) frame.config();
		loraConfig.setControl(Control.valueOf(value0));
	}

	public void encode(AbstractFrame frame) throws Exception {
		LoraConfig loraConfig = (LoraConfig) frame.config();
		int value0 = loraConfig.getControl().value();
		if (value0 == DIR.SERVER.value()) {
			value = value0 | 0x90;
		}
		desc = Control.valueOf(value0).descOf();
		frame.process().vector.add(0, (byte) value);
	}

}
