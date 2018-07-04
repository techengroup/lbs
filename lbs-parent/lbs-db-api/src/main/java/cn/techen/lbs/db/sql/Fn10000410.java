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
			
			///TODO 根据经度纬度计算相关的参数
			double distance = 0;
			float angle = 0;
			int sector = 0;
			int districtx = 0;
			int districty = 0;	
			///TODO 根据经度纬度计算相关的参数
			
			sb.append("insert into prm_meter(id, pointno, baudrate, port, protocol, commaddr, tariffcount, integercount, decimalcount"
					+ ", customerclass, customersubclass, longitude, latitude, distance, angle, sector, districtx, districty) ");
			sb.append(String.format("values(%d, %d, %d, %d, %d, '%s', %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d) "
					, sn, pointNo, protocol, ratePort[0], ratePort[1], commAddr, tariffCount, dataDigit[0], dataDigit[1]
					, customerClass[0], customerClass[1], longitude, latitude, distance, angle, sector, districtx, districty));
			sb.append("ON DUPLICATE KEY update mdfon=NOW();");
//			sb.append(String.format("pointno= %d, protocol=%d, commaddr='%s', tariffcount=%d, integercount=%d, decimalcount=%d"
//					+ ", customerclass=%d, customersubclass=%d, longitude=%d, latitude=%d, distance=%d, distance=%d"
//					+ ", angle=%d, sector=%d, districtx=%d, districty=%d;", pointNo, protocol, commAddr, tariffCount
//					, dataDigit[0], dataDigit[1], customerClass[0], customerClass[1], longitude, latitude
//					, distance, angle, sector, districtx, districty));			
		}
		
		return sb.toString();
	}

}
