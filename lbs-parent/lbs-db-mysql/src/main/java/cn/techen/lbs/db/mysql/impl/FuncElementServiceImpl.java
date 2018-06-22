package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.FuncService;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class FuncElementServiceImpl implements FuncService {

	@Override
	public Map<String, String> selectAll() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select protocol, code, classname, length, format, rank, display from PTL_FN f ");
			ddl.append("left join PTL_FN_ELEMENT e on f.elements like '%e.id%' ");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String key = rs.getInt(1) + ":" + rs.getString(2);
				String val = rs.getString(3) + "," + rs.getInt(4) + "," + rs.getString(5) + "," + rs.getInt(6) + "," + rs.getInt(7);
				
				String oVal = map.get(key);
				if (oVal != null) {
					map.put(key, oVal + ":" + val);
				} else {				
					map.put(key, val);
				}
			}
			return map;
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
	public Map<String, String> selectByTime(Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select protocol, code, classname, length, format, rank, display from PTL_FN f ");
			ddl.append("left join PTL_FN_ELEMENT e on f.elements like '%e.id%' ");
			ddl.append("where f.savetime > ? or e.savetime > ?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(time.getTime()));
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String key = rs.getInt(1) + ":" + rs.getString(2);
				String val = rs.getString(3) + "," + rs.getInt(4) + "," + rs.getString(5) + "," + rs.getInt(6) + "," + rs.getInt(7);
				
				String oVal = map.get(key);
				if (oVal != null) {
					map.put(key, oVal + ":" + val);
				} else {				
					map.put(key, val);
				}
			}
			return map;
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

}
