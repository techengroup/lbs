package cn.techen.lbs.protocol.impl;

import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;

public class T645Protocol implements ProtocolService {
	
	private T645Proxy t645Proxy;

	@Override
	public Integer getCode() {
		return 1;
	}

	@Override
	public String getName() {
		return "Techen 645";
	}

	@Override
	public String getVersion() {		
		return "1.0";
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

	public void setLoraProxy(T645Proxy loraProxy) {
		this.t645Proxy = loraProxy;
	}

}
