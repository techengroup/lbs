package cn.techen.lbs.mm.api;

import java.util.List;

import cn.techen.lbs.db.model.Meter;

/**
 * MMeterService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MRegisterService extends MyService<String, Meter> {
	
    static final String DB_METER_UNREGISTER = "DB:METER:UNREGISTER";
        
    void lpush(Meter meter);
    
    void lpush(List<Meter> meters);
    
    Meter rpop();

}
