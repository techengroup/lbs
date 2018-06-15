package cn.techen.lbs.protocol.t645;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;
import cn.techen.lbs.protocol.t645.common.T645FN;
import cn.techen.lbs.protocol.t645.common.T645Helper;

public class DataField extends AbstractElement {

	private Map<Integer, List<AbstractData>> data = new HashMap<Integer, List<AbstractData>>();

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
		sb.append("[%s]H\r\n");
		for (Entry<Integer, List<AbstractData>> entry : data.entrySet()) {
			Integer key = entry.getKey();
			List<AbstractData> aList = entry.getValue();
			sb.append(String.format("%-24s%-7s: [%04d]H%-3s: %s", "", "FUNC", key, "", T645FN.getEx(key.toString())));
			for (AbstractData ad : aList) {
				sb.append(String.format("\r\n%38s | %19s |", "", ad.getContent().toString()));
			}
		}
		return sb.toString();
	}

	public void decodeN(AbstractFrame frame) throws Exception {
		if (frame.process().queue.size() > 2) {			
			String str = "";
			for (int i = 0; i < 4; i++) {
				int f = Byte.toUnsignedInt(frame.process().queue.poll());
				if (f > 0) {
					str = ProtocolUtil.int2HexString(f) + str;
				}
				len++;
			}

			int func = Integer.parseInt(str);
			T645Config loraConfig = ((T645Config) frame.config());
			String key = func + ":" + loraConfig.getDir().value();
			String format = T645FN.get(key);

			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (format != null && !format.equals("")) {
				String[] units = format.split(":");

				for (String unit : units) {
					String[] elements = unit.split(",");
					AbstractData ad = T645Helper.newData(elements[0], Integer.parseInt(elements[1]), elements[2],
							Integer.parseInt(elements[3]), elements[4]);
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
		T645Config loraConfig = ((T645Config) frame.config());
		List<String> fList = loraConfig.func();
		for (String func : fList) {
			String func0 = ProtocolUtil.zeroFill(8, func);
			byte[] bytes = ProtocolUtil.hexString2Byte(func0);
			for (int i = 0; i < bytes.length; i++) {
				frame.process().vector.add(0, bytes[i]);
				len++;
			}

			String key = func + ":" + loraConfig.getDir().value();
			String format = T645FN.get(key);

			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (format != null && !format.equals("")) {
				String[] units = format.split(":");

				for (String unit : units) {
					String[] elements = unit.split(",");
					AbstractData ad = T645Helper.newData(elements[0], Integer.parseInt(elements[1]), elements[2],
							Integer.parseInt(elements[3]), elements[4]);
					ad.encode(frame);
					objList.add(ad);
					len = len + ad.getLen();
				}
			}

			data.put(Integer.parseInt(func), objList);
		}
	}

}
