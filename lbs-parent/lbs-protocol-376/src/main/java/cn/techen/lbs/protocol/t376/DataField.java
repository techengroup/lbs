package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t376.T376Config.AFN;

public class DataField extends AbstractElement {
	
	private int afn;
	
	private SEQ seq = new SEQ();
	
	private Data data = new Data();
	
	private AUX0 aux = new AUX0();
	
	public DataField() {
		title="Datas";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {		
		T376Config config = ((T376Config) frame.config());
		
		afn = ProtocolUtil.byte2Int(frame.process().queue.poll());
		config.setAfn(AFN.valueOf(afn));
		seq.decode(frame);
		data.decode(frame);		
		aux.decode(frame);
	}
	
	@Override
	public void encode(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());		
		aux.encode(frame);
		data.encode(frame);
		seq.encode(frame);		
		afn = config.getAfn().value();
		frame.process().vector.add(0, (byte)afn);		
	}
	
	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H\r\n");
		sb.append("%-24s%-5s: [%s]H: %s\r\n");
		sb.append("%-24s%-5s: %s\r\n");
		sb.append("%-24s%-5s: %s");
		return String.format(sb.toString()
				, ProtocolUtil.int2HexString(value)		
				, "", "AFN", ProtocolUtil.int2HexString(afn), AFN.valueOf(afn).descOf()
				, "", "SEQ", seq.toExplain()
				, "", "Units", data.toExplain());
	}

}
