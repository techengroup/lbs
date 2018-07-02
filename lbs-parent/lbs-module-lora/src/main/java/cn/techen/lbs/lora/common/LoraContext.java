package cn.techen.lbs.lora.common;

import cn.techen.lbs.channel.rxtx.RxtxChannel;
import cn.techen.lbs.lora.mananger.LoraChannel;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolManagerService;

public class LoraContext {
	
	private int timeout = 5000;
	
	private MTaskService<ProtocolFrame> mTaskService;	

	private ProtocolManagerService protocolManagerService;
	
	private ProtocolFrame frame;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
		if (this.timeout < 5000) {
			this.timeout = 5000;
		}
	}

	public MTaskService<ProtocolFrame> getmTaskService() {
		return mTaskService;
	}

	public void setmTaskService(MTaskService<ProtocolFrame> mTaskService) {
		this.mTaskService = mTaskService;
	}

	public ProtocolManagerService getProtocolManagerService() {
		return protocolManagerService;
	}

	public void setProtocolManagerService(ProtocolManagerService protocolManagerService) {
		this.protocolManagerService = protocolManagerService;
	}	

	public ProtocolFrame getFrame() {
		return frame;
	}

	public void setFrame(ProtocolFrame frame) {
		this.frame = frame;
	}	

	public void reset() {
		this.frame = null;
	}

	public RxtxChannel channel() throws Exception {
		return LoraChannel.getInstance(this).channel();
	}
}
