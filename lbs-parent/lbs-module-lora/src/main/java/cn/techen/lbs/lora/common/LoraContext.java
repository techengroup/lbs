package cn.techen.lbs.lora.common;

import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.protocol.FrameConfig.Priority;

public class LoraContext {
	
	private MTaskService<ProtocolFrame> mTaskService;	

	private ProtocolManagerService protocolManagerService;
	
	private ProtocolFrame frame;
	
	private Priority priority;

	public ProtocolFrame getFrame() {
		return frame;
	}

	public void setFrame(ProtocolFrame frame) {
		this.frame = frame;
	}	

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void reset() {
		this.frame = null;
		this.priority = null;
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
}
