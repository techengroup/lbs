package cn.techen.lbs.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ProtocolManagerService
 * 
 * @author ZY
 * @version V1.0 New Date 2018-04-11
 */
public interface ProtocolManagerService {
	
	/**
	 * Protocol Map -- key:code
	 */
	Map<Integer, ProtocolService> codeMap  = new ConcurrentHashMap<Integer, ProtocolService>();
	
	/**
	 * Protocol Map -- key:name+":"+version
	 */
	Map<String, ProtocolService> nameMap  = new ConcurrentHashMap<String, ProtocolService>();
	
	/**
	 * Get all protocols
	 * @return
	 */
	Map<Integer, ProtocolService> getProtocols();
	
	/**
	 * Get Protocol
	 * @param code
	 * @return
	 */
	ProtocolService getProtocol(Integer code);
	
	/**
	 * Get Protocol
	 * @param nameVersion
	 * @return
	 */
	ProtocolService getProtocol(String nameVersion);
}
