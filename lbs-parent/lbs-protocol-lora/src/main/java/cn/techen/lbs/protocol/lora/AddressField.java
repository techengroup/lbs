package cn.techen.lbs.protocol.lora;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.lora.LoraConfig.Addr;
import cn.techen.lbs.protocol.lora.LoraConfig.Module;
import cn.techen.lbs.protocol.lora.LoraConfig.Relay;

public class AddressField extends AbstractElement {

	private byte[] bytes;
	
	private int vLen = 0;
	
	private String sourceAddr;
	
	private List<String> relayAddrs;
	
	private String targetAddr;
	
	public AddressField() {
		title="Addrs";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		LoraConfig loraConfig = ((LoraConfig) frame.config());
		Module module = loraConfig.getModule();
		
		if (module == Module.SLAVE) {
			sourceAddr="";
			Queue<Byte> queue = frame.process().queue;
			Addr addr = loraConfig.getAddr();
			Relay relay = loraConfig.getRelay();
			int num = (addr == Addr.LONG) ? 6 : 2;
			
			len = (2 + relay.value()) * num;
			bytes = new byte[len];
			
			sourceAddr = bcd2addr(queue, num);
			loraConfig.setSourceAddr(sourceAddr);
			
			if (relay.value() > 0) {
				relayAddrs = new ArrayList<String>();
				for (int i = 0; i < relay.value(); i++) {
					String relayAddr = bcd2addr(queue, num);
					relayAddrs.add(relayAddr);
					loraConfig.addRelayAddrs(relayAddr);
				}
			}
			
			targetAddr = bcd2addr(queue, num);			
			loraConfig.setTargetAddr(targetAddr);			
		}
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		LoraConfig loraConfig = ((LoraConfig) frame.config());
		Module module = loraConfig.getModule();
		
		if (module == Module.SLAVE) {
			Vector<Byte> vector = frame.process().vector;
			Addr addr = loraConfig.getAddr();
			Relay relay = loraConfig.getRelay();
			int num = (addr == Addr.LONG) ? 6 : 2;
			
			len = (2 + relay.value()) * num;
			bytes = new byte[len];
			vLen = len - 1;
			
			targetAddr = ProtocolUtil.padLeft(loraConfig.getTargetAddr(), '0', num*2);
			addr2bcd(vector, targetAddr, num);
			
			relayAddrs = loraConfig.getRelayAddrs();
			if (relay.value() > 0) {
				for (int i = (relay.value()-1); i >= 0; i--) {
					relayAddrs.set(i, ProtocolUtil.padLeft(relayAddrs.get(i), '0', num*2));
					addr2bcd(vector, relayAddrs.get(i), num);
				}
			}
						
			sourceAddr = ProtocolUtil.padLeft(loraConfig.getSourceAddr(), '0', num*2);
			addr2bcd(vector, sourceAddr, num);	
		}		
	}	

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H\r\n");
		sb.append("%15s%s: %s\r\n");
		sb.append("%15s%s: %s\r\n");
		sb.append("%15s%s: %s");
		return String.format(sb.toString()
				, ProtocolUtil.byte2HexString(bytes, true)
				, "", "sourceAddr", sourceAddr
				, "", "relayAddrs", (relayAddrs==null) ? "" : String.join(", ", relayAddrs)
				, "", "targetAddr", targetAddr);
	}
	
	private void addr2bcd(Vector<Byte> vector, String addr, int num) {
		byte[] bcd = ProtocolUtil.str2Bcd(addr);
		for (int i = (num-1); i >= 0; i--) {
			byte b = bcd[i];
			bytes[vLen] = b;
			vLen--;
			vector.add(0, bcd[i]);
		}
	}
	
	private String bcd2addr(Queue<Byte> queue, int num) {
		String addr = "";
		for (int i = 0; i < num; i++) {			
			byte b = queue.poll();
			bytes[vLen] = b;
			vLen++;
			addr += ProtocolUtil.bcd2Str(b);
		}		
		return addr;
	}
	
}
