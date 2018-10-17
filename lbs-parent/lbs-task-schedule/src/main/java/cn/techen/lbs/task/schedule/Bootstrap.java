package cn.techen.lbs.task.schedule;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.task.schedule.common.Local;


public class Bootstrap {
	private static Logger log = LoggerFactory.getLogger(Local.PROJECT);
	
	private MTaskService<byte[]> mByteService;

	private MTaskService<ProtocolConfig> mTaskService;
	
	
	public void start() {
		mByteService.lpush(MTaskService.QUEUE_4G_TANS_LORA, new byte[] {1,2,3,4,5,6,7,8,9});
		byte[] a = mByteService.rpop(MTaskService.QUEUE_4G_TANS_LORA);
		for (byte b : a) {
			log.info("Byte:{}", b);
		}
		
		ProtocolConfig pConfig = new DefaultProtocolConfig();
		pConfig.setCommAddr("00000006");
		mTaskService.lpush(MTaskService.QUEUE_4G_SEND, pConfig);
		
		ProtocolConfig pConfig1 = mTaskService.rpop(MTaskService.QUEUE_4G_SEND);
		log.info("Config:{}", pConfig1.getCommAddr());
		
		
//		Thread env = new Thread(new EnvThread());
//		env.start();		
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

	public MTaskService<byte[]> getmByteService() {
		return mByteService;
	}

	public void setmByteService(MTaskService<byte[]> mByteService) {
		this.mByteService = mByteService;
	}

	public MTaskService<ProtocolConfig> getmTaskService() {
		return mTaskService;
	}

	public void setmTaskService(MTaskService<ProtocolConfig> mTaskService) {
		this.mTaskService = mTaskService;
	}
	
	
}
