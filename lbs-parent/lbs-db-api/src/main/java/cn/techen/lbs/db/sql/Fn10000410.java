package cn.techen.lbs.db.sql;

import java.util.Queue;

import cn.techen.lbs.db.sql.AbstractSQL;

public class Fn10000410 extends AbstractSQL {

	@Override
	public String handle(Object id, Queue<Object> datas) {
		StringBuffer sb = new StringBuffer();
		int len = Integer.parseInt(datas.poll().toString());
		for (int i = 0; i < len; i++) {
			int sn = Integer.parseInt(datas.poll().toString());
			int pointNo = Integer.parseInt(datas.poll().toString());
			String[] ratePort  = datas.poll().toString().split(":");
			int protocol = Integer.parseInt(datas.poll().toString());
			String commAddr = datas.poll().toString();
			int tariffCount = Integer.parseInt(datas.poll().toString());
			String[] dataDigit  = datas.poll().toString().split(":");
			String[] customerClass = datas.poll().toString().split(":");
			double longitude = Double.parseDouble(datas.poll().toString());
			double latitude = Double.parseDouble(datas.poll().toString());			
			
//			sb.append("ON DUPLICATE KEY update mdfon=NOW();");		
			
			if (pointNo == 0) {
				sb.append("update prm_meter set status=-1, signalstrength=0, failtimes=0"
						+ ", grade=0, relay=0, path='0/', parent=0, pathtype=0"
						+ ", mdfon=NOW(), regon=null, unregon=null where path like '%/" + sn + "/%';");
				sb.append("delete from log_network where route like '%," + commAddr + ",%';");
				sb.append("delete from log_report where route like '%," + commAddr + ",%';");				
				sb.append(String.format("delete from log_report where meterid=%d;", sn));
				sb.append(String.format("delete from data_event where meterid=%d;", sn));
				sb.append(String.format("delete from data_energy_month where meterid=%d;", sn));
				sb.append(String.format("insert IGNORE into log_exit(meterid, pointno, commaddr, logicaddr, protocol, moduleprotocol"
						+ ", baudrate, port, tariffcount, integercount, decimalcount, customerclass, customersubclass"
						+ ", longitude, latitude, distance, angle, sector, districtx, districty, relay, grade, parent"
						+ ", path, route, failtimes) select * from(select m.ID, m.PointNo, m.CommAddr, m.logicaddr"
						+ ", m.protocol, m.moduleprotocol, m.baudrate, m.port, m.tariffcount, m.integercount, m.decimalcount"
						+ ", m.customerclass, m.customersubclass, m.longitude, m.latitude, m.distance, m.angle, m.sector"
						+ ", m.districtx, m.districty, m.relay, m.grade, m.parent, m.path, n.Route, m.failtimes "
						+ "from prm_meter m JOIN log_network n on m.id=n.meterID where m.id=%d and n.result=1) as ex;", sn));
				sb.append(String.format("delete from log_network where meterid=%d;", sn));
				sb.append(String.format("delete from prm_meter where id=%d;", sn));
			} else {
				sb.append("insert IGNORE into prm_meter(id, pointno, baudrate, port, protocol, commaddr, tariffcount, integercount, decimalcount"
						+ ", customerclass, customersubclass, longitude, latitude) ");
				sb.append(String.format("values(%d, %d, %s, %s, %d, '%s', %d, %s, %s, %s, %s, %s, %s);"
						, sn, pointNo, ratePort[0], ratePort[1], protocol, commAddr, tariffCount, Integer.parseInt(dataDigit[1])+4, Integer.parseInt(dataDigit[2])+1
						, customerClass[0], customerClass[1], longitude, latitude));
			}
		}
		
		return sb.toString();
	}

}
