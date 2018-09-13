package cn.techen.lbs.task.schedule;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.task.schedule.common.Local;


public class Bootstrap {
	private static Logger log = LoggerFactory.getLogger(Local.PROJECT);
	
	public void start() {
		Thread env = new Thread(new EnvThread());
		env.start();		
	}
	
	protected class EnvThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(20000);
					
//					String rootPath = System.getenv("LBS_HOME");
//					log.info("==LBS_HOME:{}", rootPath);
//					
//					rootPath = System.getenv("JAVA_HOME");
//					log.info("=JAVA_HOME:{}", rootPath);
//					
//					rootPath = System.getenv("AMI_HOME"); 
//					log.info("=AMI_HOME:{}", rootPath);
//					
//					rootPath = System.getenv("KARAF_HOME");
//					log.info("=KARAF_HOME:{}", rootPath);
//					
//					rootPath = System.getenv("PATH");
//					log.info("=PATH:{}", rootPath);
//					
//					rootPath = System.getenv("LANG");
//					
//					rootPath = System.getenv("SHELL");
//					log.info("=SHELL:{}", rootPath);
//					
//					rootPath = System.getenv("MAIL"); 
//					log.info("=MAIL:{}", rootPath);
//					
//					rootPath = System.getenv("SSH_TTY"); 
//					log.info("=SSH_TTY:{}", rootPath);
//					
//					rootPath = System.getenv("SSH_CLIENT"); 
//					log.info("=SSH_CLIENT:{}", rootPath);
					
					log.info("=lbs.home:{}", System.getProperty("lbs.home"));
					
					Properties properties = System.getProperties();
					for (java.util.Map.Entry<Object, Object> entry : properties.entrySet()) {
						log.info("========p========key:{}", entry.getKey());
						log.info("========p========val:{}", entry.getValue());
					}
					
					Map<String, String> map = System.getenv();  
					for (java.util.Map.Entry<String, String> entry : map.entrySet()) {
						log.info("========evn========key:{}", entry.getKey());
						log.info("=========evn=======val:{}", entry.getValue());
					}
					
					log.info("============karaf=============karaf.etc:{}", System.getProperty("karaf.etc"));

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
}
