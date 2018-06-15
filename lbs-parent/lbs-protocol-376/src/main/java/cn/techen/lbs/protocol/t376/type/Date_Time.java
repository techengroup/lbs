package cn.techen.lbs.protocol.t376.type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.t376.T376Config;

public class Date_Time extends AbstractData {
	
	public Date_Time(Integer length, String format, Integer asc, String tableColumn) {
		len = length;
		bytes = new byte[length];
		this.format = format;
		this.asc = asc;
		this.tableColumn = tableColumn;
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		bytes[0] = frame.process().queue.poll();
		bytes[1] = frame.process().queue.poll();
		bytes[2] = frame.process().queue.poll();
		bytes[3] = frame.process().queue.poll();
		bytes[4] = (byte) (frame.process().queue.poll() & 0x0F);
		bytes[5] = frame.process().queue.poll();
		
		StringBuffer sb = new StringBuffer();
		sb.append(ProtocolUtil.bcd2Str(bytes[0]));
		sb.append(ProtocolUtil.bcd2Str(bytes[1]));
		sb.append(ProtocolUtil.bcd2Str(bytes[2]));
		sb.append(ProtocolUtil.bcd2Str(bytes[3]));
		sb.append(ProtocolUtil.zeroFill(2, bytes[4]));
		sb.append(ProtocolUtil.bcd2Str(bytes[5]));
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat("ssmmHHddMMyy", Locale.ENGLISH);
		Date time = sDateFormat.parse(sb.toString());
		
		content = time;
		
		sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E", Locale.ENGLISH);
		desc = sDateFormat.format(time);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		content = config.unit().poll();
		Date time = (Date) content;
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat("ssmmHHdd", Locale.ENGLISH);
		String dd = sDateFormat.format(time);
		byte[] date = ProtocolUtil.str2Bcd(dd);
		for (int i = 0; i < date.length; i++) {			
			frame.process().vector.add(date[i]);
		}
		
		sDateFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
		String mm = sDateFormat.format(time);
		sDateFormat = new SimpleDateFormat("E", Locale.ENGLISH);
		String week = sDateFormat.format(time);
		int km = (DayOfWeek(week) << 4) + Integer.parseInt(mm);
		frame.process().vector.add((byte)km);
				
		sDateFormat = new SimpleDateFormat("yy", Locale.ENGLISH);
		String yy = sDateFormat.format(time);
		date = ProtocolUtil.str2Bcd(yy);
		for (int i = 0; i < date.length; i++) {			
			frame.process().vector.add(date[i]);
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
