package cn.techen.lbs.db.sql;

import java.util.Queue;

import cn.techen.lbs.db.common.Global;

public class Fn10000E1 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		int count = Integer.parseInt(datas.poll().toString());
		int index = Integer.parseInt(datas.poll().toString());
		
		StringBuffer sb = new StringBuffer();
		sb.append("{LEN}");
		sb.append(String.format("select e.eventid, m.commaddr, e.occurtime, IFNULL(e.content, 'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE') content "
				+ "from data_event e LEFT JOIN prm_meter m on e.MeterID=m.ID LIMIT %d, %d", index, count));	
		
		Global.TempLastEventIndex = count;
		
		return sb.toString();
	}
}
