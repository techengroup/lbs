package cn.techen.lbs.db.mysql.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.BaseService;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class BaseServiceImpl implements BaseService {

	@Override
	public List<Object> query(String sqlTemplate, Object[] conditions) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			String sql = String.format(sqlTemplate, conditions);
			int count = 0;
			List<Object> datas = new ArrayList<Object>();
			
			conn = mp.getConnection();
			stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					datas.add(rs.getObject(count));
				}				
			}
			
			return datas;
		} catch (SQLException e) {
			e.printStackTrace();
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
		return null;
	}

	@Override
	public int save(String sqlTemplate, Object[] datas, Object[] conditions) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			conn = mp.getConnection();
			stmt = conn.createStatement();			
			return stmt.executeUpdate(sqlTemplate);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
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
