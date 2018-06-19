package cn.techen.lbs.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.store.common.Local;

public class Entrance {
	private static final Logger log = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);
	
	private Redis2Mysql redis2Mysql;
	
	/**
	 * Data load service start
	 */
	public void start() {
		log.info("Data load is starting......");			
		Thread redisThread = new Thread(redis2Mysql);
		redisThread.start();
	}

	public void setRedis2Mysql(Redis2Mysql redis2Mysql) {
		this.redis2Mysql = redis2Mysql;
	}
}
