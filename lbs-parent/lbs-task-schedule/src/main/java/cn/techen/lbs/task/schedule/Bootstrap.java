package cn.techen.lbs.task.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.task.schedule.common.Local;


public class Bootstrap {
	private static Logger log = LoggerFactory.getLogger(Local.PROJECT);
	
	public void start() {
		String rootPath = System.getenv("LBS_HOME");
		log.info("==================================================ENV Path:{}", rootPath);
	}
	
}
