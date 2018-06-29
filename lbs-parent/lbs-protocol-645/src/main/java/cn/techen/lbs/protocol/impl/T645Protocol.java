package cn.techen.lbs.protocol.impl;

import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.protocol.t645.common.Local;

public class T645Protocol implements ProtocolService {
	
	private T645Proxy t645Proxy;

	@Override
	public Integer getCode() {
		return Local.CODE;
	}

	@Override
	public String getName() {
		return Local.NAME;
	}

	@Override
	public String getVersion() {		
		return Local.VERSION;
	}

	@Override
	public int valid(byte[] data) {
		return 0;
	}

	@Override
	public ProtocolConfig decode(byte[] data) throws Exception {		
		return t645Proxy.decode(data);
	}

	@Override
	public byte[] encode(ProtocolConfig config) throws Exception {
		return t645Proxy.encode(config);
	}

	public void setT645Proxy(T645Proxy t645Proxy) {
		this.t645Proxy = t645Proxy;
	}

}
