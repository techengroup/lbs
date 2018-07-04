package cn.techen.lbs.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;

public class Entrance {
	private static final Logger log = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);
	
	private Mysql2Redis mysql2Redis;
	private GIS gis;
	
	/**
	 * Data load service start
	 */
	public void start() {
		log.info("Data load is starting......");			
		Thread myThread = new Thread(mysql2Redis);
		myThread.start();
		
		log.info("GIS is starting......");			
		Thread gisThread = new Thread(gis);
		gisThread.start();
	}

	public void setMysql2Redis(Mysql2Redis mysql2Redis) {
		this.mysql2Redis = mysql2Redis;
	}

	public void setGis(GIS gis) {
		this.gis = gis;
	}

}
