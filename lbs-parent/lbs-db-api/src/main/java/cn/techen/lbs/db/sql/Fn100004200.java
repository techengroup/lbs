package cn.techen.lbs.db.sql;

import java.util.Queue;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn100004200 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		int len = Integer.parseInt(datas.poll().toString());
		for (int i = 0; i < len; i++) {
			int sn = Integer.parseInt(datas.poll().toString()) + Global.RepeaterStartId;
			int pointNo = Integer.parseInt(datas.poll().toString()) + Global.RepeaterStartId;
			int deviceclass = 0;
			String[] ratePort  = datas.poll().toString().split(":");
			int protocol = Integer.parseInt(datas.poll().toString());
			String commAddr = datas.poll().toString();
			double longitude = Double.parseDouble(datas.poll().toString());
			double latitude = Double.parseDouble(datas.poll().toString());			
			
//			sb.append("ON DUPLICATE KEY update mdfon=NOW();");		
			
			if (pointNo == Global.RepeaterStartId) {
				sb.append("delete from log_network where route like '%," + commAddr + "%';");
				sb.append(String.format("delete from prm_node where id=%d;", sn));
				sb.append("update prm_node set status=-1, grade=1, relay=0, path=null, route=null, parent=0"
						+ ", mdfon=NOW(), regon=null where path like '%/" + sn + "%';");
			} else {
				sb.append("insert IGNORE into prm_node(id, pointno, deviceclass, baudrate, port, protocol, commaddr"
						+ ", longitude, latitude) ");
				sb.append(String.format("values(%d, %d, %d, %s, %s, %d, '%s', %s, %s);"
						, sn, pointNo, deviceclass, ratePort[0], ratePort[1], protocol, commAddr, longitude, latitude));
			}
		}
		
		return sb.toString();
	}

}
