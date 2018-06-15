package cn.techen.lbs.protocol.lora;

import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.lora.LoraConfig.Addr;
import cn.techen.lbs.protocol.lora.LoraConfig.Channel;
import cn.techen.lbs.protocol.lora.LoraConfig.DIR;
import cn.techen.lbs.protocol.lora.LoraConfig.Module;
import cn.techen.lbs.protocol.lora.LoraConfig.Relay;

public class InfoField extends AbstractElement {
	
	private byte[] bytes = new byte[3];	
	private int relay; // bit0-3
	private int channel; // bit4-7
	private int addr; // bit8
	private int module; // bit9
	private int dir; // bit10
	private int routeCounter = 0; // bit16-19
	
	public InfoField() {
		len=3;
		title="Infos";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		bytes[0] = frame.process().queue.poll();
		bytes[1] = frame.process().queue.poll();
		bytes[2] = frame.process().queue.poll();

		routeCounter = bytes[0];
		
		addr = bytes[1] & 0x01;
		module = (bytes[1] >> 1) & 0x01;
		dir = (bytes[1] >> 2) & 0x01;

		relay = (bytes[2] >> 4) & 0x0F;
		channel = bytes[2] & 0x0F;

		LoraConfig loraConfig = ((LoraConfig) frame.config());
		
		loraConfig.setAddr(Addr.valueOf(bytes[1] & 0x01))
			.setModule(Module.valueOf((bytes[1] >> 1) & 0x01))
			.setDir(DIR.valueOf((bytes[1] >> 2) & 0x01))
			.setRelay(Relay.valueOf((bytes[2] >> 4) & 0x0F))
			.setChannel(Channel.valueOf(bytes[2] & 0x0F));

	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		LoraConfig loraConfig = ((LoraConfig) frame.config());
		
		relay = loraConfig.getRelay().value();
		channel = loraConfig.getChannel().value();
		bytes[2] = (byte) (((relay << 4) & 0xF0) + (channel & 0x0F));
		frame.process().vector.add(0, bytes[2]);

		addr = loraConfig.getAddr().value();
		module = loraConfig.getModule().value();
		dir = loraConfig.getDir().value();
		bytes[1] = (byte) (((dir << 2) & 0x04) + ((module << 1) & 0x02) + (addr & 0x01));
		frame.process().vector.add(0, bytes[1]);

		bytes[0] = (byte) routeCounter;
		frame.process().vector.add(0, bytes[0]);
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("[%s]H  [%s]B\r\n");
		sb.append("%-24s%-19s: %-10s: [%d]: %s\r\n");
		sb.append("%-24s%-19s: %-10s: [%d]: %s\r\n");
		sb.append("%-24s%-19s: %-10s: [%d]: %s\r\n");
		sb.append("%-24s%-19s: %-10s: [%d]\r\n");
		sb.append("%-24s%-19s: %-10s: [%d]\r\n");
		sb.append("%-24s%-19s: %-10s: [%d]");
		return String.format(sb.toString()
				, ProtocolUtil.byte2HexString(bytes, true), ProtocolUtil.byte2BinaryString(bytes, true)				
				, "", "Transport Direction", "bit[10]", dir, DIR.valueOf(dir).descOf()
				, "", "Operation Object", "bit[9]", module, Module.valueOf(module).descOf()
				, "", "Address Type", "bit[8]", addr, Addr.valueOf(addr).descOf()
				, "", "Channel No", "bit[4-7]", channel
				, "", "Relay Grade", "bit[0-3]", relay
				, "", "Route Counter", "bit[16-19]", routeCounter);
	}

}
