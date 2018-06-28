package cn.techen.lbs.data.protocol;

import java.util.Queue;

public abstract class AbstractSQL {
	
	public abstract String handle(Object id, Queue<Object> datas);

}
