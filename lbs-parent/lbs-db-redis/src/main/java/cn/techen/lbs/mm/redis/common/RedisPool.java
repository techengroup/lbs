package cn.techen.lbs.mm.redis.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	private static Logger log = LoggerFactory.getLogger(Local.PROJECT);
	private static RedisPool redisPool = null;
	private static JedisPool realPool = null;
	
	static {		  
		try {
			Properties prop = loadPropertiesFile("redis.properties");
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(Integer.valueOf(prop.getProperty("redis.maxTotal").trim()));
			config.setMaxIdle(Integer.valueOf(prop.getProperty("redis.maxIdle").trim()));
			config.setMaxWaitMillis(Integer.valueOf(prop.getProperty("redis.maxWait").trim()));
			config.setTestOnBorrow(true); 
	        config.setTestOnReturn(true);
	        
	        String host = prop.getProperty("redis.host").trim();
	        Integer port = Integer.valueOf(prop.getProperty("redis.port").trim());
	        realPool = new JedisPool(config, host, port);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Create redis pool faild...");
		}
	}
	
	/**
	 * Get redis pool
	 * @return
	 */
	public static RedisPool getInstance() {
		if(redisPool == null) {
			synchronized (RedisPool.class) {
				if (redisPool == null) {
					redisPool = new RedisPool();
				}
			}
		}
		return redisPool;
	}
	
	/**
	 * Get Resource
	 * @return
	 * @throws Exception
	 */
	public Jedis getResource() throws Exception {
		Jedis jedis = realPool.getResource();
	    return jedis;
	}  
	
	/**
	 * Return Resource
	 * @param jedis
	 */
	public void returnResource(Jedis jedis) {  
	    if (jedis != null) {  
	         jedis.close();
	    }  
	}  
	
	/**
	 * Load mysql properties
	 * @param fullFile
	 * @return
	 */
	private static Properties loadPropertiesFile(String fullFile) {
		String rootPath = null;
		if (null == fullFile || fullFile.equals("")) {
			throw new IllegalArgumentException("Properties file path can not be null" + fullFile);
		}
//		rootPath = RedisPool.class.getClassLoader().getResource("").getPath();
//		log.info("Root Path:{}", rootPath);
		rootPath = System.getenv("AMI_HOME");
		log.info("ENV Path:{}", rootPath);
//		rootPath = new File(rootPath).getParent();
//		log.info("Root Parent Path:{}", rootPath);
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = new FileInputStream(new File(rootPath + File.separator + fullFile));
			p = new Properties();
			p.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return p;
	}
}
