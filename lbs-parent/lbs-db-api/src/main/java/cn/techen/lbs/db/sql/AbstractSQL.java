package cn.techen.lbs.db.sql;

import java.util.Queue;

public abstract class AbstractSQL {
	
	public abstract String handle(Object id, Queue<Object> datas);

}
