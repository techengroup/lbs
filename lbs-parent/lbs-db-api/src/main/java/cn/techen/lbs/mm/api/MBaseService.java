package cn.techen.lbs.mm.api;

/**
 * MBaseService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MBaseService {
	
	boolean isConnected();
	
	Long del(String key);
	
	String flushDB();
	
	String flushAll();
}
