package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn1111903F extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		int len = Integer.parseInt(datas.poll().toString());
		for (int i = 0; i < len; i++) {
			String frozentime = GlobalUtil.date2String(new Date(), "yyyy-MM-01");
			double e0 = Double.parseDouble(datas.poll().toString());
			double e1 = Double.parseDouble(datas.poll().toString());
			double e2 = Double.parseDouble(datas.poll().toString());
			double e3 = Double.parseDouble(datas.poll().toString());
			double e4 = Double.parseDouble(datas.poll().toString());
			

			sb.append(String.format("insert into data_energy_month(meterid, frozentime"
					+ ", negative_energy0, negative_energy1, negative_energy2, negative_energy3, negative_energy4) "
					+ "values(%s, '%s', %d, %d, %d, %d, %d) ON DUPLICATE KEY update "
					+ "negative_energy0=%d, negative_energy1=%d, negative_energy2=%d, negative_energy3=%d, negative_energy4=%d"
					, id, frozentime, e0, e1, e2, e3, e4, e0, e1, e2, e3, e4));
		}

		return sb.toString();
	}

}