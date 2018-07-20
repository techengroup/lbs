package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn30111901F extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		String frozentime = GlobalUtil.date2String(new Date(), "yyyy-MM-01");
		double e0 = Double.parseDouble(datas.poll().toString());
		double e1 = Double.parseDouble(datas.poll().toString());
		double e2 = Double.parseDouble(datas.poll().toString());
		double e3 = Double.parseDouble(datas.poll().toString());
		double e4 = Double.parseDouble(datas.poll().toString());
		

		sb.append(String.format("insert into data_energy_month(meterid, frozentime"
				+ ", active_energy0, active_energy1, active_energy2, active_energy3, active_energy4) "
				+ "values(%s, '%s', %f, %f, %f, %f, %f) ON DUPLICATE KEY update "
				+ "active_energy0=%f, active_energy1=%f, active_energy2=%f, active_energy3=%f, active_energy4=%f"
				, id, frozentime, e0, e1, e2, e3, e4, e0, e1, e2, e3, e4));

		return sb.toString();
	}

}
