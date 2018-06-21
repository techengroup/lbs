package cn.techen.lbs.business.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionHelper {
	
	/**
	 * 实例化对象
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object newInstance (String className) 
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
