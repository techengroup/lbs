package cn.techen.lbs.protocol.lora;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.lora.common.Local;

public class DataField extends AbstractElement {
	
	private Map<String, String> fnKeyMap = new HashMap<String, String>();
	private Map<String, AbstractData> adMap = new HashMap<String, AbstractData>();

	public DataField() {
		title="Datas";
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
			int func = Byte.toUnsignedInt(frame.process().queue.poll());
			byteList.add((byte)func);

			LoraConfig config = ((LoraConfig) frame.config());
			String key = Local.CODE  + ":" + config.getDir().value() + ":" + ProtocolUtil.int2HexString(config.getControl().value()).toUpperCase() + ":" + func;
			fnKeyMap.put(String.valueOf(func), key);
			
			config.funcs().add(String.valueOf(func));
			
			AbstractData ad = null;
			dataTypes = Elements.getInstace().get(key);
			if (dataTypes != null && !dataTypes.equals("")) {
				String dataClass = extract(dataTypes);
				
				ad = ProtocolUtil.newData(dataClass, dataTypes);
				ad.decode(frame);
				byteList.addAll(ad.getByteList());
			}
			
			config.funcKeys().put(String.valueOf(func), key);
			
			adMap.put(String.valueOf(func), ad);
			decodeN(frame);
		}
		
		len = byteList.size();
	}
	
	public void encodeN(AbstractFrame frame) throws Exception {
		LoraConfig config = ((LoraConfig) frame.config());
		for (String func : config.funcs()) {
			byte f = (byte)Integer.parseInt(func);
			frame.process().vector.add(0, f);
			byteList.add(f);
			
			String key = Local.CODE + ":" + config.getDir().value() + ":" + ProtocolUtil.int2HexString(config.getControl().value()).toUpperCase() + ":" + func ;
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
