package cn.techen.lbs.mm.api;

import java.util.List;

import cn.techen.lbs.db.model.Report;

/**
 * MReportService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MReportService extends MyService<String, Report> {
	
    static final String DB_REPORT = "DB:REPORT";
        
    void lpush(Report report);
    
    void lpush(List<Report> reports);
    
    Report rpop();

}
