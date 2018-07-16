package cn.techen.lbs.protocol.type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class DATE_TIME extends AbstractData {
	
	public DATE_TIME(String types) {
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
			sb.append(ProtocolUtil.bcd2Str(bytes[i]));
			byteList.add(bytes[i]);
		}
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		Date time = sDateFormat.parse(sb.toString());
		
		content = time;		
		desc = content.toString();
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		Date time = (Date) content;
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		String dd = sDateFormat.format(time);
		bytes = ProtocolUtil.str2Bcd(dd);
		for (int i = 0; i < bytes.length; i++) {
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
						
		desc = sDateFormat.parse(dd).toString();
	}
}
	
	