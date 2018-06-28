package cn.techen.lbs.protocol.lora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.techen.lbs.protocol.AbstractConfig;

public class DefaultLoraConfig extends AbstractConfig  implements LoraConfig {
	
	private Control control;
	private DIR dir;
	private Channel channel;
	private Relay relay;
	private Addr addr;
	private Module module;
	private Integer rssi;
	private String sourceAddr;
	private List<String> relayAddrs = new ArrayList<String>();
	private String targetAddr;
	
	@Override
	public Control getControl() {
		return control;
	}
	
	@Override
	public DefaultLoraConfig setControl(Control control) {
		this.control = control;
		return this;
	}
	
	@Override
	public DIR getDir() {
		return dir;
	}
	
	@Override
	public DefaultLoraConfig setDir(DIR dir) {
		this.dir = dir;
		return this;
	}
	
	@Override
	public Channel getChannel() {
		return channel;
	}
	
	@Override
	public DefaultLoraConfig setChannel(Channel channel) {
		this.channel = channel;
		return this;
	}
	
	@Override
	public Relay getRelay() {
		return relay;
	}
	
	@Override
	public DefaultLoraConfig setRelay(Relay relay) {
		this.relay = relay;
		return this;
	}		
	
	@Override
	public Addr getAddr() {
		return addr;
	}
	
	@Override
	public DefaultLoraConfig setAddr(Addr addr) {
		this.addr = addr;
		return this;
	}

	@Override
	public Module getModule() {
		return module;
	}

	@Override
	public DefaultLoraConfig setModule(Module module) {
		this.module = module;
		return this;
	}

	@Override
	public Integer getRSSI() {
		return rssi;
	}

	@Override
	public LoraConfig setRSSI(Integer rssi) {
		this.rssi = rssi;
		return this;
	}

	@Override
	public String getSourceAddr() {
		return sourceAddr;
	}

	@Override
	public DefaultLoraConfig setSourceAddr(String sourceAddr) {
		this.sourceAddr = sourceAddr;
		return this;
	}

	@Override
	public List<String> getRelayAddrs() {
		return relayAddrs;
	}

	@Override
	public DefaultLoraConfig addRelayAddrs(String... relayAddrs) {
		this.relayAddrs.addAll(Arrays.asList(relayAddrs));
		return this;
	}

	@Override
	public String getTargetAddr() {
		return targetAddr;
	}

	@Override
	public DefaultLoraConfig setTargetAddr(String targetAddr) {
		this.targetAddr = targetAddr;
		return this;
	}
	
}
