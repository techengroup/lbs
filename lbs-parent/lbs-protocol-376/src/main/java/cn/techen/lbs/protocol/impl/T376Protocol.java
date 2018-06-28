package cn.techen.lbs.protocol.impl;

import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.protocol.t376.common.Local;

public class T376Protocol implements ProtocolService {
	
	private T376Proxy t376Proxy;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProtocolConfig decode(byte[] data) throws Exception {		
		return t376Proxy.decode(data);
	}

	@Override
	public byte[] encode(ProtocolConfig config) throws Exception {
		return t376Proxy.encode(config);
	}

	public void setT376Proxy(T376Proxy t376Proxy) {
		this.t376Proxy = t376Proxy;
	}
	
}
