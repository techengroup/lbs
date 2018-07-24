package cn.techen.lbs.db.sql;

import java.util.Queue;

public class Fn10000D177 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		int time = Integer.parseInt(datas.poll().toString());
		
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("select frozentime, savetime, 4, IFNULL(active_energy0, 'EEEEEEEEEE'), IFNULL(active_energy1, 'EEEEEEEEEE'), IFNULL(active_energy2, 'EEEEEEEEEE'), IFNULL(active_energy3, 'EEEEEEEEEE'), IFNULL(active_energy4, 'EEEEEEEEEE') from data_energy_month  where meterid=%d and frozentime='%s' " 
				+ "UNION " 
				+ "select 'EEEEE', 'EEEEEEEEEE', 4, 'EEEEEEEEEE', 'EEEEEEEEEE', 'EEEEEEEEEE', 'EEEEEEEEEE', 'EEEEEEEEEE', 'EEEEEEEEEE' from DUAL where NOT EXISTS(select 1 from data_energy_month where meterid=%d and frozentime='%s')", id, time, id, time));			
		return sb.toString();
	}

}
