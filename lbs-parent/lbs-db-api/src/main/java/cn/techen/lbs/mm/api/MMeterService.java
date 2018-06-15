package cn.techen.lbs.mm.api;

import java.util.List;

import cn.techen.lbs.db.model.Meter;

/**
 * MMeterService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MMeterService extends MyService<String, Meter> {
	
    static final String DB_METER = "DB:METER";
    
    void put(Meter meter);
    
    void put(List<Meter> meters);
    
    Meter get(String commAddr);
    
    List<Meter> get();
	
}
