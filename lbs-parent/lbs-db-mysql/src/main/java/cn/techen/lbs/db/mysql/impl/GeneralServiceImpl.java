package cn.techen.lbs.db.mysql.impl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class GeneralServiceImpl implements GeneralService {
	
	@Override
	public boolean isConnected() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {			
			StringBuffer ddl = new StringBuffer();
			ddl.append("select 1 from DUAL");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();	
			if (rs.next()) {
				return true;
			}
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
		return false;
	}

	@Override
	public List<Object> query(String sql) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			List<Object> datas = new ArrayList<Object>();
			
			conn = mp.getConnection();
			stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql.replace("{LEN}", ""));
			int colCount = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= colCount; i++) {
					datas.add(rs.getObject(i));
				}				
			}
			
			if (sql.indexOf("{LEN}") >= 0) {
				int len = datas.size() / colCount;
				datas.add(0, len);
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

	@Override
	public int selectEventCount() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		int quantity = 0;
		try {			
			StringBuffer ddl = new StringBuffer();
			ddl.append("select COUNT(1) quantity from DATA_EVENT");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				quantity = rs.getInt("quantity");
			}
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
		return quantity;
	}

	@Override
	public int deleteEventRank0() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append(String.format("delete from DATA_EVENT order by savetime limit 65536"));			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			return stmt.executeUpdate(ddl.toString());
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
		return 0;
	}
}
