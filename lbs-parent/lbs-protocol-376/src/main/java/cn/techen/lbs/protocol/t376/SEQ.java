package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.t376.T376Config.CON;
import cn.techen.lbs.protocol.t376.T376Config.TPV;

public class SEQ extends AbstractElement  {
	
	private int tpv;	
	private int fir;	
	private int fin;	
	private int con;	
	private int seq;

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		value = Byte.toUnsignedInt(frame.process().queue.poll());
		tpv = (value >> 7) & 0x01;
		fir = (value >> 6) & 0x01;
		fin = (value >> 5) & 0x01;
		con = (value >> 4) & 0x01;
		seq = value & 0x0F;
		
		T376Config config = ((T376Config) frame.config());
		config.setTpv(TPV.valueOf(tpv));
		config.setFir(fir);
		config.setFin(fin);
		config.setCon(CON.valueOf(con));
		config.setSeq(seq);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		tpv = config.getTpv().value();
		fir = config.getFir();
		fin = config.getFin();
		con = config.getCon().value();
		seq = config.getSeq();
		
		value = (tpv << 7) + value;
		value = (fir << 6) + value;
		value = (fin << 5) + value;
		value = (con << 4) + value;
		value = (seq & 0x0F) + value;	
		value = Byte.toUnsignedInt((byte)value);
		frame.process().vector.add(0, (byte)value);
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H  [%s]B\r\n");
		sb.append("%-32s%-5s: %-10s: [%d]: %s\r\n");
		sb.append("%-32s%-5s: %-10s: [%d]\r\n");
		sb.append("%-32s%-5s: %-10s: [%d]\r\n");
		sb.append("%-32s%-5s: %-10s: [%d]: %s\r\n");
		sb.append("%-32s%-5s: %-10s: [%d]");
		return String.format(sb.toString()
				, ProtocolUtil.int2HexString(value), ProtocolUtil.int2BinaryString(value, true)		
				, "", "TPV", "bit[7]", tpv, TPV.valueOf(tpv).descOf()
				, "", "FIR", "bit[6]", fir
				, "", "FIN", "bit[5]", fin
				, "", "CON", "bit[4]", con, CON.valueOf(con).descOf()
				, "", "SEQ", "bit[0-3]", seq);
	}

}
