package cn.techen.lbs.protocol.t645;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t645.T645Config.Answer;
import cn.techen.lbs.protocol.t645.T645Config.Control;
import cn.techen.lbs.protocol.t645.T645Config.DIR;
import cn.techen.lbs.protocol.t645.T645Config.FollowUp;

public class ControlField extends AbstractElement {
	private int dir;
	private int answer;
	private int follow;		
	private int func;
	
	public ControlField() {
		len = 1;
		title = "Ctrl";
	}

	public void decode(AbstractFrame frame) throws Exception {
		value = Byte.toUnsignedInt(frame.process().queue.poll());		
		dir = (value >> 7) & 0x01;
		answer = (value >> 6) & 0x01;
		follow = (value >> 5) & 0x01;		
		func = value & 0x1F;
		
		T645Config config = ((T645Config) frame.config());
		config.setDir(DIR.valueOf(dir));
		config.setAnswer(Answer.valueOf(answer));
		config.setFollowUp(FollowUp.valueOf(follow));
		config.setControl(Control.valueOf(func));
	}

	public void encode(AbstractFrame frame) throws Exception {
		T645Config config = ((T645Config) frame.config());
		dir = config.getDir().value();
		answer = config.getAnswer().value();
		follow = config.getFollowUp().value();		
		func = config.getControl().value();
		
		value = (dir << 7) + value;
		value = (answer << 6) + value;
		value = (follow << 5) + value;
		value = (func & 0x1F) + value;
		
		frame.process().vector.add(0, (byte)value);
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H  [%s]B\r\n");
		sb.append("%15s%-7s: %-10s: [%d]: %s\r\n");
		sb.append("%15s%-7s: %-10s: [%d]: %s\r\n");
		sb.append("%15s%-7s: %-10s: [%d]: %s\r\n");
		sb.append("%15s%-7s: %-10s: [%d]: %s");
		return String.format(sb.toString()
				, ProtocolUtil.int2HexString(value), ProtocolUtil.int2BinaryString(value, true)		
				, "", "DIR", "bit[7]", dir, DIR.valueOf(dir).descOf()
				, "", "ANSWER", "bit[6]", answer, Answer.valueOf(answer).descOf()
				, "", "FOLLOW", "bit[5]", follow, FollowUp.valueOf(follow).descOf()				
				, "", "CONTROL", "bit[0-4]", func, Control.valueOf(func).descOf());
	}
}
