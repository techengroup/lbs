package cn.techen.lbs.task.network.common;

public final class Local {
	
	public final static String PROJECT = "LBS-TASK-NETWORK";
	
	public final static int LOADMILLIS = 60000;
	
	public final static int INTERVALMILLIS = 200;
	
	public final static int SectorRange = 1;
	
	public final static int XRange = 40;
	
	public final static int BeforeXRange = 3;
	
	public final static int WRITETIMES = 8;
	
	public final static long TIMEOUT = 10000;
	
	public final static int WRITETIMES_RELAY = 1;
	
	public final static int MINFAIL = 3;
	
	public final static int DFACTOR = 50;
	
	public final static int AFACTOR = 15;
	
	public final static int RFACTOR = 70;	
	
	public final static float APOINT = 7.5F;
	
	public static float caculate(double distance, float angle, int rssi) {
		float dFactor = (float) ((distance % DFACTOR) / DFACTOR);
		float sFactor = (float) rssi /RFACTOR;
		float aFactor = angle % AFACTOR;

		if (aFactor > APOINT) {
			aFactor = 2 - (aFactor / APOINT);
		} else {
			aFactor = aFactor / APOINT;
		}

		return Math.round((dFactor + aFactor + sFactor) * 100) * 0.01f;
	}
}
