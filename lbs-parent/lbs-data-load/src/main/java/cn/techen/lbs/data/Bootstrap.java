package cn.techen.lbs.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.data.manager.GIS2Net;
import cn.techen.lbs.data.manager.Mysql2Redis;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private Mysql2Redis mysql2Redis;
	private GIS2Net gis2Net;

	/**
	 * Data load service start
	 */
	public void start() {
		logger.info("Data load Module is starting......");			
		Thread myThread = new Thread(mysql2Redis);
		myThread.start();
		
		logger.info("GIS Caculate Module is starting......");			
		Thread gisThread = new Thread(gis2Net);
		gisThread.start();
	}

	public void setMysql2Redis(Mysql2Redis mysql2Redis) {
		this.mysql2Redis = mysql2Redis;
	}
	
	public void setGis2Net(GIS2Net gis2Net) {
		this.gis2Net = gis2Net;
	}


}
