package cn.techen.lbs.db.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn10000A200 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		int len = Integer.parseInt(datas.poll().toString());
		List<String> sns = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			int sn = Integer.parseInt(datas.poll().toString()) + Global.RepeaterStartId;
			sns.add(String.valueOf(sn));
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("{LEN}");
		sb.append(String.format("select id-%d, pointno-%d, CONCAT_WS(':',baudrate,port) bp, protocol, commaddr"
									+ ", longitude, latitude from prm_node where id IN(%s)", Global.RepeaterStartId, Global.RepeaterStartId, String.join(",", sns.toArray(new String[0]))));	
				
		return sb.toString();
	}

}
