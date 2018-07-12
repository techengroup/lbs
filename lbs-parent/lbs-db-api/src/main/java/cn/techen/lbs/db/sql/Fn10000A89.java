package cn.techen.lbs.db.sql;

import java.util.Queue;

public class Fn10000A89 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		return "select commaddr from KNL_LBS";
	}

}
