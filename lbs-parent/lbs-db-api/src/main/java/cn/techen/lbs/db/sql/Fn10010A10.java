package cn.techen.lbs.db.sql;

import java.util.Queue;

import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn10010A10 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		int beginNo = Integer.parseInt(datas.poll().toString());
		int endNo = Integer.parseInt(datas.poll().toString());
		
		StringBuffer sb = new StringBuffer();
		sb.append("{LEN}");
		sb.append(String.format("select id, pointno, CONCAT_WS(':',buadrate,port), protocol, commaddr, tariffcount"
				+ ", CONCAT_WS(':',integercount,decimalcount), CONCAT_WS(':',customerclass,customersubclass)"
				+ ", longitude, latitude from prm_meter where sn>=%d and sn <= %d", beginNo, endNo));		
		
		return sb.toString();
	}

}
