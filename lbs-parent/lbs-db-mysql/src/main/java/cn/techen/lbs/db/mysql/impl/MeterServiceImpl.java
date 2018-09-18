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
import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.db.model.Sector;
import cn.techen.lbs.db.mysql.common.MysqlPool;
import cn.techen.lbs.db.common.DataConfig.ENERGY;

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
			ddl.append("select id, status, pointno, commaddr, logicaddr, protocol, moduleprotocol, longitude, latitude from PRM_METER");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Meter meter = new Meter();
				meter.setId(rs.getInt("id"));
				meter.setStatus(rs.getInt("status"));
				meter.setPointno(rs.getInt("pointno"));
				meter.setCommaddr(rs.getString("commaddr"));
				meter.setLogicaddr(rs.getString("logicaddr"));
				meter.setProtocol(rs.getInt("protocol"));
				meter.setModuleprotocol(rs.getInt("moduleprotocol"));
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
			ddl.append("select id, status, pointno, commaddr, logicaddr, protocol, moduleprotocol, longitude, latitude from PRM_METER ");
			ddl.append("where crton>? or mdfon>?");
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
				meter.setLogicaddr(rs.getString("logicaddr"));
				meter.setProtocol(rs.getInt("protocol"));
				meter.setModuleprotocol(rs.getInt("moduleprotocol"));
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
	public List<Meter> selectGIS() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Meter> list = new ArrayList<Meter>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, longitude, latitude from PRM_METER ");
			ddl.append("where distance is null");
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
				stmt.addBatch(String.format("update PRM_METER set distance=%f, angle=%f, sector=%d, districtx=%d, districty=%d, mdfon=NOW() where id=%d"
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
	
	@Override
	public Sector selectQuantity(int s, int x) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select a.sector, a.districtX, a.total, c.fail from ");
			ddl.append("(select sector, districtX, COUNT(1) total from prm_meter ");
			ddl.append("where sector=? and districtX=? group by sector, districtX) a LEFT JOIN ");
			ddl.append("(select sector, districtX, COUNT(1) fail from prm_meter ");
			ddl.append("where sector=? and districtX=? and status = 0 group by sector, districtX) c ");
			ddl.append("on a.sector = c.sector and a.districtX = c.districtX");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());	
			stmt.setInt(1, s);
			stmt.setInt(2, x);
			stmt.setInt(3, s);
			stmt.setInt(4, x);
			ResultSet rs = stmt.executeQuery();
			Sector sector = null;
			
			while (rs.next()) {
				sector = new Sector();
				sector.setSector(rs.getInt("sector"));
				sector.setDistrictX(rs.getInt("districtX"));
				sector.setTotal(rs.getInt("total"));
				sector.setFail(rs.getInt("fail"));
			}
			return sector;
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
	public List<Meter> selectUnregister() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Meter> list = new ArrayList<Meter>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commaddr, logicaddr, status, moduleProtocol, distance, angle, sector, districtX, districtY, failTimes, pathType from PRM_METER ");
			ddl.append("where status<1 and distance is not null ");						
			ddl.append("order by status, distance");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Meter meter = new Meter();
				meter.setId(rs.getInt("id"));
				meter.setCommaddr(rs.getString("commaddr"));
				meter.setLogicaddr(rs.getString("logicaddr"));
				meter.setStatus(rs.getInt("status"));
				meter.setModuleprotocol(rs.getInt("moduleProtocol"));
				meter.setDistance(rs.getDouble("distance"));
				meter.setAngle(rs.getFloat("angle"));
				meter.setSector(rs.getInt("sector"));
				meter.setDistrictX(rs.getInt("districtX"));
				meter.setDistrictY(rs.getInt("districtY"));
				meter.setFailTimes(rs.getInt("failTimes"));
				meter.setPathtype(rs.getInt("pathType"));
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
	public int selectQuantityAfterX(int s, int x) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		int count = 0;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select COUNT(1) total from prm_meter ");
			ddl.append("where sector=? and districtX>?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());	
			stmt.setInt(1, s);
			stmt.setInt(2, x);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("total");
			}
			return count;
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
		return count;
	}
		
	@Override
	public List<Meter> selectRelay() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Meter> list = new ArrayList<Meter>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commAddr, distance, angle, sector, grade, relay, districtX, districtY, signalStrength, parent, path from prm_meter where relay>0");			
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Meter relay = new Meter();
				relay.setId(rs.getInt("id"));
				relay.setCommaddr(rs.getString("commaddr"));
				relay.setDistance(rs.getDouble("distance"));
				relay.setAngle(rs.getFloat("angle"));
				relay.setSector(rs.getInt("sector"));
				relay.setGrade(rs.getInt("grade"));
				relay.setRelay(rs.getInt("relay"));
				relay.setDistrictX(rs.getInt("districtX"));
				relay.setDistrictY(rs.getInt("districtY"));	
				relay.setSignal(rs.getInt("signalStrength"));
				relay.setParent(rs.getInt("parent"));
				relay.setPath(rs.getString("path"));
				list.add(relay);
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
	public Meter selectRelay(Meter entity) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select a.*, b.route from (");
			ddl.append("select id, commAddr, distance, angle, sector, grade, relay, districtX, districtY, signalStrength, parent, path from prm_meter ");
			ddl.append("where status=1 and id!=? ");
			ddl.append("and id NOT IN(select DISTINCT IFNULL(relayID, -1) rId ");
			ddl.append("from log_network where meterID=?) ");
			ddl.append("order by ABS(districtX-?), SignalStrength, ABS(Angle-?) limit 1) a ");
			ddl.append("LEFT JOIN log_network b ON a.id = b.meterID");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, entity.getId());
			stmt.setInt(2, entity.getId());
			stmt.setInt(3, entity.getDistrictX());
			stmt.setFloat(4, entity.getAngle());
			ResultSet rs = stmt.executeQuery();
			Meter relay = null;
			while (rs.next()) {
				relay = new Meter();
				relay.setId(rs.getInt("id"));
				relay.setCommaddr(rs.getString("commaddr"));
				relay.setDistance(rs.getDouble("distance"));
				relay.setAngle(rs.getFloat("angle"));
				relay.setSector(rs.getInt("sector"));
				relay.setGrade(rs.getInt("grade"));
				relay.setRelay(rs.getInt("relay"));
				relay.setDistrictX(rs.getInt("districtX"));
				relay.setDistrictY(rs.getInt("districtY"));	
				relay.setSignal(rs.getInt("signalStrength"));
				relay.setParent(rs.getInt("parent"));
				relay.setPath(rs.getString("path"));
				relay.running().setRoute(rs.getString("route"));
			}
			return relay;
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
	public int updateSuccess(Meter entity) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("update PRM_METER set ");	
			ddl.append("relay=0 ");
			ddl.append("where sector=" + entity.getSector() + " ");
			ddl.append("and grade=" + entity.getGrade() + " ");
			ddl.append("and districtY=" + entity.getDistrictY());
			
			StringBuffer ddl1 = new StringBuffer();
			ddl1.append("update PRM_METER set ");	
			ddl1.append("status=" + entity.getStatus() + ", ");
			ddl1.append("signalstrength=" + entity.getSignal() + ", ");
			ddl1.append("relay=" + entity.getRelay() + ", ");
			ddl1.append("grade=" + entity.getGrade() + ", ");
			ddl1.append("parent=" + entity.getParent() + ", ");
			ddl1.append("path='" + entity.getPath() + "', ");
			ddl1.append("regon=NOW() ");
			ddl1.append("where id=" + entity.getId());
			
			StringBuffer ddl2 = new StringBuffer();
			ddl2.append("insert into LOG_NETWORK(MeterID, StartTime, EndTime, CommAddr, Route, SignalStrength, Result, RelayID) values(");
			ddl2.append(entity.getId() + ",");
			ddl2.append("'" + new java.sql.Timestamp(entity.running().getStartTime().getTime()) + "',");
			ddl2.append("'" + new java.sql.Timestamp(entity.running().getEndTime().getTime()) + "',");
			ddl2.append("'" + entity.getCommaddr() + "',");
			ddl2.append("'" + entity.running().getRoute() + "',");
			ddl2.append(entity.getSignal() + ", ");
			ddl2.append(entity.running().getResult() + ",");
			ddl2.append(entity.running().getRelayId() + ")");			
			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			stmt.addBatch(ddl.toString());
			stmt.addBatch(ddl1.toString());
			stmt.addBatch(ddl2.toString());
			stmt.executeBatch();
//			conn.commit();
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
	public int updateFail(Meter entity, boolean changeStatus) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			StringBuffer ddl = null;
			if (changeStatus) {
				ddl = new StringBuffer();
				ddl.append("update PRM_METER set ");	
				ddl.append("status=" + entity.getStatus() + ",");
				ddl.append("signalstrength=" + entity.getSignal() + ", ");
				ddl.append("pathType=" + entity.getPathtype() + ",");
				ddl.append("failtimes=failtimes+1 ");
				ddl.append("where id=" + entity.getId());
			}
			
			StringBuffer ddl1 = null;			
			ddl1 = new StringBuffer();
			ddl1.append("insert into LOG_NETWORK(MeterID, StartTime, EndTime, CommAddr, Route, SignalStrength, Result, RelayID) values(");
			ddl1.append(entity.getId() + ",");
			ddl1.append("'" + new java.sql.Timestamp(entity.running().getStartTime().getTime()) + "',");
			ddl1.append("'" + new java.sql.Timestamp(entity.running().getEndTime().getTime()) + "',");
			ddl1.append("'" + entity.getCommaddr() + "',");
			ddl1.append("'" + entity.running().getRoute() + "',");
			ddl1.append(entity.getSignal() + ", ");
			ddl1.append(entity.running().getResult() + ",");
			ddl1.append(entity.running().getRelayId() + ")");
			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			if (changeStatus) stmt.addBatch(ddl.toString());
			stmt.addBatch(ddl1.toString());
			stmt.executeBatch();
//			conn.commit();
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
	public int updateRelay(Meter entity) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("update PRM_METER set relay=? ");			
			ddl.append("where id=?");			
			if (entity.getRelay() == 3) {
				ddl.append(" and relay=0");
			}
			
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, entity.getRelay());
			stmt.setInt(2, entity.getId());
			return stmt.executeUpdate();
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
	public List<Month> selectMonth(ENERGY energy, Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Month> list = new ArrayList<Month>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select m.id, m.commaddr, m.logicaddr, m.protocol, m.moduleprotocol");
			ddl.append(", (select route from log_network n where n.meterid=m.id and n.result=1 order by savetime desc LIMIT 1) route from prm_meter m ");
			ddl.append("where m.status=1 and m.id NOT IN(select meterid from data_energy_month em where em.frozentime = ? ");
			if (energy == ENERGY.ACTIVE) {
				ddl.append("or active_energy0 is null)");
			} else if (energy == ENERGY.NEGATIVE) {
				ddl.append("or negative_energy0 is null)");
			}
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Month month = new Month();
				month.setId(rs.getInt("id"));
				month.setCommaddr(rs.getString("commaddr"));
				month.setLogicaddr(rs.getString("logicaddr"));
				month.setProtocol(rs.getInt("protocol"));
				month.setModuleprotocol(rs.getInt("moduleProtocol"));
				month.setRoute(rs.getString("route"));
				if (energy == ENERGY.ACTIVE) {
					month.setDataId(energy.descOf());
				} else if (energy == ENERGY.NEGATIVE) {
					month.setDataId(energy.descOf());
				}
				list.add(month);
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
	public int reNet(Meter entity) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			StringBuffer ddl = null;			
			ddl = new StringBuffer();
			ddl.append("update PRM_METER set failtimes=0 where id=" + entity.getId());
			
			StringBuffer ddl1 = null;			
			ddl1 = new StringBuffer();
			ddl1.append("delete from LOG_NETWORK where meterid=" + entity.getId());
			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			stmt.addBatch(ddl.toString());
			stmt.addBatch(ddl1.toString());
			stmt.executeBatch();
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
