package cn.techen.lbs.db.common;

import java.util.concurrent.ConcurrentHashMap;

public class Sqls extends ConcurrentHashMap<String, String> {
	
	private static final long serialVersionUID = 803552693696491218L;
	
	private static Sqls sqls;
	
	public static Sqls getInstance() {
		if (sqls == null) {
			synchronized (Sqls.class) {
				if (sqls == null) {
					sqls = new Sqls();
				}
			}
		}
		return sqls;
	}

}
