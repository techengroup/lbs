package cn.techen.lbs.protocol.impl;

import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;

public class LoraProtocol implements ProtocolService {
	
	private LoraProxy loraProxy;

	@Override
	public Integer getCode() {
		return 0;
	}

	@Override
	public String getName() {
		return "Techen-lora";
	}

	@Override
	public String getVersion() {		
		return "1.0";
	}

	@Override
	public int valid(byte[] data) {
		return loraProxy.valid(data);
	}

	@Override
	public ProtocolConfig decode(byte[] data) throws Exception {		
		return loraProxy.decode(data);
	}

	@Override
	public byte[] encode(ProtocolConfig config) throws Exception {
		return loraProxy.encode(config);
	}

	public void setLoraProxy(LoraProxy loraProxy) {
		this.loraProxy = loraProxy;
	}

}
