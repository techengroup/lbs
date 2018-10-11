package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn30111C42A extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		int len = Integer.parseInt(datas.poll().toString());
		if (len >= 0) {
			for (int i = 0; i < len; i++) {
				datas.poll();// sub meter no -- no need right now
				int eventId = Integer.parseInt(datas.poll().toString());
				String occurtime = Global.date2String((Date) datas.poll(), "yyyy-MM-dd HH:mm:ss");

				sb.append(String.format("insert IGNORE into data_event(meterid, eventid, occurtime) "
						+ "values(%s, %d, '%s');", id, eventId, occurtime));
			}

			sb.append(String.format("update log_report set status=1, mdfon=NOW() where meterid=%s;", id));
		}

		return sb.toString();
	}

}
