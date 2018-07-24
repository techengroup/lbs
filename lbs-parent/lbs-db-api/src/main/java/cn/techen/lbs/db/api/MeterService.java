package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.List;

import cn.techen.lbs.db.common.DataConfig.ENERGY;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.db.model.Sector;

/**
 * MeterService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MeterService extends IService<Meter> {
	
    List<Meter> selectByTime(Date time);
    
    Sector selectQuantity(int s, int x);

    List<Meter> selectUnregister();

	List<Meter> selectUnregisterByTime(Date time);
    
	int selectQuantityAfterX(int s, int x);

	List<Meter> selectRelay();

	Meter selectRelay(Meter entity);
	
	int updateSuccess(Meter entity);

	int updateFail(Meter entity, boolean changeStatus);
	
	int updateRelay(Meter entity);

	List<Meter> selectGIS();

	int updateGIS(List<Meter> entitys);

	List<Month> selectMonth(ENERGY energy, Date time);

	int reNetwork(Meter entity);
	
}
