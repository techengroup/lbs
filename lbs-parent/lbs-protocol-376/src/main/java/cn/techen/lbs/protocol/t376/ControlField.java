package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t376.T376Config.ACD;
import cn.techen.lbs.protocol.t376.T376Config.DIR;
import cn.techen.lbs.protocol.t376.T376Config.FUNC0;
import cn.techen.lbs.protocol.t376.T376Config.FUNC1;

public class ControlField extends AbstractElement {	
	private int dir;//bit[7]
	private int prm;//bit[6]
	private int fcb;//bit[5]
	private int acd;//bit[5]
	private int fcv;//bit[4]
	private int res;//bit[4]
	private int func;//bit[0-3]
	
	public ControlField() {
		len = 1;
		title = "Ctrl";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		value = Byte.toUnsignedInt(frame.process().queue.poll());
		dir = (value >> 7) & 0x01;
		prm = (value >> 6) & 0x01;
		fcb = (value >> 5) & 0x01;
		acd = fcb;
		fcv = (value >> 4) & 0x01;
		res = fcv;
		func = value & 0x0F;
				
		T376Config config = ((T376Config) frame.config());
		config.setDir(DIR.valueOf(dir));
		config.setPrm(prm);
		if (config.getDir() == DIR.SERVER) {
			config.setAcd(ACD.valueOf(acd));
		} else {
			config.setFcb(fcb);
			config.setFcv(fcv);
		}
		config.setFunc(func);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		dir = config.getDir().value();
		prm = config.getPrm();
		fcb = config.getFcb();
		acd = config.getAcd().value();
		fcv = config.getFcv();
		func = config.getFunc();		
		
		value = (dir << 7) + value;
		value = (prm << 6) + value;
		if (config.getDir() == DIR.SERVER) {
			value = (acd << 5) + value;	
			value = (res << 4) + value;
		} else {
			value = (fcb << 5) + value;
			value = (fcv << 4) + value;
		}
		value = (func & 0x0F) + value;
		frame.process().vector.add(0, (byte)value);
	}
	
	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H  [%s]B\r\n");
		sb.append("%-24s%-5s: %-10s: [%d]: %s\r\n");
		sb.append("%-24s%-5s: %-10s: [%d]\r\n");
		sb.append("%-24s%-5s: %-10s: [%d]: %s\r\n");
		sb.append("%-24s%-5s: %-10s: [%d]\r\n");
		sb.append("%-24s%-5s: %-10s: [%d]: %s");
		if (dir == DIR.SERVER.value()) {
			return String.format(sb.toString()
					, ProtocolUtil.int2HexString(value), ProtocolUtil.int2BinaryString(value, true)		
					, "", "DIR", "bit[7]", dir, DIR.valueOf(dir).descOf()
					, "", "PRM", "bit[6]", prm
					, "", "ACB", "bit[5]", acd, ACD.valueOf(acd).descOf()
					, "", "RES", "bit[4]", res
					, "", "FUNC", "bit[0-3]", func, (prm == 0) ? FUNC0.valueOf(func).descOf() :  FUNC1.valueOf(func).descOf());
		} else {
			return String.format(sb.toString()
					, ProtocolUtil.int2HexString(value), ProtocolUtil.int2BinaryString(value, true)		
					, "", "DIR", "bit[7]", dir, DIR.valueOf(dir).descOf()
					, "", "PRM", "bit[6]", prm
					, "", "FCB", "bit[5]", fcb, String.valueOf(fcb)
					, "", "FCV", "bit[4]", fcv
					, "", "FUNC", "bit[0-3]", func, (prm == 0) ? FUNC0.valueOf(func).descOf() :  FUNC1.valueOf(func).descOf());			
		}
	}

}
