package cn.techen.lbs.task.realtime.common;

import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.task.realtime.manager.ProcessHandler;

public class RealTimeContext {
	
	public final Priority PRIORITY = Priority.REALTIME;
	
	private State state = State.FINISHED;
	
	private MMeterService mMeterService;
	
	private MTaskService<byte[]> mByteService;

	private MTaskService<ProtocolFrame> mTaskService;
	
	private MTaskService<ProtocolConfig> mConfigService;
	
	private ProtocolManagerService protocolManagerService;
	
	private ProcessHandler processHandler = new ProcessHandler();
	
	private ProtocolConfig config;
	
	private String route = "";
	
	public ProtocolConfig getConfig() {
		if (config == null) {
			config = mConfigService.rpop(MTaskService.QUEUE_TRANSFER);
		}
		
		return config;
	}
	
	public void reset() {
		config = null;
		setRoute("");
		setState(State.FINISHED);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	public void fireEncode(byte[] bFrame) {
		try {
			processHandler.encode(this, bFrame);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
	}
	
	public void fireDecode(ProtocolFrame frame) {
		try {
			processHandler.decode(this, frame);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
	}

	public MMeterService getmMeterService() {
		return mMeterService;
	}

	public void setmMeterService(MMeterService mMeterService) {
		this.mMeterService = mMeterService;
	}

	public MTaskService<byte[]> getmByteService() {
		return mByteService;
	}

	public void setmByteService(MTaskService<byte[]> mByteService) {
		this.mByteService = mByteService;
	}

	public MTaskService<ProtocolFrame> getmTaskService() {
		return mTaskService;
	}

	public void setmTaskService(MTaskService<ProtocolFrame> mTaskService) {
		this.mTaskService = mTaskService;
	}

	public MTaskService<ProtocolConfig> getmConfigService() {
		return mConfigService;
	}

	public void setmConfigService(MTaskService<ProtocolConfig> mConfigService) {
		this.mConfigService = mConfigService;
	}

	public ProtocolManagerService getProtocolManagerService() {
		return protocolManagerService;
	}

	public void setProtocolManagerService(ProtocolManagerService protocolManagerService) {
		this.protocolManagerService = protocolManagerService;
	}
	
}
