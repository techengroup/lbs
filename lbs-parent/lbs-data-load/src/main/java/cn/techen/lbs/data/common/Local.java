package cn.techen.lbs.data.common;

import java.util.Date;

public final class Local {
	
	public final static String PROJECT = "LBS-DATA-LOAD";
	
	/**
	 * 地球半径（单位：m）
	 */
	private final static double EARTH_RADIUS = 6378137.0;
	
	public final static int INTERVALMILLIS = 60000;
	
	public volatile static Date LASTTIME = new Date();
	
	/**
	 * 获取两点之间的距离（单位：m）
	 * @param lat_a
	 * @param lng_a
	 * @param lat_b
	 * @param lng_b
	 * @return
	 */
	public static double distance(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = Math.toRadians(lat_a);
		double radLat2 = Math.toRadians(lat_b);
		double a = radLat1 - radLat2;

		double b = Math.toRadians(lng_a) - Math.toRadians(lng_b);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;

		return s;
	}
	
	/**
	 * 和中心点角度（东为0度开始，逆时针旋转）
	 * @param lat_a
	 * @param lng_a
	 * @param lat_b
	 * @param lng_b
	 * @return
	 */
	public static double angle(double lat_a, double lng_a, double lat_b, double lng_b) {
        double y = Math.sin(lat_b-lat_a) * Math.cos(lng_b);
        double x = Math.cos(lng_a)*Math.sin(lng_b) - Math.sin(lng_a)*Math.cos(lng_b)*Math.cos(lat_b-lat_a);
        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        if(brng < 0)
            brng = brng +360;
        return brng;
    }
	
	/**
	 * 通过角度计算扇区
	 * 
	 * @param angle
	 * @return
	 */
	public static int sector(double angle) {
		int var = 0;

		if ((angle - 0.0) == 0) {
			var = 1;
		} else {
			var = (int) Math.ceil(angle / 30);
		}
		return var;
	}

	/**
	 * 计算子扇区的X坐标
	 * 
	 * @param distance
	 * @return
	 */

	public static int districtX(double distance) {
		int var = 0;
		var = (int) Math.ceil(distance / 100);
		return var;
	}

	/**
	 * 计算子扇区的Y坐标
	 * 
	 * @param angle
	 * @return
	 */

	public static int districtY(double angle) {
		int var = 0;

		var = (int) Math.floorMod((int) angle, 30) / 10;
		return var;
	}
}
