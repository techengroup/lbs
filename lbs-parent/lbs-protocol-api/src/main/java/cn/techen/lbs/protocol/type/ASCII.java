package cn.techen.lbs.protocol.type;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;

public class ASCII extends AbstractData{

	public ASCII(String types) {
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
		
		char[] chars = new char[len];
		for (int i = 0; i < bytes.length; i++) {
			chars[i] = (char)bytes[i];
		}
		
		content = String.valueOf(chars);
		desc = String.valueOf(chars);
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		desc = content.toString();
		
		desc = String.format("%-16s", desc);
		
		char[] chars = desc.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			bytes[i] = (byte)chars[i];
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}		
	}

}
