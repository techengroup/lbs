package cn.techen.lbs.db.api;

import java.util.Date;

import cn.techen.lbs.db.model.LBS;

/**
 * LbsService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface LbsService extends IService<LBS> {
	
	LBS selectByTime(Date time);
    
}
