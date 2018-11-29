package cn.techen.lbs.protocol.impl;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.t645.T645Config;
import cn.techen.lbs.protocol.t645.T645Frame;
import cn.techen.lbs.protocol.t645.T645Config.DIR;
import cn.techen.lbs.protocol.t645.T645Config.Control;
import cn.techen.lbs.protocol.t645.T645Config.Answer;
import cn.techen.lbs.protocol.t645.T645Config.FollowUp;

public class T645Proxy {
	
	public ProtocolConfig decode(byte[] data) throws Exception {
		T645Frame frame = new T645Frame();
		frame.setBytes(data);
		frame.decode();
		
		T645Config t645Config = (T645Config) frame.config();		
		ProtocolConfig protocolConfig = new DefaultProtocolConfig();
		protocolConfig.setDir(dir2dir(t645Config, protocolConfig))
			.setOperation(op2control(t645Config, protocolConfig)).setCommAddr(t645Config.getCommAddr());
		protocolConfig.runs().putAll(t645Config.runs());
		protocolConfig.funcs().addAll(t645Config.funcs());
		protocolConfig.units().addAll(t645Config.units());
		protocolConfig.funcKeys().putAll(t645Config.funcKeys());
		return protocolConfig;
	}
	
	public byte[] encode(ProtocolConfig config) throws Exception {
		T645Frame frame = new T645Frame();	
		T645Config t645Config = (T645Config) frame.config();		
		t645Config.setDir(dir2dir(config)).setControl(op2control(config)).setCommAddr(config.getCommAddr());
		t645Config.runs().putAll(config.runs());		
		data2data(config, t645Config);
		t645Config.funcs().addAll(config.funcs());
		t645Config.units().addAll(config.units());
		frame.encode();
		
		return frame.getBytes();
	}

	private ProtocolConfig.DIR dir2dir(T645Config t645Config, ProtocolConfig config) {
		ProtocolConfig.DIR dir = null;
		switch (t645Config.getDir()) {
		case CLIENT:
			dir = ProtocolConfig.DIR.CLIENT;
			break;
		case SERVER:
			dir = ProtocolConfig.DIR.SERVER;
			break;
		}
		return dir;
	}
	
	private ProtocolConfig.OPERATION op2control(T645Config t645Config, ProtocolConfig config) {
		ProtocolConfig.OPERATION op = null;
		switch (t645Config.getControl()) {
		case READ:
			op = ProtocolConfig.OPERATION.GET;
			break;
		case WRITE:
			op = ProtocolConfig.OPERATION.SET;
			break;
		case ACTION:
			op = ProtocolConfig.OPERATION.ACTION;
			break;
		default:
			break;
		}
		config.runs().put("ANSWER", t645Config.getAnswer().value());//应答帧类型
		return op;
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
		case GET:
			control = Control.READ;
			break;
		case SET:
			control = Control.WRITE;
			break;
		case ACTION:
			control = Control.ACTION;
			break;
		default:
			break;
		}
		return control;
	}

	private void data2data(ProtocolConfig config, T645Config t645Config) {
		FollowUp follow = FollowUp.NONE;
		Answer answer = Answer.SUCCESS;
		Object obj = config.runs().get("Answer");
		
		if (obj != null && !obj.toString().trim().equals("")) {
			answer = Answer.valueOf(Integer.parseInt(obj.toString()));
		}
		
		t645Config.setAnswer(answer);
		t645Config.setFollowUp(follow);
	}
}
