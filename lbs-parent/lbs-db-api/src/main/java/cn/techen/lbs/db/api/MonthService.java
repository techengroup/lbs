package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.List;

import cn.techen.lbs.db.common.DataConfig.ENERGY;
import cn.techen.lbs.db.model.Month;

/**
 * MeterService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MonthService extends IService<Month> {

	List<Month> selectMonth(ENERGY energy, Date time);
	
}
