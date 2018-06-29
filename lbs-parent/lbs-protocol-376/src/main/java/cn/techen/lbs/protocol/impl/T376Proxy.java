package cn.techen.lbs.protocol.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.t376.T376Config;
import cn.techen.lbs.protocol.t376.T376Config.DIR;
import cn.techen.lbs.protocol.t376.T376Config.FUNC0;
import cn.techen.lbs.protocol.t376.T376Config.FUNC1;
import cn.techen.lbs.protocol.t376.T376Config.TPV;
import cn.techen.lbs.protocol.t376.common.Local;
import cn.techen.lbs.protocol.t376.T376Config.ACD;
import cn.techen.lbs.protocol.t376.T376Config.AFN;
import cn.techen.lbs.protocol.t376.T376Config.CON;
import cn.techen.lbs.protocol.t376.T376Frame;

public class T376Proxy {

	public ProtocolConfig decode(byte[] data) throws Exception {
		T376Frame frame = new T376Frame();
		frame.setBytes(data);
		frame.decode();
		
		T376Config config = (T376Config) frame.config();
		ProtocolConfig protocolConfig = new DefaultProtocolConfig();
		protocolConfig.setCommAddr(config.getCommAddr());
		dir2dir(config, protocolConfig);
		op2control(config, protocolConfig);
		protocolConfig.runs().putAll(config.runs());
		protocolConfig.funcs().addAll(config.funcs());
		protocolConfig.units().addAll(config.units());
		protocolConfig.funcKeys().putAll(config.funcKeys());
		return protocolConfig;
	}

	public byte[] encode(ProtocolConfig config) throws Exception {
		T376Frame t376Frame = new T376Frame();

		T376Config t376Config = (T376Config) t376Frame.config();
		t376Config.setCommAddr(config.getCommAddr()).setSeq(Local.sequence());
		dir2dir(config, t376Config);
		data2data(config, t376Config);
		op2control(config, t376Config);	
		t376Config.runs().putAll(config.runs());
		t376Config.funcs().addAll(config.funcs());
		t376Config.units().addAll(config.units());
		t376Frame.encode();

		return t376Frame.getBytes();
	}
	
	private void dir2dir(T376Config t376Config, ProtocolConfig config) {
		switch (t376Config.getDir()) {
		case CLIENT:
			config.setDir(ProtocolConfig.DIR.CLIENT);
			break;
		case SERVER:
			config.setDir(ProtocolConfig.DIR.SERVER);
			break;
		}
	}
	
	private void op2control(T376Config t376Config, ProtocolConfig config) {
		switch (t376Config.getAfn()) {
		case CONFIRM:
			config.setOperation(ProtocolConfig.OPERATION.CONFIRM);
			break;
		case LINK:
			List<String> dadt = t376Config.funcs();
			String[] array = dadt.get(0).split(":");			
			int fn = Integer.parseInt(array[1]);
			if (fn == 1) {
				config.setOperation(ProtocolConfig.OPERATION.LOGIN);
			} else if (fn == 2) {
				config.setOperation(ProtocolConfig.OPERATION.LOGOUT);
			} else if (fn == 3) {
				config.setOperation(ProtocolConfig.OPERATION.HEARTBEAT);
			}
			break;
		default:
			break;
		}
	}

	private void dir2dir(ProtocolConfig config, T376Config t376Config) {
		switch (config.getDir()) {
		case CLIENT:
			t376Config.setDir(DIR.CLIENT);
			break;
		case SERVER:
			t376Config.setDir(DIR.SERVER);
			break;
		}
	}
	
	private void data2data(ProtocolConfig config, T376Config t376Config) {
		Map<String, Object> datas = config.runs();
		int prm = (datas.get("PRM") == null) ? 0 : Integer.parseInt(datas.get("PRM").toString());
		int acd = (datas.get("ACD") == null) ? 0 : Integer.parseInt(datas.get("ACD").toString());
		int tpv = (datas.get("TPV") == null) ? 0 : Integer.parseInt(datas.get("TPV").toString());
		t376Config.setPrm(prm);
		t376Config.setAcd(ACD.valueOf(acd));
		t376Config.setTpv(TPV.valueOf(tpv));
	}

	private void op2control(ProtocolConfig config, T376Config t376Config) {
		switch (config.getOperation()) {
		case GET:
			break;
		case SET:
			break;
		case TRANSPORT:
			break;
		case ACTION:
			break;
		case PROXY:
			break;
		case HEARTBEAT:
			t376Config.setFunc(FUNC1.LINK.value()).setAfn(AFN.LINK).setFir(1).setFin(1).setCon(CON.YES);
			t376Config.funcs().add("0:3");
			t376Config.units().add(new Date());
			break;
		case LOGIN:
			t376Config.setFunc(FUNC1.LINK.value()).setAfn(AFN.LINK).setFir(1).setFin(1).setCon(CON.YES);
			t376Config.funcs().add("0:1");
//			t376Config.unit().add(new Date());
			break;
		case CONFIRM:
			t376Config.setFunc(FUNC0.LINK.value()).setAfn(AFN.CONFIRM).setFir(1).setFin(1).setCon(CON.NO);
			break;
		default:
			break;
		}
	}

}
