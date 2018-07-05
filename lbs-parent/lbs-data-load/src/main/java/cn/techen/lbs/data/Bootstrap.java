package cn.techen.lbs.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.data.manager.GIS2Net;
import cn.techen.lbs.data.manager.Mysql2Redis;

public class Bootstrap {
	private static final Logger log = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);
	
	private Mysql2Redis mysql2Redis;
	private GIS2Net gis2Net;
	
	/**
	 * Data load service start
	 */
	public void start() {
		log.info("Data load is starting......");			
		Thread myThread = new Thread(mysql2Redis);
		myThread.start();
		
		log.info("GIS to net is starting......");			
		Thread gisThread = new Thread(gis2Net);
		gisThread.start();
	}

	public void setMysql2Redis(Mysql2Redis mysql2Redis) {
		this.mysql2Redis = mysql2Redis;
	}

	public void setGis(GIS2Net gis2Net) {
		this.gis2Net = gis2Net;
	}

}
