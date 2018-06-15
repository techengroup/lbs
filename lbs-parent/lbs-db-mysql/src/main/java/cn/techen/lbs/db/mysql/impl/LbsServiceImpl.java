package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.LbsService;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class LbsServiceImpl implements LbsService {

	@Override
	public LBS selectByKey(Object key) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commaddr, moduleaddr, logicaddr, protocol, channel, longitude, latitude, ip, port, ip1, port1 from PRM_LBS");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			LBS lbs = null;
			while (rs.next()) {
				lbs = new LBS();
				lbs.setId(rs.getInt("id"));
				lbs.setCommaddr(rs.getString("commaddr"));
				lbs.setModuleaddr(rs.getString("moduleaddr"));
				lbs.setLogicaddr(rs.getString("logicaddr"));
				lbs.setProtocol(rs.getInt("protocol"));
				lbs.setChannel(rs.getInt("channel"));
				lbs.setLatitude(rs.getDouble("longitude"));
				lbs.setLatitude(rs.getDouble("latitude"));
				lbs.setIp(rs.getString("ip"));
				lbs.setPort(rs.getInt("port"));
				lbs.setIp1(rs.getString("ip1"));
				lbs.setPort1(rs.getInt("port1"));
			}
			return lbs;
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
	public List<LBS> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(LBS entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(LBS entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(LBS entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LBS selectByTime(Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commaddr, moduleaddr, logicaddr, protocol, channel, longitude, latitude, ip, port, ip1, port1 from PRM_LBS ");
			ddl.append(" where mdfon>?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			ResultSet rs = stmt.executeQuery();
			LBS lbs = null;
			while (rs.next()) {
				lbs = new LBS();
				lbs.setId(rs.getInt("id"));
				lbs.setCommaddr(rs.getString("commaddr"));
				lbs.setModuleaddr(rs.getString("moduleaddr"));
				lbs.setLogicaddr(rs.getString("logicaddr"));
				lbs.setProtocol(rs.getInt("protocol"));
				lbs.setChannel(rs.getInt("channel"));
				lbs.setLatitude(rs.getDouble("longitude"));
				lbs.setLatitude(rs.getDouble("latitude"));
				lbs.setIp(rs.getString("ip"));
				lbs.setPort(rs.getInt("port"));
				lbs.setIp1(rs.getString("ip1"));
				lbs.setPort1(rs.getInt("port1"));
			}
			return lbs;
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
