package cn.techen.lbs.protocol.t645;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t645.common.Local;

public class DataField extends AbstractElement {

	private Map<String, String> fnKeyMap = new HashMap<String, String>();
	private Map<String, AbstractData> adMap = new HashMap<String, AbstractData>();

	public DataField() {
		title = "Datas";
	}

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		decodeN(frame);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		encodeN(frame);
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("[%s]H\r\n", ProtocolUtil.byte2HexString(ProtocolUtil.list2byte(byteList), true)));
		
		for (Entry<String, AbstractData> entry : adMap.entrySet()) {
			String func = entry.getKey();	
			AbstractData ad = entry.getValue();
			sb.append(String.format("%15s%s : [%s] [%s]", "",  "Func", func, FnNames.getInstace().get(fnKeyMap.get(func))));
			if (ad != null) {
				sb.append(String.format("\r\n%15s%s", "", ad.toExplain()));
			}
		}
		return sb.toString();
	}

	public void decodeN(AbstractFrame frame) throws Exception {
		if (frame.process().queue.size() > 2) {			
			String func = "";
			for (int i = 0; i < 4; i++) {
				int f = Byte.toUnsignedInt(frame.process().queue.poll());
				if (f > 0) {
					func = ProtocolUtil.int2HexString(f) + func;
				}
				byteList.add((byte)f);
			}

			T645Config config = ((T645Config) frame.config());
			String key = Local.CODE  + ":" + config.getDir().value() + ":" + ProtocolUtil.int2HexString(config.getControl().value()) + ":" + func;
			fnKeyMap.put(String.valueOf(func), key);
			
			AbstractData ad = null;
			dataTypes = Elements.getInstace().get(key);
			if (dataTypes != null && !dataTypes.equals("")) {
				String dataClass = extract(dataTypes);
				
				ad = ProtocolUtil.newData(dataClass, dataTypes);
				ad.decode(frame);
				byteList.addAll(ad.getByteList());
			}
			
			adMap.put(String.valueOf(func), ad);
			decodeN(frame);
		}
	}

	public void encodeN(AbstractFrame frame) throws Exception {
		T645Config config = ((T645Config) frame.config());
		for (String func : config.funcs()) {
			String funcByte = ProtocolUtil.zeroFill(8, func);
			byte[] bs = ProtocolUtil.hexString2Byte(funcByte);
			for (int i = 0; i < bs.length; i++) {
				frame.process().vector.add(0, bs[i]);
				byteList.add(0, bs[i]);
			}
			
			String key = Local.CODE + ":" + config.getDir().value() + ":" + ProtocolUtil.int2HexString(config.getControl().value()) + ":" + func ;
			fnKeyMap.put(String.valueOf(func), key);
			
			AbstractData ad = null;
			dataTypes = Elements.getInstace().get(key);
			if (dataTypes != null && !dataTypes.equals("")) {
				String dataClass = extract(dataTypes);
				
				ad = ProtocolUtil.newData(dataClass, dataTypes);
				ad.encode(frame);
				byteList.addAll(ad.getByteList());
			}
			
			adMap.put(func, ad);
		}
		
		len = byteList.size();
	}

}
