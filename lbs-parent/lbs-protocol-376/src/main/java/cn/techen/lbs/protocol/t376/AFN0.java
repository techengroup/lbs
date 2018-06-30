package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.t376.T376Config.AFN;

public class AFN0 extends AbstractElement  {
	
	public AFN0() {
		len = 1;
		title = "AFN";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		value = Byte.toUnsignedInt(frame.process().queue.poll());
		desc = AFN.valueOf(value).descOf();
		config.setAfn(AFN.valueOf(value));
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		value = config.getAfn().value();
		desc = AFN.valueOf(value).descOf();
		frame.process().vector.add(0, (byte) value);
	}

}
