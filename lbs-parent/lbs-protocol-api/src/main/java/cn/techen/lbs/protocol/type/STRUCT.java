package cn.techen.lbs.protocol.type;

import java.util.ArrayList;
import java.util.List;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;

public class STRUCT extends AbstractData {
	
	private List<AbstractData> adList = new ArrayList<>();
	
	public STRUCT(String types) {
		dataTypes = types;
		len = Integer.parseInt(extract(dataTypes));
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		for (int i = 0; i < len; i++) {
			String dataClass = extract(dataTypes);
			
			AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
			ad.decode(frame);
			byteList.addAll(ad.getByteList());
			
			dataTypes = ad.getDataTypes();
		}
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		for (int i = 0; i < len; i++) {
			String dataClass = extract(dataTypes);
			
			AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
			ad.decode(frame);
			byteList.addAll(ad.getByteList());
			
			dataTypes = ad.getDataTypes();
			
			adList.add(ad);
		}
	}
	
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		for (AbstractData ad : adList) {
			sb.append(ad.toExplain());
		}
		return sb.toString().replace("||", "|");
	}

}
