package cn.techen.lbs.db.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.techen.lbs.db.model.LBS;

public class Global {

	public static volatile boolean DBReady = false;//关系数据库满足
	
	public static volatile boolean MBReady = false;//内存数据库满足
	
	public static volatile boolean G4Ready = false;//GPRS模块满足
	
	public static volatile boolean LoraReady = false;//基站Lora模块满足

	public static volatile boolean DATAReady = false;//基站所需运行基础数据满足
	
	public static volatile boolean GISReady = false;//基站经度和纬度满足
	
	public static volatile boolean ChannelReady = false;//基站通道满足
	
	public static volatile LBS lbs = null;//基站档案
	
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

}
