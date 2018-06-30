package cn.techen.lbs.protocol.t376.common;

public class Local {
	
	public static final String PROJECT = "LBS-PROTOCOL-376";
	
	public static final Integer CODE = 100;
	
	public static final String NAME = "Techen_376.1";
	
	public static final String VERSION = "1.0";
	
	private static volatile int PFC = 0;
	
	public static int sequence() {
		int s = PFC % 16;
		if (PFC >= 256) {
			PFC = 0;
		}
		PFC++;
		return s;
	}
}
