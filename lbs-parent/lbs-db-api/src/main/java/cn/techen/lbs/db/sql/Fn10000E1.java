package cn.techen.lbs.db.sql;

import java.util.Queue;

import cn.techen.lbs.db.common.Global;

public class Fn10000E1 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		int count = Integer.parseInt(datas.poll().toString());
		int start = Integer.parseInt(datas.poll().toString());
		

		Global.CurrentReadEventCount = count;
		Global.CurrentReadEventStart = start;
		
		StringBuffer sb = new StringBuffer();
		sb.append("{LEN}");
		sb.append(String.format("select e.eventid, m.commaddr, e.occurtime, IFNULL(e.content, 'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE') content "
				+ "from data_event e LEFT JOIN prm_meter m on e.MeterID=m.ID LIMIT %d, %d", start, count));	
		
		return sb.toString();
	}
}
