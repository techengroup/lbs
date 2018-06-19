package cn.techen.lbs.db.mysql.impl;

import java.sql.Statement;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.StoreService;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class StoreServiceImpl implements StoreService {

	@Override
	public int save(String sql) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			conn = mp.getConnection();
			stmt = conn.createStatement();			
			return stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
