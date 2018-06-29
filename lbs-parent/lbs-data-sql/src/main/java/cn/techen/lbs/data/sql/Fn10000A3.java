package cn.techen.lbs.data.sql;

import java.util.Queue;

import cn.techen.lbs.db.common.AbstractSQL;

public class Fn10000A3 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		return "select ip, port, ip1, port1, apn from PRM_LBS";
	}

}
