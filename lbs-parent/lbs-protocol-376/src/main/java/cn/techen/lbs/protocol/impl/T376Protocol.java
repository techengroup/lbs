package cn.techen.lbs.protocol.impl;

import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;

public class T376Protocol implements ProtocolService {
	
	private T376Proxy t376Proxy;

	@Override
	public Integer getCode() {
		return 100;
	}

	@Override
	public String getName() {
		return "Techen GW376.1";
	}

	@Override
	public String getVersion() {		
		return "1.0";
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
