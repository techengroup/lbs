package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.GlobalUtil;

public class Fn10000D178 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		String time = GlobalUtil.date2String((Date)datas.poll(), "yyyy-MM-01");
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("select frozentime, savetime, 4, IFNULL(negative_energy0, 'EEEEEEEE') negative_energy0"
				+ ", IFNULL(negative_energy1, 'EEEEEEEE') negative_energy1, IFNULL(negative_energy2, 'EEEEEEEE') negative_energy2"
				+ ", IFNULL(negative_energy3, 'EEEEEEEE') negative_energy3, IFNULL(negative_energy4, 'EEEEEEEE') negative_energy4"
				+ " from data_energy_month  where meterid=%s and frozentime='%s' " 
				+ "UNION " 
				+ "select 'EEEEE' frozentime, 'EEEEEEEEEE' savetime, 4, 'EEEEEEEE' negative_energy0, 'EEEEEEEE' negative_energy1"
				+ ", 'EEEEEEEE' negative_energy2, 'EEEEEEEE' negative_energy3, 'EEEEEEEE' negative_energy4 from DUAL where "
				+ "NOT EXISTS(select 1 from data_energy_month where meterid=%s and frozentime='%s')",id, time, id, time));			
		return sb.toString();
	}
}
