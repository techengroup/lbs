package cn.techen.lbs.protocol.lora.common;

import java.lang.reflect.Constructor;

import cn.techen.lbs.protocol.AbstractData;

public class LoraHelper {
	
	/**
	 * New Data
	 * @param className
	 * @throws Exception
	 */
	public static AbstractData newData(String className) throws Exception {
		Class<?> c = Class.forName(String.format("cn.techen.lbs.protocol.lora.type.%s", className));
		Object obj = c.newInstance();
		return (AbstractData)obj;
	}
	
	/**
	 * New Data
	 * @param className
	 * @param length
	 * @param format
	 * @param asc
	 * @param tableColumn
	 * @return
	 * @throws Exception
	 */
	public static AbstractData newData(String className, Integer length, String format, Integer asc, String tableColumn) throws Exception {				
		Class<?> c = Class.forName(String.format("cn.techen.lbs.protocol.lora.type.%s", className));
        Constructor<?> constructor = c.getConstructor(Integer.class, String.class, Integer.class, String.class);
		return (AbstractData) constructor.newInstance(length, format, asc, tableColumn);
	}
	
}
