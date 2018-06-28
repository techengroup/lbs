package cn.techen.lbs.business.common;

import cn.techen.lbs.data.protocol.AbstractSQL;

public class BusinessUtil {
	
	/**
	 * 地球半径（单位：m）
	 */
	private static final double EARTH_RADIUS = 6378137.0;
	
	/**
	 * New Data
	 * @param dataClass
	 * @return
	 * @throws Exception
	 */
	public static AbstractSQL newSql(String dataClass) throws Exception {				
		Class<?> c = Class.forName(String.format("cn.techen.lbs.data.protocol.%s", dataClass));
		return (AbstractSQL) c.newInstance();
	}

	/**
	 * 获取两点之间的距离（单位：m）
	 * @param lng_a
	 * @param lat_a
	 * @param lng_b
	 * @param lat_b
	 * @return
	 */
	public static double distance(double lng_a, double lat_a, double lng_b, double lat_b) {
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
	public static double angle(double lng_a, double lat_a, double lng_b, double lat_b) {
        double y = Math.sin(lng_b-lng_a) * Math.cos(lat_b);
        double x = Math.cos(lat_a)*Math.sin(lat_b) - Math.sin(lat_a)*Math.cos(lat_b)*Math.cos(lng_b-lng_a);
        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        if(brng < 0)
            brng = brng +360;
        return brng;
    }
	
//	public static String distance0(double lng_a, double lat_a, double distance, double angle) {
//		double dx = distance*1000*Math.sin(Math.toRadians(angle));
//		double dy= distance*1000*Math.cos(Math.toRadians(angle));
//		double bjd=(dx/A.Ed+A.m_RadLo)*180./Math.PI;
//		double bwd=(dy/A.Ec+A.m_RadLa)*180./Math.PI;
//		String lnglat=bjd+","+bwd;
//		return lnglat;
//	}

	public static void main(String[] args) {
		double d = distance(30.3304137006,120.0908457386,30.3310619233,120.0915645706);
		System.out.println(d);
		
		double a = angle(30.3304137006,120.0908457386,30.3310619233,120.0915645706);
		System.out.println(a);
	}

}
