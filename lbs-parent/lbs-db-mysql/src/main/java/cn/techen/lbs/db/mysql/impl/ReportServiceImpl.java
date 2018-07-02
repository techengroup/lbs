package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.ReportService;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.db.mysql.common.MysqlPool;

public class ReportServiceImpl implements ReportService {
	
	@Override
	public Report selectByKey(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Report entity) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		Statement stmt = null;
		try {
			StringBuffer ddl = new StringBuffer();
			ddl.append("insert into LOG_REPORT(meterid, reporttime, commaddr, route, signalstrength) values(");
			ddl.append(entity.getMeterid() + ",");
			ddl.append("'" + new java.sql.Timestamp(entity.getReporttime().getTime()) + "',");
			ddl.append("'" + entity.getCommaddr() + "',");
			ddl.append("'" + entity.getRoute() + "',");
			ddl.append(entity.getSignal() + ")");
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

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(Report entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(Report entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Report> selectByTime(Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Report> reportList = new ArrayList<Report>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select meterid, reporttime, commaddr, route from LOG_REPORT ");
			ddl.append("where ADDDATE(reporttime, INTERVAL 1 DAY) > ? and result = 0");
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			ResultSet rs = stmt.executeQuery();			
			while (rs.next()) {
				Report report = new Report();
				report.setMeterid(rs.getInt("meterid"));
				report.setReporttime(rs.getDate("reporttime"));
				report.setCommaddr(rs.getString("commaddr"));
				report.setRoute(rs.getString("route"));
				reportList.add(report);
			}
			return reportList;
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
