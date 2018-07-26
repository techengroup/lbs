package cn.techen.lbs.db.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.techen.lbs.db.model.LBS;

public class Global {

	public static volatile boolean DBReady = false;
	
	public static volatile boolean G4Ready = false;
	
	public static volatile boolean LoraReady = false;//基站Lora模块满足

	public static volatile boolean DATAReady = false;//基站所需运行基础数据满足
	
	public static volatile boolean GISReady = false;//基站经度和纬度满足
	
	public static volatile boolean ChannelReady = false;//基站通道满足
	
	public static volatile LBS lbs = null;//基站档案
	
	/**
	 * 系统运行参数 包括：
	 * LastEventIndex : 最后一次事件上报索引
	 * LoraSignalThreshold : Lora信号零界点
	 * LoraCOM : Lora模块串口名称
	 */
	public static volatile Map<String, Object> RunParams = new ConcurrentHashMap<String, Object>();
	
	/**
	 * 临时最后一次事件索引
	 */
	public static volatile int TempLastEventIndex = 0;

}
