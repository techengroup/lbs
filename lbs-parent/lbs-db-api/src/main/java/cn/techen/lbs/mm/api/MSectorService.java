package cn.techen.lbs.mm.api;

import java.util.List;

import cn.techen.lbs.db.model.Sector;

/**
 * MSectorService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MSectorService extends MyService<String, Sector> {
	
	static final String DB_SECTOR = "DB:SECTOR_X_TOTAL";//key:[Sector:X] value:[total]
	static final String DB_SECTOR_X_FAIL = "DB:SECTOR_X_FAIL";//key:[Sector:X] value:[fail]

	void put(Sector sector);
	
	void put(List<Sector> sectors);

	Sector get(Integer sector, Integer districtX);
	
}
