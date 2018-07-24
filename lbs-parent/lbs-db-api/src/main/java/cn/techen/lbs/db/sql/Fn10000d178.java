package cn.techen.lbs.db.sql;

import java.util.Queue;

public class Fn10000D178 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		int time = Integer.parseInt(datas.poll().toString());
		
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
