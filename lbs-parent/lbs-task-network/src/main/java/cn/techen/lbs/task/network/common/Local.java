package cn.techen.lbs.task.network.common;

public final class Local {
	
	public final static String PROJECT = "LBS-TASK-NETWORK";
	
	public final static int LOADMILLIS = 60000;
	
	public final static int INTERVALMILLIS = 200;
	
	public final static int SEEKRELAYSECTORRANGE = 1;//seeking sector range is between -1 and 1.
	
	public final static int SEEKREPEATERDISTRICTXRANGE = 20;//each district x is 100m. so seeking district x range is between -2km and 2km.
	
	public final static int SEEKRELAYDISTRICTXRANGE = 5;//each district x is 100m. so seeking district x range is between -0.5km and 0.5km.
	
	public final static int WRITETIMES = 8;
	
	public final static long TIMEOUT = 10000;
	
	public final static int WRITETIMES_RELAY = 1;
}
