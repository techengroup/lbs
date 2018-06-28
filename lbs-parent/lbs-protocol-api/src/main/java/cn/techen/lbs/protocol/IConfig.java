package cn.techen.lbs.protocol;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface IConfig {	
	
	Map<String, Object> runs();
	
	List<String> funcs();
	
	Queue<Object> units();
	
	Map<String, String> funcKeys();
	
}
