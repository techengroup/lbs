package cn.techen.lbs.db.common;

import cn.techen.lbs.db.model.LBS;

public class Global {

	public static volatile boolean DBReady = false;
	
	public static volatile boolean G4Ready = false;

	public static volatile boolean DATAReady = false;//基站所需运行基础数据满足
	
	public static volatile boolean LoraReady = false;//基站通道改变
	
	public static volatile boolean GISReady = false;//基站经度和纬度满足	
	
	public static volatile LBS lbs = null;

}
