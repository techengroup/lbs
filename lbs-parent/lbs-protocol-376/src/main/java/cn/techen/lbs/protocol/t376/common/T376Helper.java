package cn.techen.lbs.protocol.t376.common;

import java.lang.reflect.Constructor;

import cn.techen.lbs.protocol.AbstractData;

public class T376Helper {
	
	private static volatile int PFC = 0;
	
	/**
	 * New Data
	 * @param className
	 * @throws Exception
	 */
	public static AbstractData newData(String className) throws Exception {
		Class<?> c = Class.forName(String.format("cn.techen.lbs.protocol.t376.type.%s", className));
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
		Class<?> c = Class.forName(String.format("cn.techen.lbs.protocol.t376.type.%s", className));
        Constructor<?> constructor = c.getConstructor(Integer.class, String.class, Integer.class, String.class);
		return (AbstractData) constructor.newInstance(length, format, asc, tableColumn);
	}
	
	/**
	 * Get Class Name
	 * @param data_Class
	 * @return
	 */
	public static String getClassName(String data_Class)
    {    	
		if (data_Class.equals("DateTime_E")) {
			return "cn.techen.lbs.protocol.t376.type.Null_Data";
		}
//		switch (data_Class) {
//		case "DateTime_E":
//			return "cn.techen.lbs.protocol.t376.type.Null_Data";
//		case ARRAY:
//			break;
//		case BCD:
//			break;
//		case BCD_STRING:
//			return "com.techenframework.protocol.dlms.dataclass.BCD_String";
//		case BIT_STRING:
//			break;
//		case BOOLEAN:
//			break;
//		case COMPACT_ARRAY:
//			break;
//		case DATE:
//			break;
//		case DATE_TIME:
//			return "com.techenframework.protocol.dlms.dataclass.Date_Time";
//		case DONT_CARE:
//			break;
//		case DOUBLE_LONG:
//			break;
//		case DOUBLE_LONG_UNSIGNED:
//			return "com.techenframework.protocol.dlms.dataclass.Double_Long_Unsigned";
//		case ENUM:
//			break;
//		case FLOAT32:
//			break;
//		case FLOAT64:
//			break;
//		case FLOATING_POINT:
//			break;
//		case INTEGER:
//			break;
//		case IP4_STRING:
//			break;
//		case IP6_STRING:
//			break;
//		case LONG:
//			break;
//		case LONG64:
//			break;
//		case LONG64_UNSIGNED:
//			break;
//		case LONG_UNSIGNED:
//			return "com.techenframework.protocol.dlms.dataclass.Long_Unsigned";	
//		case OCTET_STRING:
//			return "com.techenframework.protocol.dlms.dataclass.Octet_String";
//		case STRUCTURE:
//			return "com.techenframework.protocol.dlms.dataclass.Structure";
//		case TIME:
//			break;
//		case UNSIGNED:
//			return "com.techenframework.protocol.dlms.dataclass.Unsigned";
//		case VISIBLE_STRING:
//			break;
//		default:
//			break;		
//		}
    	
		return null;
    }
	
	public static int sequence() {
		int s = PFC % 16;
		if (PFC >= 256) {
			PFC = 0;
		}
		PFC++;
		return s;
	}
}
