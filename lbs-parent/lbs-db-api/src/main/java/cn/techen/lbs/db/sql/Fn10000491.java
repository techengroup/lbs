package cn.techen.lbs.db.sql;

import java.util.Queue;

public class Fn10000491  extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		return String.format("update KNL_LBS set channel=%d"
				, datas.poll());
	}

}
