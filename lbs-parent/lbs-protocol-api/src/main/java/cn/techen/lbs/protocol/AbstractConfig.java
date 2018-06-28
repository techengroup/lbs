package cn.techen.lbs.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public abstract class AbstractConfig {
	
	private List<String> funcs  = new ArrayList<String>();
	
	private Queue<Object> units = new LinkedList<Object>();
	
	private Map<String, Object> runs = new HashMap<String, Object>();
	
	private Map<String, String> funcKeys = new HashMap<String, String>();
	
	public List<String> funcs() {
		return funcs;
	}
	
	public Queue<Object> units() {
		return units;
	}
	
	public Map<String, Object> runs() {
		return runs;
	}

	public Map<String, String> funcKeys() {
		return funcKeys;
	}
	
}
