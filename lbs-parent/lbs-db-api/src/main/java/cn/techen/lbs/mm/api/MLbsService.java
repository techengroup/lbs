package cn.techen.lbs.mm.api;

import cn.techen.lbs.db.model.LBS;

/**
 * MMeterService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MLbsService extends MyService<String, LBS> {
	
    static final String TABLENAME = "DB:LBS";
    
    void set(LBS lbs);
    
    LBS get();

}
