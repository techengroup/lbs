package cn.techen.lbs.db.api;

import cn.techen.lbs.db.model.Report;

/**
 * ReportService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface ReportService extends IService<Report> {

	int updateFail(int meterId, int status);
	
}
