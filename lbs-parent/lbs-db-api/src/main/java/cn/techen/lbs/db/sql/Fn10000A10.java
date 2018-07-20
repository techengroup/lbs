package cn.techen.lbs.db.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn10000A10 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		int len = Integer.parseInt(datas.poll().toString());
		List<String> sns = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			sns.add(datas.poll().toString());
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("{LEN}");
		sb.append(String.format("select id, pointno, CONCAT_WS(':',baudrate,port) bp, protocol, commaddr, tariffcount"
				+ ", CONCAT('0:', CONCAT_WS(':',integercount-4,decimalcount-1)) di, CONCAT_WS(':',customerclass,customersubclass) cc"
				+ ", longitude, latitude from prm_meter where id IN(%s)",  String.join(",", sns.toArray(new String[0]))));	
				
		return sb.toString();
	}

}
