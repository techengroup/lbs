package cn.techen.lbs.db.sql;

import java.util.Date;
import java.util.Queue;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn1111C42A extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		int len = Integer.parseInt(datas.poll().toString());
		for (int i = 0; i < len; i++) {
			datas.poll();// sub meter no -- no need right now
			int eventId = Integer.parseInt(datas.poll().toString());
			String occurtime = GlobalUtil.date2String((Date) datas.poll(), "yyyy-MM-dd HH:mm:ss");

			sb.append(String.format("insert IGNORE into data_event(meterid, eventid, occurtime, remark) "
					+ "values(%s, %d, '%s', null)", id, eventId, occurtime));
		}

		return sb.toString();
	}

}
