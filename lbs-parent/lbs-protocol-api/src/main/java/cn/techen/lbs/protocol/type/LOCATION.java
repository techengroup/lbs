package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class LOCATION extends AbstractData{

	public LOCATION(String types) {
		dataTypes = types;
		len = Integer.parseInt(extract(dataTypes));
		bytes = new byte[len];
		format = extract(dataTypes);
		sort = Integer.parseInt(extract(dataTypes));
	}
	@Override
	public void decode(AbstractFrame frame) throws Exception {		
		StringBuffer sb = new StringBuffer();		
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();	
		}
		if (sort == 0) {
			bytes = ProtocolUtil.switchBytes(bytes);
		}
		for (int i = 0; i < len; i++) {
			sb.append(ProtocolUtil.bcd2Str(bytes[i]));
			if (i == 1) {
				sb.append(".");
			}
		}	    
		content = sb.toString();
		desc = sb.toString();
		desc = desc.replaceAll("^(0+)", "");
		content = desc;
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
	    desc = content.toString();
		desc = content.toString();
		desc = desc.replace(".", "");
		desc = ProtocolUtil.zeroFill(len*2, desc);
		
		byte[] octs = ProtocolUtil.str2Bcd(desc);
		if (sort == 0) {
			octs = ProtocolUtil.switchBytes(octs);
		}
		for (int i = 0; i < len; i++) {
			frame.process().vector.add(octs[i]);
		}
	}

//	@Override
//	public void decode(AbstractFrame frame) throws Exception {
//		for (int i = 0; i < len; i++) {
//			bytes[i] = frame.process().queue.poll();
//			byteList.add(bytes[i]);
//		}
//		
//		char[] chars = new char[len];
//		for (int i = 0; i < bytes.length; i++) {
//			chars[i] = (char)bytes[i];
//		}
//		
//		content = String.valueOf(chars);
//		desc = String.valueOf(chars);
//		
//		frame.config().units().add(content);
//	}
//
//	@Override
//	public void encode(AbstractFrame frame) throws Exception {
//		content = frame.config().units().poll();
//		desc = content.toString();
//		
//		desc = String.format("%-16s", desc);
//		
//		char[] chars = desc.toCharArray();
//		for (int i = 0; i < chars.length; i++) {
//			bytes[i] = (byte)chars[i];
//			frame.process().vector.add(bytes[i]);
//			byteList.add(bytes[i]);
//		}		
//	}

}
