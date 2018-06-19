package cn.techen.lbs.mm.api;

import cn.techen.lbs.db.model.Table;

/**
 * MTableService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MTableService extends MyService<String, Table> {
	
    static final String STORE_TABLE = "STORE:TABLE";
    
    void lpush(Table table);
    
    Table rpop();
	
}
