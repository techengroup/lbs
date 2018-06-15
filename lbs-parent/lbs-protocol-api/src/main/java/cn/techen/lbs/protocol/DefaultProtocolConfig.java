package cn.techen.lbs.protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultProtocolConfig implements ProtocolConfig {
	
	private String commAddr;
	
	private DIR dir;
	
	private OPERATION operation;
	
	private Map<String, Object> userData = new HashMap<String, Object>();//key-value
	
	private List<String> dataId = new ArrayList<String>();//X:X
	
	private List<Object> dataUnit = new ArrayList<Object>();//X:X

	@Override
	public String getCommAddr() {
		return commAddr;
	}

	@Override
	public ProtocolConfig setCommAddr(String commAddr) {
		this.commAddr = commAddr;
		return this;
	}
	
	@Override
	public DIR getDir() {
		return dir;
	}

	@Override
	public DefaultProtocolConfig setDir(DIR dir) {
		this.dir = dir;
		return this;
	}

	@Override
	public OPERATION getOperation() {
		return operation;
	}

	@Override
	public DefaultProtocolConfig setOperation(OPERATION operation) {
		this.operation = operation;
		return this;
	}

	@Override
	public Map<String, Object> userData() {
		return userData;
	}

	@Override
	public List<String> dataId() {
		return dataId;
	}

	@Override
	public List<Object> dataUnit() {
		return dataUnit;
	}
	
}
