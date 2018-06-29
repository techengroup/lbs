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
			
			if (i == 4) {
				sb.append(ProtocolUtil.zeroFill(2, (bytes[i] & 0x0F)));
			} else {
				sb.append(ProtocolUtil.bcd2Str(bytes[i]));
			}
			
			byteList.add(bytes[i]);
		}
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat("ssmmHHddMMyy", Locale.ENGLISH);
		Date time = sDateFormat.parse(sb.toString());
		
		content = time;
		
		sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E", Locale.ENGLISH);
		desc = sDateFormat.format(time);
		
		frame.config().units().add(content);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		content = frame.config().units().poll();
		Date time = (Date) content;
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat("ssmmHHddMMyy", Locale.ENGLISH);
		String dd = sDateFormat.format(time);
		bytes = ProtocolUtil.str2Bcd(dd);
		for (int i = 0; i < bytes.length; i++) {
			if (i == 4) {
				sDateFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
				String mm = sDateFormat.format(time);
				sDateFormat = new SimpleDateFormat("E", Locale.ENGLISH);
				String week = sDateFormat.format(time);
				int wm = (DayOfWeek(week) << 4) + Integer.parseInt(mm);
				frame.process().vector.add((byte)wm);
				byteList.add((byte)wm);
			} else {
				frame.process().vector.add(bytes[i]);
				byteList.add(bytes[i]);
			}
		}
		
		sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E", Locale.ENGLISH);
		desc = sDateFormat.format(time);
	}
	
	private int DayOfWeek(String week) {
		int d = 0;
		if (week.equals("Mon")) {
			d = 1;
		}
		if (week.equals("Tue")) {
			d = 2;
		}
		if (week.equals("Wed")) {
			d = 3;
		}
		if (week.equals("Thu")) {
			d = 4;
		}
		if (week.equals("Fri")) {
			d = 5;
		}
		if (week.equals("Sat")) {
			d = 6;
		}
		if (week.equals("Sun")) {
			d = 7;
		}
		return d;
	}

}
