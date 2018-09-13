package cn.techen.lbs.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.data.manager.GISCalc;
import cn.techen.lbs.data.manager.RunData;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private RunData runData;
	private GISCalc gisCalc;

	/**
	 * Data load service start
	 */
	public void start() {
		logger.info("System run data loading is starting......");			
		Thread myThread = new Thread(runData);
		myThread.start();
		
		logger.info("Meter GIS caculating is starting......");			
		Thread gisThread = new Thread(gisCalc);
		gisThread.start();
	}

	public void setRunData(RunData runData) {
		this.runData = runData;
	}

	public void setGisCalc(GISCalc gisCalc) {
		this.gisCalc = gisCalc;
	}

}
