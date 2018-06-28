package cn.techen.lbs.protocol.t645;

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
import cn.techen.lbs.protocol.t645.common.Local;

public class DataField extends AbstractElement {

	private List<Byte> bytes = new ArrayList<Byte>();
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
		sb.append(String.format("[%s]H\r\n", ProtocolUtil.byte2HexString(ProtocolUtil.list2byte(bytes), true)));
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
			String str = "";
			for (int i = 0; i < 4; i++) {
				int f = Byte.toUnsignedInt(frame.process().queue.poll());
				if (f > 0) {
					str = ProtocolUtil.int2HexString(f) + str;
				}
				bytes.add((byte)f);
			}

			int func = Integer.parseInt(str);
			T645Config config = ((T645Config) frame.config());
			String key = Local.CODE + ":" + config.getControl().value() + ":" + func + ":" + config.getDir().value();
			String format = Elements.getInstace().get(key);

			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (format != null && !format.equals("")) {
				String[] units = format.split(":");

				for (String unit : units) {
					String[] elements = unit.split(",");
					AbstractData ad = ProtocolUtil.newData(elements[0], Integer.parseInt(elements[1]), elements[2],
							Integer.parseInt(elements[3]), elements[4]);
					ad.decode(frame);
					bytes.addAll(ProtocolUtil.byte2List(ad.getByte()));
					objList.add(ad);					
				}
			}

			data.put(func, objList);

			decodeN(frame);
		}
	}

	public void encodeN(AbstractFrame frame) throws Exception {
		T645Config config = ((T645Config) frame.config());
		List<String> fList = config.funcs();
		for (String func : fList) {
			String func0 = ProtocolUtil.zeroFill(8, func);
			byte[] bs = ProtocolUtil.hexString2Byte(func0);
			for (int i = 0; i < bs.length; i++) {
				frame.process().vector.add(0, bs[i]);
				bytes.add(0, bs[i]);
			}

			String key = Local.CODE + ":" + config.getControl().value() + ":" + func + ":" + config.getDir().value();
			String format =  Elements.getInstace().get(key);

			List<AbstractData> objList = new ArrayList<AbstractData>();
			if (format != null && !format.equals("")) {
				String[] units = format.split(":");

				for (String unit : units) {
					String[] elements = unit.split(",");
					AbstractData ad = ProtocolUtil.newData(elements[0], Integer.parseInt(elements[1]), elements[2],
							Integer.parseInt(elements[3]), elements[4]);
					ad.encode(frame);
					bytes.addAll(ProtocolUtil.byte2List(ad.getByte()));
					objList.add(ad);
				}
			}

			data.put(Integer.parseInt(func), objList);
		}
	}

}
