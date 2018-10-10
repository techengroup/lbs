package cn.techen.lbs.db.api;

import java.util.List;

/**
 * SqlService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface GeneralService {
	
	boolean isConnected();

	List<Object> query(String sql);
	
	int save(String sqls);
    
	int selectEventCount();

	int deleteEventRank0();
}
