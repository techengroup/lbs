package cn.techen.lbs.db.sql;

import java.util.Queue;

import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn1000043 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		return String.format("update KNL_LBS set ip='%s', port=%s, ip1='%s', port1=%s, apn='%s'"
				, datas.poll(), datas.poll(), datas.poll(), datas.poll(), datas.poll());
	}

}
