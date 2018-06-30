package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class DataField extends AbstractElement {
	
	private AFN0 afn = new AFN0();
	
	private SEQ seq = new SEQ();
	
	private Data data = new Data();
	
	private AUX0 aux = new AUX0();
	
	public DataField() {
		len=2;
		title="Datas";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {		
		afn.decode(frame);
		seq.decode(frame);
		data.decode(frame);		
		aux.decode(frame);
		
		len += data.getLen() + aux.getLen();
	}
	
	@Override
	public void encode(AbstractFrame frame) throws Exception {
		aux.encode(frame);
		data.encode(frame);
		seq.encode(frame);		
		afn.encode(frame);	
		
		len += data.getLen() + aux.getLen();
	}
	
	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H\r\n");
		sb.append("%15s%-5s: %s\r\n");
		sb.append("%15s%-5s: %s\r\n");
		sb.append("%15s%-5s: %s");
		return String.format(sb.toString()
				, ProtocolUtil.int2HexString(value)		
				, "", "AFN", afn.toExplain()
				, "", "SEQ", seq.toExplain()
				, "", "Units", data.toExplain());
	}

}
