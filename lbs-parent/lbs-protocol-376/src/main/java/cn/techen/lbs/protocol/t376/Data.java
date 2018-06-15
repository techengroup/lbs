package cn.techen.lbs.protocol.t376;

import java.util.ArrayList;
import java.util.List;

import cn.techen.lbs.protocol.AbstractData;
import cn.techen.lbs.protocol.AbstractElement;
import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.t376.T376Config.AFN;
import cn.techen.lbs.protocol.t376.T376Config.DIR;
import cn.techen.lbs.protocol.t376.T376Config.FUNC0;
import cn.techen.lbs.protocol.t376.common.T376FN;
import cn.techen.lbs.protocol.t376.common.T376Helper;

public class Data extends AbstractElement {

	@Override
	public void decode(AbstractFrame frame) throws Exception {
		decodeN(frame);
	}

	@Override
	public void encode(AbstractFrame frame) throws Exception {
		encodeN(frame);
	}

	private void decodeN(AbstractFrame frame) throws Exception {
		int da1 = frame.process().queue.poll();
		int da2 = frame.process().queue.poll();
		da2 = (da2 << 8) + frame.process().queue.poll();
		int dt1 = frame.process().queue.poll();
		int dt2 = frame.process().queue.poll();

		T376Config config = ((T376Config) frame.config());
		if (config.getFunc() == FUNC0.LINK.value() && config.getAfn() == AFN.CONFIRM) {
			config.data().put("LoginCon", dt1);
		} else {
			List<String> das = new ArrayList<String>();
			for (int i = 0; i < 8; i++) {
				int a = ((da1 >> i) & 0x01) * da2;
				if (a > 0)
					das.add(String.valueOf(a));
			}
			if (das.size() <= 0)
				das.add(String.valueOf(0));

			List<String> dts = new ArrayList<String>();
			for (int i = 0; i < 8; i++) {
				int t = ((dt1 >> i) & 0x01) * (dt2 + 1);
				if (t > 0)
					dts.add(String.valueOf(t));
			}

			String dadt = String.join(",", das) + ":" + String.join(",", dts);
			config.dadt().add(dadt);

			for (int i = 0; i < das.size(); i++) {
				for (int j = 0; j < dts.size(); j++) {
					int t = Integer.parseInt(dts.get(j));
					decodeUnit(frame, t);
				}
			}
		}
	}

	private void decodeUnit(AbstractFrame frame, int fn) throws Exception {
		T376Config config = ((T376Config) frame.config());
		DIR dir = config.getDir();
		AFN afn = config.getAfn();
		String key = afn.value() + ":" + fn + ":" + dir.value();

		String format = T376FN.get(key);
		if (format != null && !format.equals("")) {
			String[] units = format.split(":");

			for (String unit : units) {
				String[] elements = unit.split(",");
				AbstractData ad = T376Helper.newData(elements[0]);
				ad.decode(frame);
			}
		}
	}

	public void encodeN(AbstractFrame frame) throws Exception {
		T376Config config = ((T376Config) frame.config());
		List<String> dadts = config.dadt();

		for (String d : dadts) {
			String[] dArray = d.split(":");
			String da = dArray[0];
			String dt = dArray[1];
			String[] das = da.split(",");
			String[] dts = dt.split(",");

			da2da(frame, das);
			dt2dt(frame, dts);
			unit2unit(frame, dts);
		}
	}

	private void da2da(AbstractFrame frame, String[] das) {
		int da1 = 0;
		int da2 = 0;

		for (int i = 0; i < das.length; i++) {
			String d = das[i];
			int p = Integer.parseInt(d);
			if (p != 0) {
				if (i == 0)
					da2 = (p / 8) + 1;

				int r = (p - 1) % 8;
				da1 = (1 << r) + da1;
			}
		}

		frame.process().vector.add((byte) da1);
		frame.process().vector.add((byte) (da2 & 0xFF));
		frame.process().vector.add((byte) ((da2 >> 8) & 0xFF));
	}

	private void dt2dt(AbstractFrame frame, String[] dts) {
		int dt1 = 0;
		int dt2 = 0;

		for (int i = 0; i < dts.length; i++) {
			String d = dts[i];
			int t = Integer.parseInt(d);
			if (i == 0)
				dt2 = t / 8;

			int r = (t - 1) % 8;
			dt1 = (1 << r) + dt1;
		}

		frame.process().vector.add((byte) dt1);
		frame.process().vector.add((byte) dt2);
	}

	private void unit2unit(AbstractFrame frame, String[] dts) throws Exception {
		T376Config config = ((T376Config) frame.config());
		DIR dir = config.getDir();
		AFN afn = config.getAfn();

		for (String fn : dts) {
			String key = afn.value() + ":" + fn + ":" + dir.value();
			String format = T376FN.get(key);
			if (format != null && !format.equals("")) {
				String[] units = format.split(":");

				for (String unit : units) {
					String[] elements = unit.split(",");
					AbstractData ad = T376Helper.newData(elements[0], Integer.parseInt(elements[1]), elements[2],
							Integer.parseInt(elements[3]), elements[4]);
					ad.encode(frame);
				}
			}
		}
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("%-6s: ");
		sb.append("%s");
		return String.format(sb.toString(), "Datas", "");
	}

}
