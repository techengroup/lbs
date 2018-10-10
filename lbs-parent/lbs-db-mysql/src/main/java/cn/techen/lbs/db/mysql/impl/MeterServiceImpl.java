package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.MeterService;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class MeterServiceImpl implements MeterService {
	
	@Override
	public Meter selectByKey(Object key) {
		return null;
	}

	@Override
	public List<Meter> selectAll() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Meter> list = new ArrayList<Meter>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, status, pointno, commaddr, protocol, moduleprotocol, longitude, latitude, route from PRM_NODE where deviceclass=1");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Meter meter = new Meter();
				meter.setId(rs.getInt("id"));
				meter.setStatus(rs.getInt("status"));
				meter.setPointno(rs.getInt("pointno"));
				meter.setCommaddr(rs.getString("commaddr"));
				meter.setProtocol(rs.getInt("protocol"));
				meter.setModuleprotocol(rs.getInt("moduleprotocol"));
				meter.setLongitude(rs.getDouble("longitude"));
				meter.setLatitude(rs.getDouble("latitude"));
				meter.setRoute(rs.getString("route"));
				list.add(meter);
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
	public int save(Meter entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(Meter entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(Meter entity) {		
		return 0;
	}
		
	@Override
	public List<Meter> selectByTime(Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Meter> list = new ArrayList<Meter>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, status, pointno, commaddr, protocol, moduleprotocol, longitude, latitude, route from PRM_NODE ");
			ddl.append("where deviceclass=1 and (crton>? or mdfon>?)");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(time.getTime())); 
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Meter meter = new Meter();
				meter.setId(rs.getInt("id"));
				meter.setStatus(rs.getInt("status"));
				meter.setPointno(rs.getInt("pointno"));
				meter.setCommaddr(rs.getString("commaddr"));
				meter.setProtocol(rs.getInt("protocol"));
				meter.setModuleprotocol(rs.getInt("moduleprotocol"));
				meter.setLongitude(rs.getDouble("longitude"));
				meter.setLatitude(rs.getDouble("latitude"));
				meter.setRoute(rs.getString("route"));
				list.add(meter);
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
	public List<Meter> selectGIS() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Meter> list = new ArrayList<Meter>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, longitude, latitude from PRM_NODE ");
			ddl.append("where deviceclass=1 and distance is null");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Meter meter = new Meter();
				meter.setId(rs.getInt("id"));
				meter.setLongitude(rs.getDouble("longitude"));
				meter.setLatitude(rs.getDouble("latitude"));
				list.add(meter);
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
	public int updateGIS(List<Meter> entitys) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			for (Meter meter : entitys) {
				stmt.addBatch(String.format("update PRM_NODE set distance=%f, angle=%f, sector=%d, districtx=%d, districty=%d, mdfon=NOW() where id=%d"
						, meter.getDistance(), meter.getAngle(), meter.getSector(), meter.getDistrictX(), meter.getDistrictY(), meter.getId()));
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
