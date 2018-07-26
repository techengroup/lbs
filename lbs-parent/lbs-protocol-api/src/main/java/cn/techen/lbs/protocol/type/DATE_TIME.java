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
			byteList.add(bytes[i]);
		}
		
		String hex = ProtocolUtil.byte2HexString(bytes, false);
		if (ProtocolUtil.isAllE(hex)) {
			content = hex;
		} else {
			sb.append(ProtocolUtil.bcd2Str(bytes));
			SimpleDateFormat sDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
			Date time = sDateFormat.parse(sb.toString());			
			content = time;	
		}
			
		desc = content.toString();
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		if (ProtocolUtil.isAllE(content.toString())) {
			bytes = ProtocolUtil.hexString2Byte(content.toString());
			desc = content.toString();
		} else {
			Date time = null;
			if (content instanceof Date) {
				time = (Date) content;
			} else {
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				time = s.parse(content.toString());
			}		
			SimpleDateFormat sDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
			String dd = sDateFormat.format(time);
			bytes = ProtocolUtil.str2Bcd(dd);			
			desc = sDateFormat.parse(dd).toString();
		}
		
		for (int i = 0; i < bytes.length; i++) {
			frame.process().vector.add(bytes[i]);
			byteList.add(bytes[i]);
		}
	}
}
	
	