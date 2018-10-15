package cn.techen.lbs.mm.api;

import java.util.List;

import cn.techen.lbs.db.model.Node;

/**
 * MNodeService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface MNodeService extends MyService<String, Node>, MySize {
	
	static final String DB_NODE = "DB:NODE";
    static final String DB_NODE_UNREGISTER = "DB:NODE:UNREGISTER";
    
    void put(Node node);
    
    void put(List<Node> nodes);
    
    Node get(String commAddr);
    
    List<Node> get();
        
    void lpush(Node node);
    
    void lpush(List<Node> nodes);
    
    Node rpop();

}
