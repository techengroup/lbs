package cn.techen.lbs.data.common;

import java.util.Date;

public final class Local {
	
	public final static String PROJECT = "LBS-DATA-LOAD";
	
	public final static int INTERVALMILLIS = 60000;
	
	public volatile static Date LASTTIME = new Date();
	
}
