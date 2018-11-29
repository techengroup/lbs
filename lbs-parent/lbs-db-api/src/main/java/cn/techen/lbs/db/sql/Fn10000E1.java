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
		
		int diff = start + count - 65536;
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("{LEN}");
		sb.append("select * from (select * from (");
		sb.append("select * from (select e.eventid, m.commaddr, e.occurtime, IFNULL(e.content, 'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE') content "
				+ "from data_event e LEFT JOIN prm_node m ON e.meterid=m.id ORDER BY e.savetime LIMIT 65536, 65536) a");	
		sb.append(" UNION ");	
		sb.append(String.format("select * from (select e.eventid, m.commaddr, e.occurtime, IFNULL(e.content, 'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE') content "
				+ "from data_event e LEFT JOIN prm_node m ON e.meterid=m.id ORDER BY e.savetime LIMIT %d, 65536) b", Global.EventRecoderOverAmount));	
		sb.append(String.format(") c LIMIT %d, %d) m", start, count));
		
		if (diff > 0) {
			sb.append(" UNION ");	
			
			sb.append("select * from (select * from (");
			sb.append("select * from (select e.eventid, m.commaddr, e.occurtime, IFNULL(e.content, 'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE') content "
					+ "from data_event e LEFT JOIN prm_node m ON e.meterid=m.id ORDER BY e.savetime LIMIT 65536, 65536) a");	
			sb.append(" UNION ");	
			sb.append(String.format("select * from (select e.eventid, m.commaddr, e.occurtime, IFNULL(e.content, 'EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE') content "
					+ "from data_event e LEFT JOIN prm_node m ON e.meterid=m.id ORDER BY e.savetime LIMIT %d, 65536) b", Global.EventRecoderOverAmount));	
			sb.append(String.format(") c LIMIT 0, %d) n", diff));
		}		
		
		return sb.toString();
	}
}
