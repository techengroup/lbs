package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.List;

import cn.techen.lbs.db.model.Meter;

/**
 * MeterService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MeterService extends IService<Meter> {
	
    List<Meter> selectByTime(Date time);

	List<Meter> selectGIS();

	int updateGIS(List<Meter> entitys);
	
}
