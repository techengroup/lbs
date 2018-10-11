package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn301119010 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		String frozentime = Global.date2String(new Date(), "yyyy-MM-01");
		double e0 = Double.parseDouble(datas.poll().toString());
		

		sb.append(String.format("insert into data_energy_month(meterid, frozentime, active_energy0) "
				+ "values(%s, '%s', %f) ON DUPLICATE KEY update active_energy0=%f"
				, id, frozentime, e0, e0));

		return sb.toString();
	}

}
