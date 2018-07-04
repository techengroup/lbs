package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.FnService;
import cn.techen.lbs.db.model.Fn;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class FnServiceImpl implements FnService {

	@Override
	public Fn selectByKey(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fn> selectAll() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Fn> list = new ArrayList<Fn>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select protocol, direction, operation, fn, name, elements, titles from KNL_FN");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fn fn = new Fn();
				fn.setProtocol(rs.getInt("protocol"));
				fn.setDirection(rs.getInt("direction"));
				fn.setOperation(rs.getString("operation"));
				fn.setFunction(rs.getString("fn"));
				fn.setName(rs.getString("name"));
				fn.setElements(rs.getString("elements"));
				fn.setTitles(rs.getString("titles"));
				list.add(fn);
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
	public int save(Fn entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(Fn entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(Fn entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Fn> selectByTime(Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Fn> list = new ArrayList<Fn>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select protocol, direction, operation, fn, name, elements, titles from KNL_FN ");
			ddl.append("where savetime>?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fn fn = new Fn();
				fn.setProtocol(rs.getInt("protocol"));
				fn.setDirection(rs.getInt("direction"));
				fn.setOperation(rs.getString("operation"));
				fn.setFunction(rs.getString("fn"));
				fn.setName(rs.getString("name"));
				fn.setElements(rs.getString("elements"));
				fn.setTitles(rs.getString("titles"));
				list.add(fn);
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

}
