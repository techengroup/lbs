package cn.techen.lbs.db.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.db.sql.AbstractSQL;

public class Global {
	
	/**
	 * GPRS模块
	 */
	public static volatile boolean G4Ready = false;//GPRS模块满足
	
	/**
	 * Lora模块
	 */
	public static volatile boolean LoraReady = false;//基站Lora模块满足

	/**
	 * 数据库mysql
	 */
	public static volatile boolean DBReady = false;//关系数据库满足
	
	/**
	 * 内存数据库Redis
	 */
	public static volatile boolean MBReady = false;//内存数据库满足

	/**
	 * 基站连接主站IP和端口信息
	 */
	public static volatile boolean IPReady = false;//连接主站信息满足
	
	/**
	 * 基站GIS信息
	 */
	public static volatile boolean GISReady = false;//基站经度和纬度满足
	
	/**
	 * 基站通道信息
	 */
	public static volatile boolean ChannelReady = false;//基站通道满足
	
	/**
	 * 基站档案
	 */
	public static volatile LBS lbs = null;//基站档案
	
	/**
	 * 中继设备起始Id
	 */
	public static volatile int RepeaterStartId = 1000000;
	
	/**
	 * 系统运行参数 包括：
	 * LastReportEventIndex : 最后一次上报事件索引
	 * LoraSignalThreshold : Lora信号零界点
	 * LoraCOM : Lora模块串口名称
	 */
	public static volatile Map<String, Object> RunParams = new ConcurrentHashMap<String, Object>();
	
	/**
	 * 事件记录超出65536数量
	 */
	public static volatile int EventRecoderOverAmount = 0;
	
	/**
	 * 当前主站招读事件起始索引
	 */
	public static volatile int CurrentReadEventStart = 0;
	
	/**
	 * 当前主站招读事件数量
	 */
	public static volatile int CurrentReadEventCount = 0;
	
	/**
	 * New Sql
	 * @param dataClass
	 * @return
	 * @throws Exception
	 */
	public static AbstractSQL newSql(String dataClass) throws Exception {				
		Class<?> c = Class.forName(String.format("cn.techen.lbs.db.sql.%s", dataClass));
		return (AbstractSQL) c.newInstance();
	}
	
	/**
	 * 时间转字符串	
	 * @param time
	 * @param format
	 * @return
	 */
	public static String date2String(Date time, String format) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		 return dateFormat.format(time);
	}
	
	/**
	 * 字符串转时间
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date string2Date(String time, String format) throws ParseException {
		 SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		 return dateFormat.parse(time);
	}
	
	/**
	 * 是否是第一天本月
	 * @return
	 */
	public static boolean isFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        
        if (1 == day) {
           return true;
        }
        return false;
    }

	
	/**
	 * 是否是最后一天本月
	 * @return
	 */
	public static boolean isLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        if (day == lastDay) {
			return true;
		}
        return false;
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
    
    /**
     * 获取堆棧信息
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try
        {
            throwable.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
			e.printStackTrace();
			return null;
        } finally {
            pw.close();
        }
    }

}
