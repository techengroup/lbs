package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.List;

import cn.techen.lbs.db.model.Param;

/**
 * ParamService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface ParamService extends IService<Param> {
	
    List<Param> selectByTime(Date time);

	int updateValue(String key, String value);
    
}
