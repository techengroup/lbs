package cn.techen.lbs.protocol;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface IConfig {	
	
	Date newTime();
	
	Map<String, Object> runs();
	
	List<String> funcs();
	
	Queue<Object> units();
	
	Map<String, String> funcKeys();
	
}
