package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.ParamService;
import cn.techen.lbs.db.model.Param;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class ParamServiceImpl implements ParamService {
	
	@Override
	public Param selectByKey(Object key) {
		return null;
	}

	@Override
	public List<Param> selectAll() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Param> list = new ArrayList<Param>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, key0, value0, remark from RUN_PARAM ");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				Param param = new Param();
				param.setId(rs.getInt("id"));
				param.setKey(rs.getString("key0"));
				param.setValue(rs.getString("value0"));
				param.setDesc(rs.getString("remark"));
				list.add(param);
			}
			return list;
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
	public int save(Param entity) {		
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(Param entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(Param entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Param> selectByTime(Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Param> list = new ArrayList<Param>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, key0, value0, remark from RUN_PARAM ");
			ddl.append("where savetime>?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				Param param = new Param();
				param.setId(rs.getInt("id"));
				param.setKey(rs.getString("key0"));
				param.setValue(rs.getString("value0"));
				param.setDesc(rs.getString("remark"));
				list.add(param);
			}
			return list;
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
	public int updateValue(String key, String value) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append(String.format("update RUN_PARAM set value0='%s' where key0='%s'", key, value));			
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
