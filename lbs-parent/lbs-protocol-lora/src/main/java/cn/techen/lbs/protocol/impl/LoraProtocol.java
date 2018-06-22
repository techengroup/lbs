package cn.techen.lbs.protocol.impl;

import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.protocol.lora.common.Local;

public class LoraProtocol implements ProtocolService {
	
	private LoraProxy loraProxy;

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
