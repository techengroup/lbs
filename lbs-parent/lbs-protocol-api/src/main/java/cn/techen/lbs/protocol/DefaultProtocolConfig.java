package cn.techen.lbs.protocol;

public class DefaultProtocolConfig extends AbstractConfig implements ProtocolConfig {
	
	private static final long serialVersionUID = 5603352288508869314L;

	private String commAddr;
	
	private DIR dir;
	
	private OPERATION operation;

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
	
}
