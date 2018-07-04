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
			
			sb.append("insert IGNORE into prm_meter(id, pointno, baudrate, port, protocol, commaddr, tariffcount, integercount, decimalcount"
					+ ", customerclass, customersubclass, longitude, latitude) ");
			sb.append(String.format("values(%d, %d, %d, %d, %d, '%s', %d, %d, %d, %d, %d, %d, %d);"
					, sn, pointNo, protocol, ratePort[0], ratePort[1], commAddr, tariffCount, dataDigit[0], dataDigit[1]
					, customerClass[0], customerClass[1], longitude, latitude));
//			sb.append("ON DUPLICATE KEY update mdfon=NOW();");		
			
			if (pointNo == 0) {
				sb.append("update prm_meter set status=-1, signalstrength=0, failtimes=0"
						+ ", grade=0, relay=0, path='0/', parent=0, pathtype=0"
						+ ", mdfon=NOW(), regon=null, unregon=null where path like '%/" + sn + "/%';");
				sb.append("delete from log_networking where route like '%," + commAddr + ",%';");
				sb.append("delete from log_report where route like '%," + commAddr + ",%';");				
				sb.append(String.format("delete from log_networking where meterid=%d;", sn));
				sb.append(String.format("delete from log_report where meterid=%d;", sn));
				sb.append(String.format("delete from data_event where meterid=%d;", sn));
				sb.append(String.format("delete from data_energy_month where meterid=%d;", sn));
				sb.append(String.format("delete from prm_meter where id=%d;", sn));
			}
		}
		
		return sb.toString();
	}

}
