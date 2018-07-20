package cn.techen.lbs.protocol.type;

import java.util.ArrayList;
import java.util.List;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class BIT_STRING extends AbstractData {

	public BIT_STRING(String types) {
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
		
		String val = ProtocolUtil.byte2BinaryString(bytes, false);	
		val = val.substring(val.length() - 8*len,val.length());

		List<String> vals = new ArrayList<String>();
		String[] fs = format.split(":");
		int start = 0;
		int end = 0;
		for (String f : fs) {
			end = start + Integer.parseInt(f);
			vals.add(String.valueOf(Integer.parseInt(val.substring(start, end), 2)));
			start = end;
		}
		
		content = String.join(":", vals.toArray(new String[0]));
		desc = content.toString();

		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString();
		
		String[] ds = desc.split(":");
		String[] fs = format.split(":");
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < fs.length; i++) {
			sb.append(ProtocolUtil.zeroFill(Integer.parseInt(fs[i]), Integer.toBinaryString(Integer.parseInt(ds[i]))));
		}
		
		int val = Integer.parseInt(sb.toString(), 2);

		for (int i = 0; i < len; i++) {
			bytes[i] = (byte) (val << (8*i));
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}

}
