package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t376.T376Config.ACD;

public class AUX0 extends AbstractElement  {
	
//	private int pw;//下行
	private EC ec;//上行 pw和ec同时只存在一个
//	private int tp;
	
	public AUX0() {
		title="AUX";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		if (config.getAcd() == ACD.YES) {
			ec = new EC();
			ec.encode(frame);
		}
	}

	@Override
	public String toExplain() {
		if (ec != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("[%s]H\r\n");
			sb.append("%23s%-5s: %s");
			return String.format(sb.toString()
					, ProtocolUtil.int2HexString(value)		
					, "", "EC", ec.toExplain());
		}
		
		return "Null";		
	}

}
