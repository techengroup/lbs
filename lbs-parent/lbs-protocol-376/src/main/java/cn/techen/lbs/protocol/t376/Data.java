package cn.techen.lbs.protocol.t376;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t376.T376Config.AFN;
import cn.techen.lbs.protocol.t376.T376Config.DIR;
import cn.techen.lbs.protocol.t376.T376Config.FUNC0;
import cn.techen.lbs.protocol.t376.common.Local;

public class Data extends AbstractElement {
	private List<String> dadtList = new ArrayList<String>();
	private Map<String, String[]> dtKeyMap = new HashMap<String, String[]>();
	private Map<String, AbstractData> adMap = new HashMap<String, AbstractData>();
	
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
		sb.append(String.format("[%s]H", ProtocolUtil.byte2HexString(ProtocolUtil.list2byte(byteList), true)));
		
		for (String dadt : dadtList) {
			String[] dadts = dadt.split(":");
			sb.append(String.format("\r\n%23s%-5s: [%s]", "", "Point", dadts[0]));
			sb.append(String.format("\r\n%23s%-5s: [%s] [%s]", "", "Func", dadts[1], FnNames.getInstace().get(dtKeyMap.get(dadt))));
			
			String[] das = dadts[0].split(",");
			String[] dts = dadts[1].split(",");
			
			for (int i = 0; i < das.length; i++) {
				for (int j = 0; j < dts.length; j++) {
					AbstractData ad =adMap.get(das[i] + ":" + dts[i]);
					if (ad != null) {
						sb.append(String.format("\r\n%23s%s", "", ad.toExplain()));
					}
				}
			}
		}
		return sb.toString();
	}

	private void decodeN(AbstractFrame frame) throws Exception {
		if (frame.process().queue.size() > 2) {
			byte[] bytes = new byte[5];
			for (int i = 0; i < 5; i++) {
				bytes[i] = frame.process().queue.poll();
				byteList.add(bytes[i]);
			}
			
			int da1 = bytes[0];
			int da2 = (bytes[2] << 8) + bytes[1];
			int dt1 = bytes[3];
			int dt2 = bytes[4];
	
			T376Config config = ((T376Config) frame.config());
			if (config.getFunc() == FUNC0.LINK.value() && config.getAfn() == AFN.CONFIRM) {
				config.runs().put("LoginCon", dt1);
			} else {
				DIR dir = config.getDir();
				AFN afn = config.getAfn();
				
				List<String> das = new ArrayList<String>();
				for (int i = 0; i < 8; i++) {
					int x = (da1 >> i) & 0x01;
					if (x == 1) {
						int a = (i + 1) + 8 * (da2 - 1);
						das.add(String.valueOf(a));
					}
				}
				if (das.size() <= 0)
					das.add(String.valueOf(0));
	
				List<String> dts = new ArrayList<String>();
				List<String> dtKeys = new ArrayList<String>();
				for (int i = 0; i < 8; i++) {
					int x = (dt1 >> i) & 0x01;
					if (x == 1) {
						int t = (i + 1) + 8 * dt2;
						dts.add(String.valueOf(t));
						dtKeys.add(Local.CODE + ":" + dir.value() + ":" + ProtocolUtil.int2HexString(afn.value()).toUpperCase() + ":" + t);
					}
				}
				
				String daStr = String.join(",", das);
				String dtStr = String.join(",", dts);
				String funcs = daStr + ":" + dtStr;
				
				dtKeyMap.put(funcs, dtKeys.toArray(new String[0]));
				dadtList.add(funcs);

				config.funcs().add(funcs);	
				
				for (int i = 0; i < das.size(); i++) {
					for (int j = 0; j < dtKeys.size(); j++) {
						dataTypes = Elements.getInstace().get(dtKeys.get(j));
						
						if (dataTypes != null && !dataTypes.equals("")) {
							String dataClass = extract(dataTypes);
							
							AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
							ad.decode(frame);			
							byteList.addAll(ad.getByteList());	
							
							adMap.put(das.get(i) + ":" + dts.get(j), ad);
						}
						
						config.funcKeys().put(das.get(i) + ":" + dts.get(j), dtKeys.get(j));
					}
				}
				
				decodeN(frame);
			}
		}
		
		len = byteList.size();
	}

	public void encodeN(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		DIR dir = config.getDir();
		AFN afn = config.getAfn();
		List<String> dadts = config.funcs();

		for (String d : dadts) {
			String[] dArray = d.split(":");
			String da = dArray[0];
			String dt = dArray[1];
			String[] das = da.split(",");
			String[] dts = dt.split(",");

			String[] dtKeys = getDtKeys(dir, afn, dts);
			dtKeyMap.put(d, dtKeys);
			dadtList.add(d);
			
			da2da(frame, das);
			dt2dt(frame, dts);
			unit2unit(frame, das, dts, dtKeys);
		}
	}
	
	private String[] getDtKeys(DIR dir, AFN afn, String[] dts) {
		List<String> kList = new ArrayList<String>();
		for (String dt : dts) {
			kList.add(Local.CODE + ":" + dir.value() + ":" + ProtocolUtil.int2HexString(afn.value()).toUpperCase() + ":" + dt);
		}
		return kList.toArray(new String[0]);
	}

	private void da2da(AbstractFrame frame, String[] das) {
		int da1 = 0;
		int da2 = 0;

		for (int i = 0; i < das.length; i++) {
			String d = das[i];
			int p = Integer.parseInt(d);
			if (p == 0) break;
			int x = (int) Math.ceil((double)p / 8);
			int y = p % 8;
			if (y == 0) {
				da1 = (int) (Math.pow(2, 7) + da1);			
			} else {
				da1 = (int) (Math.pow(2, (y-1)) + da1);
			}
			da2 = x;
		}
		
		byte b1 = (byte) da1;
		byte b2 = (byte) (da2 & 0xFF);
		byte b3 = (byte) ((da2 >> 8) & 0xFF);

		frame.process().vector.add(b1);
		frame.process().vector.add(b2);
		frame.process().vector.add(b3);
		
		byteList.add(b1);
		byteList.add(b2);
		byteList.add(b3);
	}

	private void dt2dt(AbstractFrame frame, String[] dts) {
		int dt1 = 0;
		int dt2 = 0;

		for (int i = 0; i < dts.length; i++) {
			String d = dts[i];
			int t = Integer.parseInt(d);
			int x = (int) Math.floor((double)t / 8);
			int y = t % 8;
			if (y == 0) {
				dt1 = (int) (Math.pow(2, 7) + dt1);
				dt2 = x - 1;
			} else {
				dt1 = (int) (Math.pow(2, (y-1)) + dt1);
				dt2 = x;
			}			
		}

		frame.process().vector.add((byte) dt1);
		frame.process().vector.add((byte) dt2);
		
		byteList.add((byte) dt1);
		byteList.add((byte) dt2);
	}

	private void unit2unit(AbstractFrame frame, String[] das, String[] dts, String[] dtKeys) throws Exception {		
		for (int i = 0; i < das.length; i++) {
			for (int j = 0; j < dtKeys.length; j++) {
				dataTypes = Elements.getInstace().get(dtKeys[j]);
				
				if (dataTypes != null && !dataTypes.equals("")) {
					String dataClass = extract(dataTypes);
					
					AbstractData ad = ProtocolUtil.newData(dataClass, dataTypes);
					ad.encode(frame);
					byteList.addAll(ad.getByteList());
					
					adMap.put(das[i] + ":" + dts[j], ad);
				}				
			}
		}
		
		len = byteList.size();
	}

}
