package cn.techen.lbs.mm.api;

import java.util.List;

import cn.techen.lbs.db.model.Month;

/**
 * MMonthService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MMonthService extends MyService<String, Month>, MySize {
	
    static final String DB_MONTH = "DB:MONTH";
        
    void lpush(Month month);
    
    void lpush(List<Month> months);
    
    Month rpop();

}
