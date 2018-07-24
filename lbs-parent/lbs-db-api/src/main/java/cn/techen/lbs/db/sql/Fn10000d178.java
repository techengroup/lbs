package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.GlobalUtil;

public class Fn10000D178 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		String time = GlobalUtil.date2String((Date)datas.poll(), "yyyy-MM-01");
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("select frozentime, savetime, 4, IFNULL(negative_energy0, 'EEEEEEEE'), IFNULL(negative_energy1, 'EEEEEEEE'), "
				+ "IFNULL(negative_energy2, 'EEEEEEEE'), IFNULL(negative_energy3, 'EEEEEEEE'), "
				+ "IFNULL(negative_energy4, 'EEEEEEEE') from data_energy_month  where meterid=%d and frozentime='%s' " 
				+ "UNION " 
				+ "select 'EEEEE', 'EEEEEEEEEE', 4, 'EEEEEEEE', 'EEEEEEEE', 'EEEEEEEE', 'EEEEEEEE', 'EEEEEEEE', 'EEEEEEEE' "
				+ "from DUAL where NOT EXISTS(select 1 from data_energy_month where meterid=%d and frozentime='%s')",id, time, id, time));			
		return sb.toString();
	}
}
