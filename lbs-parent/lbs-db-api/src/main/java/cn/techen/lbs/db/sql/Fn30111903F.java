package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn30111903F extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		String frozentime = Global.date2String(new Date(), "yyyy-MM-01");
		double e0 = Double.parseDouble(datas.poll().toString());
		double e1 = Double.parseDouble(datas.poll().toString());
		double e2 = Double.parseDouble(datas.poll().toString());
		double e3 = Double.parseDouble(datas.poll().toString());
		double e4 = Double.parseDouble(datas.poll().toString());
		

		sb.append(String.format("insert into data_energy_month(meterid, frozentime"
				+ ", negative_energy0, negative_energy1, negative_energy2, negative_energy3, negative_energy4) "
				+ "values(%s, '%s', %f, %f, %f, %f, %f) ON DUPLICATE KEY update "
				+ "negative_energy0=%f, negative_energy1=%f, negative_energy2=%f, negative_energy3=%f, negative_energy4=%f"
				, id, frozentime, e0, e1, e2, e3, e4, e0, e1, e2, e3, e4));

		return sb.toString();
	}

}
