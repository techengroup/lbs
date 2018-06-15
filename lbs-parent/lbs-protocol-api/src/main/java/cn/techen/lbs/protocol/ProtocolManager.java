package cn.techen.lbs.protocol;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProtocolManagerImpl
 * 
 * @author ZY
 * @version V1.0 New Date 2014-09-20
 */
public class ProtocolManager implements ProtocolManagerService {
	private static final Logger logger = (Logger) LoggerFactory  
            .getLogger("LBS-PROTOCOL");
	
	@Override
	public Map<Integer, ProtocolService> getProtocols() {
		return codeMap;
	}  
 	
	@Override
	public ProtocolService getProtocol(Integer code) {
		return codeMap.get(code);
	}

	@Override
	public ProtocolService getProtocol(String nameVersion) {
		return nameMap.get(nameVersion);
	}
	
	/**
	 * Register protocol to protocol map
	 * @param protocol
	 */
    public void bind(ProtocolService protocolService) {  
    	if (protocolService == null) return;
    	Integer code = protocolService.getCode();
    	String name = protocolService.getName();
    	String version = protocolService.getVersion();
    	String nameKey = name + ":" + version;
        if (!codeMap.containsKey(code) && !nameMap.containsKey(nameKey)) {  
        	codeMap.put(code, protocolService); 
        	nameMap.put(nameKey, protocolService); 
        	logger.info("{} protocol[code:{},version:{}] was registered.", name, code, version);
        } else {
        	logger.warn("{} protocol[code:{},version:{}]  has been existed in protocol stack. Please check it.", name, code, version);
        }
    }  
      
    /**
	 * Remove protocol from protocol map
	 * @param protocol
	 */
    public void unbind(ProtocolService protocolService) {  
    	if (protocolService == null) return;
    	Integer code = protocolService.getCode();
    	String name = protocolService.getName();
    	String version = protocolService.getVersion();
    	String nameKey = name + ":" + version;
    	if (codeMap.containsKey(code) || nameMap.containsKey(nameKey)) {   
    		codeMap.remove(code); 
    		nameMap.remove(nameKey);
        	logger.info("{} protocol[code:{}, version:{}] was removed.", name, code, version);
        }  
    }
	
}
