package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.NodeService;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class NodeServiceImpl implements NodeService {

	@Override
	public Node selectByKey(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Node> selectAll() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Node> list = new ArrayList<Node>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, status, pointno, commaddr, protocol, moduleprotocol, latitude, longitude, route from PRM_NODE");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Node node = new Node();
				node.setId(rs.getInt("id"));
				node.setStatus(rs.getInt("status"));
				node.setPointno(rs.getInt("pointno"));
				node.setCommaddr(rs.getString("commaddr"));
				node.setProtocol(rs.getInt("protocol"));
				node.setModuleprotocol(rs.getInt("moduleprotocol"));
				node.setLatitude(rs.getDouble("latitude"));
				node.setLongitude(rs.getDouble("longitude"));
				node.setRoute(rs.getString("route"));
				list.add(node);
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
	public int save(Node entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(Node entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(Node entity) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<Node> selectByTime(Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Node> list = new ArrayList<Node>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, status, pointno, commaddr, protocol, moduleprotocol, latitude, longitude, route from PRM_NODE ");
			ddl.append("where crton>? or mdfon>?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			stmt.setTimestamp(2, new java.sql.Timestamp(time.getTime())); 
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Node node = new Node();
				node.setId(rs.getInt("id"));
				node.setStatus(rs.getInt("status"));
				node.setPointno(rs.getInt("pointno"));
				node.setCommaddr(rs.getString("commaddr"));
				node.setProtocol(rs.getInt("protocol"));
				node.setModuleprotocol(rs.getInt("moduleprotocol"));
				node.setLatitude(rs.getDouble("latitude"));
				node.setLongitude(rs.getDouble("longitude"));
				node.setRoute(rs.getString("route"));
				list.add(node);
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
	public List<Node> selectGIS() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Node> list = new ArrayList<Node>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, latitude, longitude from PRM_NODE ");
			ddl.append("where distance is null");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Node node = new Node();
				node.setId(rs.getInt("id"));
				node.setLatitude(rs.getDouble("latitude"));
				node.setLongitude(rs.getDouble("longitude"));
				list.add(node);
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
	public int updateGIS(List<Node> entitys) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			for (Node node : entitys) {
				stmt.addBatch(String.format("update PRM_NODE set distance=%f, angle=%f, sector=%d, districtx=%d, districty=%d where id=%d"
						, node.getDistance(), node.getAngle(), node.getSector(), node.getDistrictX(), node.getDistrictY(), node.getId()));
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
	public List<Node> selectUnregister() {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Node> list = new ArrayList<Node>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commaddr, deviceClass, status, moduleProtocol, distance, angle, sector, districtX, districtY from PRM_NODE ");
			ddl.append("where status<1 and distance is not null ");						
			ddl.append("order by status, distance");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Node node = new Node();
				node.setId(rs.getInt("id"));
				node.setCommaddr(rs.getString("commaddr"));
				node.setDeviceclass(rs.getInt("deviceClass"));
				node.setStatus(rs.getInt("status"));				
				node.setModuleprotocol(rs.getInt("moduleProtocol"));
				node.setDistance(rs.getDouble("distance"));
				node.setAngle(rs.getFloat("angle"));
				node.setSector(rs.getInt("sector"));
				node.setDistrictX(rs.getInt("districtX"));
				node.setDistrictY(rs.getInt("districtY"));	
				list.add(node);
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
	public Node selectPrimeRelay(int sector, double distance) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commaddr, grade, path, route, districtX, distance from PRM_NODE where sector=? and relay=2 and distance<? ");
			ddl.append("ORDER BY distance desc LIMIT 1");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, sector);
			stmt.setDouble(2, distance);
			ResultSet rs = stmt.executeQuery();
			Node relay = null;
			
			while (rs.next()) {
				relay = new Node();
				relay.setId(rs.getInt("id"));
				relay.setCommaddr(rs.getString("commaddr"));
				relay.setGrade(rs.getInt("grade"));
				relay.setPath(rs.getString("path"));
				relay.setRoute(rs.getString("route"));
				relay.setDistrictX(rs.getInt("districtX"));
				relay.setDistance(rs.getDouble("distance"));
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
	public int selectExecTimesWithOptimalRelay(int nodeId, int relayId) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select COUNT(1) num from LOG_NETWORK where nodeId=? and relayid=?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, nodeId);
			stmt.setInt(2, relayId);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			
			while (rs.next()) {
				count = rs.getInt("num");
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
		
		return 0;
	}
	
	@Override
	public Node selectSecondaryRelay(int nodeId, int sector, double distance) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commaddr, grade, path, route, districtX, distance from PRM_NODE where sector=? and relay=1 ");
			ddl.append("and distance<? and id NOT IN(select DISTINCT IFNULL(relayID, -1) rId from LOG_NETWORK where nodeid=?) ");
			ddl.append("ORDER BY distance desc LIMIT 1");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, sector);
			stmt.setDouble(2, distance);
			stmt.setDouble(3, nodeId);
			ResultSet rs = stmt.executeQuery();
			Node relay = null;
			
			while (rs.next()) {
				relay = new Node();
				relay.setId(rs.getInt("id"));
				relay.setCommaddr(rs.getString("commaddr"));
				relay.setGrade(rs.getInt("grade"));
				relay.setPath(rs.getString("path"));
				relay.setRoute(rs.getString("route"));
				relay.setDistrictX(rs.getInt("districtX"));
				relay.setDistance(rs.getDouble("distance"));
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
	public int selectSecondaryRelayAmount(int nodeId) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select COUNT(1) num from (");
			ddl.append("select DISTINCT w.relayId, n.relay from PRM_NODE n LEFT JOIN log_network w on n.id = w.nodeid ");
			ddl.append("where n.id = ?) a where a.relay = 1");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, nodeId);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			
			while (rs.next()) {
				count = rs.getInt("num");
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
		
		return 0;
	}
	
	@Override
	public Node selectOtherRepeater(int nodeId, int sector, int sRange, int districtX, int xRange) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commAddr, grade, path, route, relay, districtX, distance from PRM_NODE ");
			ddl.append("where deviceclass=0 and status=1 and (sector BETWEEN ? AND ?) and (districtX BETWEEN ? AND ?) ");
			ddl.append("and id NOT IN(select DISTINCT IFNULL(relayID, -1) rId from log_network where nodeid=?) ");
			ddl.append("order by grade, ABS(sector-?), ABS(districtX-?) limit 1");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, (12 + (sector-sRange)) % 12);
			stmt.setInt(2, (sector+sRange) % 12);
			stmt.setInt(3, ((districtX-xRange) < 0) ? 0 : (districtX-xRange));
			stmt.setInt(4, districtX+xRange);
			stmt.setInt(5, nodeId);
			stmt.setInt(6, sector);
			stmt.setInt(7, districtX);
			ResultSet rs = stmt.executeQuery();
			Node relay = null;
			
			while (rs.next()) {
				relay = new Node();
				relay.setId(rs.getInt("id"));
				relay.setCommaddr(rs.getString("commaddr"));
				relay.setGrade(rs.getInt("grade"));
				relay.setPath(rs.getString("path"));
				relay.setRoute(rs.getString("route"));
				relay.setRelay(rs.getInt("relay"));
				relay.setDistrictX(rs.getInt("districtX"));
				relay.setDistance(rs.getDouble("distance"));
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
	public Node selectOtherRelay(int nodeId, int sector, int sRange, int districtX, int xRange, float angle) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commAddr, grade, path, route, relay, districtX, distance from PRM_NODE ");
			ddl.append("where status=1 and (sector BETWEEN ? AND ?) and (districtX BETWEEN ? AND ?) ");
			ddl.append("and id NOT IN(select DISTINCT IFNULL(relayID, -1) rId from log_network where nodeid=?) ");
			ddl.append("order by grade, ABS(sector-?), ABS(districtX-?), ABS(Angle-?) limit 1");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, (12 + (sector-sRange)) % 12);
			stmt.setInt(2, (sector+sRange) % 12);
			stmt.setInt(3, ((districtX-xRange) < 0) ? 0 : (districtX-xRange));
			stmt.setInt(4, districtX+xRange);
			stmt.setInt(5, nodeId);
			stmt.setInt(6, sector);
			stmt.setInt(7, districtX);
			stmt.setFloat(8, angle);
			ResultSet rs = stmt.executeQuery();
			Node relay = null;
			
			while (rs.next()) {
				relay = new Node();
				relay.setId(rs.getInt("id"));
				relay.setCommaddr(rs.getString("commaddr"));
				relay.setGrade(rs.getInt("grade"));
				relay.setPath(rs.getString("path"));
				relay.setRoute(rs.getString("route"));
				relay.setRelay(rs.getInt("relay"));
				relay.setDistrictX(rs.getInt("districtX"));
				relay.setDistance(rs.getDouble("distance"));
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
	public int clearRoute(int nodeId) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {					
			StringBuffer ddl = new StringBuffer();
			ddl.append("delete from LOG_NETWORK where nodeid=" + nodeId);
			
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
		
		return -1;
	}
	
	@Override
	public int saveSuccess(int nodeId, String commAddr, int grade, int parent, String path, String route, int relay, Date startTime, Date endTime, int RSSI, int channel) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {			
			StringBuffer ddl = new StringBuffer();
			ddl.append("update PRM_NODE set ");	
			ddl.append("status=1, ");
			ddl.append("grade=" + grade + ", ");
			ddl.append("parent=" + parent + ", ");
			ddl.append("path='" + path + "', ");
			ddl.append("route='" + route + "', ");
			ddl.append("relay=" + relay + ", ");
			ddl.append("regon=NOW() ");
			ddl.append("where id=" + nodeId);
			
			StringBuffer ddl1 = new StringBuffer();
			ddl1.append("insert into LOG_NETWORK(NodeID, StartTime, EndTime, CommAddr, Route, SignalStrength, Channel, Result, RelayID) values(");
			ddl1.append(nodeId + ",");
			ddl1.append("'" + new java.sql.Timestamp(startTime.getTime()) + "',");
			ddl1.append("'" + new java.sql.Timestamp(endTime.getTime()) + "',");
			ddl1.append("'" + commAddr + "',");
			ddl1.append("'" + route + "',");
			ddl1.append(RSSI + ", ");
			ddl1.append(channel + ", ");
			ddl1.append("1,");
			ddl1.append(parent + ")");			
			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			stmt.addBatch(ddl.toString());
			stmt.addBatch(ddl1.toString());
			stmt.executeBatch();
			
			return 1;
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
	public int saveFailSingle(int nodeId, String commAddr, int parent, String path, String route, Date startTime, Date endTime, Integer RSSI, Integer channel) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {			
			StringBuffer ddl = null;			
			ddl = new StringBuffer();
			ddl.append("insert into LOG_NETWORK(NodeID, StartTime, EndTime, CommAddr, Route, SignalStrength, Channel, Result, RelayID) values(");
			ddl.append(nodeId + ",");
			ddl.append("'" + new java.sql.Timestamp(startTime.getTime()) + "',");
			ddl.append("'" + new java.sql.Timestamp(endTime.getTime()) + "',");
			ddl.append("'" + commAddr + "',");
			ddl.append("'" + route + "',");
			ddl.append(RSSI + ", ");
			ddl.append(channel + ", ");
			ddl.append(0 + ",");
			ddl.append(parent + ")");
			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			return stmt.executeUpdate(ddl.toString());
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
	public int saveFailCompletely(int nodeId, String commAddr, int parent, String path, String route, Date startTime, Date endTime, Integer RSSI, Integer channel, int status) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {			
			StringBuffer ddl = new StringBuffer();
			ddl.append("update PRM_NODE set ");	
			ddl.append("status=0 ");
			ddl.append("where id=" + nodeId);			
			
			StringBuffer ddl1 = null;			
			ddl1 = new StringBuffer();
			ddl1.append("insert into LOG_NETWORK(NodeID, StartTime, EndTime, CommAddr, Route, SignalStrength, Channel, Result, RelayID) values(");
			ddl1.append(nodeId + ",");
			ddl1.append("'" + new java.sql.Timestamp(startTime.getTime()) + "',");
			ddl1.append("'" + new java.sql.Timestamp(endTime.getTime()) + "',");
			ddl1.append("'" + commAddr + "',");
			ddl1.append("'" + route + "',");
			ddl1.append(RSSI + ", ");
			ddl1.append(channel + ", ");
			ddl1.append(0 + ",");
			ddl1.append(parent + ")");
			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			stmt.addBatch(ddl.toString());
			stmt.addBatch(ddl1.toString());
			stmt.executeBatch();
			
			return 1;
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
	public int selectSuccessNodeAfterNode(int sector, double distance) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		int count = 0;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select COUNT(1) num from PRM_NODE ");
			ddl.append("where sector=? and status=1 and Distance>?");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());	
			stmt.setInt(1, sector);
			stmt.setDouble(2, distance);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("num");
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
	public Map<String, Integer> selectTotalAndFailNode(int sector, int districtX) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select (select COUNT(1) total from PRM_NODE where sector=? and districtX=?) total, ");
			ddl.append("(select COUNT(1) fail from PRM_NODE where sector=? and districtX=? and status=0) fail from dual");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());	
			stmt.setInt(1, sector);
			stmt.setInt(2, districtX);
			stmt.setInt(3, sector);
			stmt.setInt(4, districtX);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				map.put("total", rs.getInt("total"));
				map.put("fail", rs.getInt("fail"));
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
	public Node selectSuccessNodeBeforeNode(int sector, double distance) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("select id, commaddr, grade, path, route, districtX, distance from PRM_NODE ");
			ddl.append("where sector=? and status=1 and relay=0 and distance<? ");
			ddl.append("ORDER BY distance desc LIMIT 1");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());	
			stmt.setInt(1, sector);
			stmt.setDouble(2, distance);
			ResultSet rs = stmt.executeQuery();
			Node node = null;

			while (rs.next()) {
				node = new Node();
				node.setId(rs.getInt("id"));
				node.setCommaddr(rs.getString("commaddr"));
				node.setGrade(rs.getInt("grade"));
				node.setPath(rs.getString("path"));
				node.setRoute(rs.getString("route"));
				node.setDistrictX(rs.getInt("districtX"));
				node.setDistance(rs.getDouble("distance"));
			}
			
			return node;
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
	public List<Node> selectOptimalNode(int sector, double previousRelayDistance, double nearSuccessNodeDistance) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Node> list = new ArrayList<Node>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select * from (");
			ddl.append("select n.id, n.districtX, n.districtY, w.signalstrength from prm_node n LEFT JOIN log_network w on n.id=w.nodeid and w.result=1 ");						
			ddl.append("where n.sector=? and n.districtY=0 and status=1 and n.distance>? and n.distance<=? order by n.districtX desc, n.grade, w.signalstrength desc LIMIT 1) a ");
			ddl.append("UNION ");
			ddl.append("select * from (");
			ddl.append("select n.id, n.districtX, n.districtY, w.signalstrength from prm_node n LEFT JOIN log_network w on n.id=w.nodeid and w.result=1 ");						
			ddl.append("where n.sector=? and n.districtY=1 and status=1 and n.distance>? and n.distance<=? order by n.districtX desc, n.grade, w.signalstrength desc LIMIT 1) b ");
			ddl.append("UNION ");
			ddl.append("select * from (");
			ddl.append("select n.id, n.districtX, n.districtY, w.signalstrength from prm_node n LEFT JOIN log_network w on n.id=w.nodeid and w.result=1 ");						
			ddl.append("where n.sector=? and n.districtY=2 and status=1 and n.distance>? and n.distance<=? order by n.districtX desc, n.grade, w.signalstrength desc LIMIT 1) c ");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setInt(1, sector);
			stmt.setDouble(2, previousRelayDistance);
			stmt.setDouble(3, nearSuccessNodeDistance);
			stmt.setInt(4, sector);
			stmt.setDouble(5, previousRelayDistance);
			stmt.setDouble(6, nearSuccessNodeDistance);
			stmt.setInt(7, sector);
			stmt.setDouble(8, previousRelayDistance);
			stmt.setDouble(9, nearSuccessNodeDistance);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Node node = new Node();
				node.setId(rs.getInt("id"));
				node.setDistrictX(rs.getInt("districtX"));
				node.setDistrictY(rs.getInt("districtY"));	
				node.setRssi(rs.getInt("signalstrength"));
				list.add(node);
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
	public int optimalRelay(List<Node> nodes) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {			
			conn = mp.getConnection();
			stmt = conn.createStatement();
			for (Node node : nodes) {
				StringBuffer ddl = new StringBuffer();
				ddl.append("update PRM_NODE set ");	
				ddl.append("relay=" + node.getRelay() + " ");
				ddl.append("where id=" + node.getId());
				stmt.addBatch(ddl.toString());
			}
			stmt.executeBatch();
			
			return 1;
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
	
}
