package cn.techen.lbs.db.mysql.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class GeneralServiceImpl implements GeneralService {

	@Override
	public List<Object> query(String sql) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			List<Object> datas = new ArrayList<Object>();
			
			conn = mp.getConnection();
			stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					datas.add(rs.getObject(i));
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
	public int save(String sql) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			String[] sqlArray = sql.split(";");
			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			
			for (String ddl : sqlArray) {
				stmt.addBatch(ddl);
			}
			
			return stmt.executeBatch()[0];
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
		
		return -1;
	}
}
