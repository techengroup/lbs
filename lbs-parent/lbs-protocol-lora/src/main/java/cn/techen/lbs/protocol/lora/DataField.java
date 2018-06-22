package cn.techen.lbs.protocol.lora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.lora.common.Local;
import cn.techen.lbs.protocol.lora.common.LoraFN;
import cn.techen.lbs.protocol.lora.common.LoraHelper;

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
		sb.append("[%s]H\r\n");
		for (Entry<Integer, List<AbstractData>> entry : data.entrySet()) {
			Integer key = entry.getKey();	
			List<AbstractData> aList = entry.getValue();
			sb.append(String.format("%24s%4s : [%02d]H : %s", "", "Func", key, LoraFN.getEx(key.toString())));
			for (AbstractData ad : aList) {
				sb.append(String.format("\r\n%38s | %13s |", "", ad.getContent().toString()));
			}
		}
		return sb.toString();
	}
	
	public void decodeN(AbstractFrame frame) throws Exception {
		if (frame.process().queue.size() > 2) {
			int func = frame.process().queue.poll();
			len++;

			LoraConfig loraConfig = ((LoraConfig) frame.config());
			String key = Local.CODE + ":" +loraConfig.getControl().value() + ":" + func  + ":" + loraConfig.getDir().value();
			String format = LoraFN.get(key);
			
			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (format != null && !format.equals("")) {
				String[] units = format.split(":");
				
				for (String unit : units) {
					String[] elements = unit.split(",");
					AbstractData ad = LoraHelper.newData(elements[0], Integer.parseInt(elements[1]), elements[2], Integer.parseInt(elements[3]), elements[4]);
					ad.decode(frame);
					objList.add(ad);
					len = len + ad.getLen();
				}
			}
			
			data.put(func, objList);
			
			decodeN(frame);
		}		
	}
	
	public void encodeN(AbstractFrame frame) throws Exception {
		LoraConfig loraConfig = ((LoraConfig) frame.config());
		List<String> fList = loraConfig.func();
		for (String func : fList) {
			frame.process().vector.add(0, (byte)Integer.parseInt(func));
			
			String key = Local.CODE + ":" + loraConfig.getControl().value() + ":" + func  + ":" + loraConfig.getDir().value();
			String format = LoraFN.get(key);
			len++;
			
			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (format != null && !format.equals("")) {
				String[] units = format.split(":");
				
				for (String unit : units) {
					String[] elements = unit.split(",");
					AbstractData ad = LoraHelper.newData(elements[0], Integer.parseInt(elements[1]), elements[2], Integer.parseInt(elements[3]), elements[4]);
					ad.encode(frame);
					objList.add(ad);
					len = len + ad.getLen();
				}
			}
			
			data.put(Integer.parseInt(func), objList);
		}
	}


}
