package cn.techen.lbs.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.data.manager.GISCalc;
import cn.techen.lbs.data.manager.DBMBDetect;
import cn.techen.lbs.data.manager.DataLoad;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private DBMBDetect dbmbDetect;
	private DataLoad dataLoad;
	private GISCalc gisCalc;

	/**
	 * Data load service start
	 */
	public void start() {
		logger.info("Detecting DB and MB is starting......");
		Thread detectThread = new Thread(dbmbDetect);
		detectThread.start();
		
		logger.info("Loading data is starting......");
		Thread loadThread = new Thread(dataLoad);
		loadThread.start();
		
		logger.info("Caculating meter information of gis is starting......");			
		Thread gisThread = new Thread(gisCalc);
		gisThread.start();
	}

	public void setDbmbDetect(DBMBDetect dbmbDetect) {
		this.dbmbDetect = dbmbDetect;
	}

	public void setDataLoad(DataLoad dataLoad) {
		this.dataLoad = dataLoad;
	}

	public void setGisCalc(GISCalc gisCalc) {
		this.gisCalc = gisCalc;
	}

}
