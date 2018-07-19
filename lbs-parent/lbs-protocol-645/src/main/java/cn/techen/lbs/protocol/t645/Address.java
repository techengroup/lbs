package cn.techen.lbs.protocol.t645;

import java.util.Queue;
import java.util.Vector;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class Address extends AbstractElement {
	
	public Address() {
		len = 6;
		title = "Addr";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		T645Config t645Config = ((T645Config) frame.config());
		Queue<Byte> queue = frame.process().queue;
		
		desc = bcd2addr(queue, len);			
		t645Config.setCommAddr(desc);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		T645Config t645Config = ((T645Config) frame.config());		
		desc = ProtocolUtil.zeroFill(len*2, t645Config.getCommAddr());
		Vector<Byte> vector = frame.process().vector;
		addr2bcd(vector, desc, len);
	}
	
	@Override
	public String toExplain() {	
		return String.format("[%s]H  %s", desc, desc);
	}
	
	private void addr2bcd(Vector<Byte> vector, String addr, int num) {		
		byte[] bcd = ProtocolUtil.str2Bcd(addr);
		for (int i = 0; i < len; i++) {
			vector.add(0, bcd[i]);
		}
	}
	
	private String bcd2addr(Queue<Byte> queue, int num) {
		String addr = "";
		for (int i = 0; i < num; i++) {				
			addr = ProtocolUtil.bcd2Str(queue.poll()) + addr;
		}		
		return addr;
	}
	
	

}
