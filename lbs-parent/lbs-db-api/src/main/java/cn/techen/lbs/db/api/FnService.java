package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.List;

import cn.techen.lbs.db.model.Fn;

/**
 * FuncService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface FnService extends IService<Fn> {
	
    List<Fn> selectByTime(Date time);
	
}
