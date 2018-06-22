package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.Map;

/**
 * FuncService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface FuncService {
	
	Map<String, String> selectAll();
	
    Map<String, String> selectByTime(Date time);
	
}
