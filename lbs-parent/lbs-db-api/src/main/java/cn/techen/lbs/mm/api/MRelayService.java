package cn.techen.lbs.mm.api;

import java.util.List;

import cn.techen.lbs.db.model.Meter;

/**
 * MMeterService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MRelayService extends MyService<String, Meter> {
	
	static final String DB_METER_RELAY = "DB:METER:RELAY";
    
    void put(Meter meter);

	void put(List<Meter> meters);
    
    Meter get(Integer sector, Integer grade, Integer districtY);

	Meter getOptimal(Integer sector, Integer grade);
	
}
