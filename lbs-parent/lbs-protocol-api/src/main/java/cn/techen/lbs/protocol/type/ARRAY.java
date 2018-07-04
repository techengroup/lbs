package cn.techen.lbs.protocol.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class ARRAY extends AbstractData {	
	private int byteLen = 2;
	private List<String> typeArray;
	private List<AbstractData> adList = new ArrayList<AbstractData>();

	public ARRAY(String types) {
		dataTypes = types;
		byteLen = Integer.parseInt(extract(dataTypes));
		typeArray = Arrays.asList(dataTypes.split(","));
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		for (int i = 0; i < byteLen; i++) {
			byte b = frame.process().queue.poll();
			len = (b & 0xff) << (8 * i);
			byteList.add(b);
		}
		
		for (int i = 0; i < len; i++) {
			String dataClass = extract(dataTypes);
			
			AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
			ad.decode(frame);	
			
			byteList.addAll(ad.getByteList());			
			dataTypes = ad.getDataTypes();
			
			if (i < (len - 1)) repeat(ad);
		}		
		
		frame.config().units().add(len);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		len = Integer.parseInt(frame.config().units().poll().toString());
		
		for (int i = 0; i < byteLen; i++) {
			byte b = (byte) ((len << (8 * i)) & 0xff);
			frame.process().vector.add(b);
			byteList.add(b);
		}
		
		for (int i = 0; i < len; i++) {
			String dataClass = extract(dataTypes);
			
			AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
			ad.encode(frame);
			adList.add(ad);
			
			byteList.addAll(ad.getByteList());			
			dataTypes = ad.getDataTypes();
			
			if (i < (len - 1)) repeat(ad);
		}
	}
	
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("%s[%d]", "ARRAY", len));
		for (AbstractData ad : adList) {
			sb.append("\r\n" + ad.toExplain());
		}
		return sb.toString();	
	}
	
	private void repeat(AbstractData ad) {
		int count = 4;
		if (ad instanceof STRUCT) {
			count = 2 + ad.getLen() * 4;
		}
		
		List<String> list  = typeArray.subList(0, count);
		list.addAll(Arrays.asList(dataTypes.split(",")));
		dataTypes = String.join(",", list.toArray(new String[0]));
	}

}
