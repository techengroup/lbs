package cn.techen.lbs.db.api;

import java.util.List;

/**
 * BaseService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface BaseService {
	
	List<Object> query(String sqlTemplate, Object[] conditions);
	
	int save(String sqlTemplate, Object[] datas, Object[] conditions);
    
}
