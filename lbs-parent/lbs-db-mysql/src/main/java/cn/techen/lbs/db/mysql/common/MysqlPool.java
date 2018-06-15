package cn.techen.lbs.db.mysql.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

public class MysqlPool {
	private static Logger log = (Logger) LoggerFactory.getLogger(Local.PROJECT);
	private static MysqlPool mysqlPool = null;
	private static DruidDataSource druidDataSource = null;

	static {
		Properties properties = loadPropertiesFile("mysql.properties");
		try {
			druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties); // DruidDataSrouce工厂模式
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Create mysql pool faild.");
		}
	}

	/**
	 * Connection Pool -- Singleton
	 * @return
	 */
	public static MysqlPool getInstance() {		
		if (mysqlPool == null) {
			synchronized (MysqlPool.class) {
				if (mysqlPool == null) {
					mysqlPool = new MysqlPool();
				}
			}
		}
		return mysqlPool;
	}

	/**
	 * Get mysql connection
	 * @return
	 * @throws SQLException
	 */
	public DruidPooledConnection getConnection() throws SQLException {
		return druidDataSource.getConnection();
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