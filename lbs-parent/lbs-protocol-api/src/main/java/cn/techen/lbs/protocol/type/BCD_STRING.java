package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class BCD_STRING extends AbstractData {	
	
	public BCD_STRING(String types) {
		dataTypes = types;
		len = Integer.parseInt(extract(dataTypes));
		bytes = new byte[len];
		format = extract(dataTypes);
		sort = Integer.parseInt(extract(dataTypes));
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {		
		for (int i = 0; i < len; i++) {
			bytes[i] = frame.process().queue.poll();
			byteList.add(bytes[i]);
		}
		if (sort == 0) {
			bytes = ProtocolUtil.switchBytes(bytes);
		}
		
		String hex = ProtocolUtil.byte2HexString(bytes, false);
		if (ProtocolUtil.isAllE(hex)) {
			content = hex;
			desc = content.toString().toUpperCase();
		} else {	
			StringBuffer sb = new StringBuffer();
			String[] fs = format.split(":");
			
			for (int i = 0; i < len; i++) {
				sb.append(ProtocolUtil.bcd2Str(bytes[i]));	
			}
			
			if (!fs[1].equals("0")) {
				sb.insert(Integer.parseInt(fs[0]), ".");
				content = Double.parseDouble(sb.toString());
			} else {
				content = Integer.parseInt(sb.toString());
			}
			
			desc = content.toString();			
		}	    
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString().toUpperCase();
		
		if (ProtocolUtil.isAllE(desc)) {
			bytes = ProtocolUtil.hexString2Byte(desc);
		} else {			
			String[] fs = format.split(":");
			String[] ds = desc.split(".");
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < fs.length; i++) {
				int l = Integer.parseInt(fs[i]);
				sb.append(ProtocolUtil.zeroFill(l*2, ds[i]));
			}
			
			bytes = ProtocolUtil.str2Bcd(sb.toString());
		}
		
		if (sort == 0) {
			bytes = ProtocolUtil.switchBytes(bytes);
		}
		for (int i = 0; i < len; i++) {				
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}

}
