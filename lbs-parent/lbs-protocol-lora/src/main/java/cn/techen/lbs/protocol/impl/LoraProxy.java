package cn.techen.lbs.protocol.impl;

import java.util.List;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.lora.LoraConfig;
import cn.techen.lbs.protocol.lora.LoraConfig.Module;
import cn.techen.lbs.protocol.lora.LoraConfig.Addr;
import cn.techen.lbs.protocol.lora.LoraConfig.Channel;
import cn.techen.lbs.protocol.lora.LoraConfig.Control;
import cn.techen.lbs.protocol.lora.LoraConfig.DIR;
import cn.techen.lbs.protocol.lora.LoraConfig.Relay;
import cn.techen.lbs.protocol.lora.LoraFrame;

public class LoraProxy {
	
	public int valid(byte[] data) {
		int size = data.length;
		if (size > 2) {
			if (data[0] == 0x68) {			
				if (data[1] > 4) {
					if ((data[1] + 4) > size) {
						return 2;
					} else {
						if (data[size-1] == 0x16) {
							return 1;
						} else {
							return 0;
						}
					}
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
		
		return 2;
	}
	
	public ProtocolConfig decode(byte[] data) throws Exception {
		LoraFrame loraFrame = new LoraFrame();
		loraFrame.setBytes(data);
		loraFrame.decode();
		
		LoraConfig loraConfig = (LoraConfig) loraFrame.config();		
		ProtocolConfig protocolConfig = new DefaultProtocolConfig();
		protocolConfig.setDir(dir2dir(loraConfig, protocolConfig))
			.setOperation(op2control(loraConfig, protocolConfig)).setCommAddr(addr2addr(loraConfig));
		protocolConfig.runs().putAll(loraConfig.runs());
		protocolConfig.funcs().addAll(loraConfig.funcs());
		protocolConfig.units().addAll(loraConfig.units());
		return protocolConfig;
	}
	
	public byte[] encode(ProtocolConfig config) throws Exception {
		LoraFrame loraFrame = new LoraFrame();		
		LoraConfig loraConfig = (LoraConfig) loraFrame.config();		
		loraConfig.setDir(dir2dir(config)).setControl(op2control(config));
		loraConfig.runs().putAll(config.runs());
		addr2addr(config, loraConfig);
		data2data(config, loraConfig);
		loraConfig.funcs().addAll(config.funcs());
		loraConfig.units().addAll(config.units());
		loraFrame.encode();
		
		return loraFrame.getBytes();
	}
	
	private ProtocolConfig.DIR dir2dir(LoraConfig loraConfig, ProtocolConfig config) {
		ProtocolConfig.DIR dir = null;
		switch (loraConfig.getDir()) {
		case CLIENT:
			dir = ProtocolConfig.DIR.CLIENT;
			break;
		case SERVER:
			dir = ProtocolConfig.DIR.SERVER;
			break;
		}
		return dir;
	}
	
	private ProtocolConfig.OPERATION op2control(LoraConfig loraConfig, ProtocolConfig config) {
		ProtocolConfig.OPERATION op = null;
		switch (loraConfig.getControl()) {		
		case NET:
			op = ProtocolConfig.OPERATION.LOGIN;
			break;
		case READ:
			op = ProtocolConfig.OPERATION.GET;
			break;
		case WRITE:
			op = ProtocolConfig.OPERATION.SET;
			break;
		case TRANSPORT:
			op = ProtocolConfig.OPERATION.TRANSPORT;
			break;
		case REPORT:
			op = ProtocolConfig.OPERATION.REPORT;
			break;
		case EXIT:
			op = ProtocolConfig.OPERATION.LOGOUT;
			break;
		default:
			break;
		}
		return op;
	}
	
	private String addr2addr(LoraConfig loraConfig) {
		StringBuffer sb = new StringBuffer("");
		if (loraConfig.getModule() == Module.SLAVE) {
			String sAddr = loraConfig.getSourceAddr();
			List<String> rAddrs = loraConfig.getRelayAddrs();
			String dAddr = loraConfig.getTargetAddr();
			
			sb.append(sAddr);
			if (rAddrs.size() > 0) {
				sb.append(",");
				sb.append(String.join(",", rAddrs));			
			}
			sb.append(",");
			sb.append(dAddr);
		}
		return sb.toString();
	}

	private DIR dir2dir(ProtocolConfig config) {
		DIR dir = null;
		switch (config.getDir()) {
		case CLIENT:
			dir = DIR.CLIENT;
			break;
		case SERVER:
			dir = DIR.SERVER;
			break;
		}
		return dir;
	}

	private Control op2control(ProtocolConfig config) {
		Control control = null;
		switch (config.getOperation()) {
		case LOGIN:
			control = Control.NET;
			break;
		case GET:
			control = Control.READ;
			break;
		case SET:
			control = Control.WRITE;
			break;
		case TRANSPORT:
			control = Control.TRANSPORT;
			break;
		case REPORT:
			control = Control.REPORT;
			break;
		case LOGOUT:
			control = Control.EXIT;
			break;
		default:
			break;
		}
		return control;
	}

	private void data2data(ProtocolConfig config, LoraConfig loraConfig) {
		Channel channel = Channel.CHANNEL_0;
		Object obj = config.runs().get("Channel");
		if (obj != null && !obj.toString().trim().equals("")) {
			channel = Channel.valueOf(Integer.parseInt(obj.toString()));
		}
		
		loraConfig.setChannel(channel);
	}

	private void addr2addr(ProtocolConfig config, LoraConfig loraConfig) {
		Module module = null;
		Relay relay = null;
		Addr addr = null;	
		String sourceAddr = null; 
		String[] relayAddrs = null; 
		String targetAddr = null;
		String commAddr = config.getCommAddr();
		if (commAddr == null || commAddr.trim().isEmpty()) {
			module = Module.MASTER;
			relay = Relay.RELAY_0;
			addr = Addr.LONG;
		} else {
			String[] addrs = commAddr.split(",");
			int size = addrs.length;
			
			if (size >= 2) {
				sourceAddr = addrs[0];
				targetAddr = addrs[size-1];
				
				module = Module.SLAVE;
				relay = Relay.valueOf(size-2);
				if (sourceAddr.length() <= 2) {
					addr = Addr.SHORT;
				} else {
					addr = Addr.LONG;
				}
				
				relayAddrs = new String[size-2];
				for (int i = 1; i < (size - 1); i++) {
					relayAddrs[i-1] = addrs[i];
				}				
			} else {
				throw new IllegalArgumentException("unknown Communication address value:" + commAddr);
			}
		}		
		loraConfig.setModule(module).setRelay(relay).setAddr(addr)
		.setSourceAddr(sourceAddr).addRelayAddrs(relayAddrs).setTargetAddr(targetAddr);
	}

}
