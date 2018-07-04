package cn.techen.lbs.db.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.techen.lbs.db.sql.AbstractSQL;

public class GlobalUtil {
	
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
		Class<?> c = Class.forName(String.format("cn.techen.lbs.db.sql.%s", dataClass));
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
			var = (int) Math.ceil(angle / 15);
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
		var = (int) Math.floor(distance / 50);
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

		var = (int) Math.floorMod((int) angle, 15) / 5;
		return var;
	}
	
	/**
	 * 实例化对象
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object newInstance(String className) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		Class<?> c = Class.forName(className);		
		return c.newInstance();
	}
	
	/**
	 * 获取私有成员变量的值
	 * @param instance
	 * @param fieldName
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
    public static Object getValue(Object instance, String fieldName)
            throws IllegalAccessException, NoSuchFieldException {

        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true); // 参数值为true，禁止访问控制检查
        return field.get(instance);
    }

    /**
     * 设置私有成员变量的值
     * @param instance
     * @param fileName
     * @param value
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setValue(Object instance, String fileName, Object value)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        Field field = instance.getClass().getDeclaredField(fileName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    /**
     * 访问私有方法
     * @param instance
     * @param methodName
     * @param classes
     * @param objects
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object callMethod(Object instance, String methodName, @SuppressWarnings("rawtypes") Class[] classes, Object[] objects)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        Method method = instance.getClass().getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        return method.invoke(instance, objects);
    }
    
}
