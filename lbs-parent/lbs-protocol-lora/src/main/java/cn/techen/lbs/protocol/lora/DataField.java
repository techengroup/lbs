package cn.techen.lbs.protocol.lora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.common.Titles;
import cn.techen.lbs.protocol.lora.common.Local;

public class DataField extends AbstractElement {
	
	private Map<Integer, List<AbstractData>> data = new HashMap<Integer, List<AbstractData>>();

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
		for (Entry<Integer, List<AbstractData>> entry : data.entrySet()) {
			Integer key = entry.getKey();	
			List<AbstractData> aList = entry.getValue();
			sb.append(String.format("%24s%s : [%02d]H : %s", "",  "Func", key, Titles.getInstace().get(key.toString())));
			for (int i = 0; i < aList.size(); i++) {
				if (i == 0) {
					sb.append(String.format("\r\n%24s| %13s |", "", aList.get(i).getName()));
				}
				sb.append(String.format("\r\n%24s| %13s |", "", aList.get(i).getContent().toString()));
			}
		}
		return sb.toString();
	}
	
	public void decodeN(AbstractFrame frame) throws Exception {
		if (frame.process().queue.size() > 2) {
			int func = frame.process().queue.poll();
			byteList.add((byte)func);

			LoraConfig loraConfig = ((LoraConfig) frame.config());
			String key = Local.CODE  + ":" + loraConfig.getDir().value() + ":" +loraConfig.getControl().value() + ":" + func;
			dataTypes = Elements.getInstace().get(key);
			
			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (dataTypes != null && !dataTypes.equals("")) {
				String dataClass = extract(dataTypes);
				
				AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
				ad.decode(frame);
				byteList.addAll(ad.getByteList());
			}
			
			data.put(func, objList);
			decodeN(frame);
		}
		
		len = byteList.size();
	}
	
	public void encodeN(AbstractFrame frame) throws Exception {
		LoraConfig loraConfig = ((LoraConfig) frame.config());
		List<String> fList = loraConfig.funcs();
		for (String func : fList) {
			byte f = (byte)Integer.parseInt(func);
			frame.process().vector.add(0, f);
			byteList.add(f);
			
			String key = Local.CODE + ":" + loraConfig.getDir().value() + ":" + loraConfig.getControl().value() + ":" + func ;
			dataTypes = Elements.getInstace().get(key);
			
			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (dataTypes != null && !dataTypes.equals("")) {
				String dataClass = extract(dataTypes);
				
				AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
				ad.encode(frame);
				byteList.addAll(ad.getByteList());
			}
			
			data.put(Integer.parseInt(func), objList);
		}
		
		len = byteList.size();
	}


}
