package cn.techen.lbs.db.mysql.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import cn.techen.lbs.db.api.MonthService;
import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.db.mysql.common.MysqlPool;
import cn.techen.lbs.db.common.DataConfig.ENERGY;

public class MonthServiceImpl implements MonthService {
	
	@Override
	public Month selectByKey(Object key) {
		return null;
	}

	@Override
	public List<Month> selectAll() {		
		return null;
	}

	@Override
	public int save(Month entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Object key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(Month entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotNull(Month entity) {		
		return 0;
	}
	
	@Override
	public List<Month> selectMonth(ENERGY energy, Date time) {
		MysqlPool mp = MysqlPool.getInstance();
		DruidPooledConnection conn = null;
		PreparedStatement stmt = null;
		try {
			List<Month> list = new ArrayList<Month>();
			StringBuffer ddl = new StringBuffer();
			ddl.append("select m.id, m.commaddr, m.protocol, m.moduleprotocol, m.route from prm_node m ");
			ddl.append("where m.deviceclass=1 and m.status=1 and m.id NOT IN(select meterid from data_energy_month em where em.frozentime=? ");
			if (energy == ENERGY.ACTIVE) {
				ddl.append("or em.active_energy0 is null)");
			} else if (energy == ENERGY.NEGATIVE) {
				ddl.append("or em.negative_energy0 is null)");
			}
			conn = mp.getConnection();
			stmt = conn.prepareStatement(ddl.toString());
			stmt.setTimestamp(1, new java.sql.Timestamp(time.getTime()));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Month month = new Month();
				month.setId(rs.getInt("id"));
				month.setCommaddr(rs.getString("commaddr"));
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
	
}
