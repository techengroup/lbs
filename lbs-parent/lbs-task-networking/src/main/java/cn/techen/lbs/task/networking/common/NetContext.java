package cn.techen.lbs.task.networking.common;

import cn.techen.lbs.db.api.MeterService;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.mm.api.MLbsService;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MRegisterService;
import cn.techen.lbs.mm.api.MRelayService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolManagerService;

public class NetContext {
	
	public final Priority PRIORITY = Priority.NETWORKING;
	
	private State state = State.FINISHED;
	
	private MeterService meterService;
	
	private MLbsService mLbsService;
	
	private MMeterService mMeterService;
	
	private MRegisterService mRegisterService;
	
	private MRelayService mRelayService;

	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;
	
	private LBS lbs;
	
	private Meter meter;
	
	private ProtocolFrame frame;

	public LBS getLbs() {
		if (lbs == null) {
			lbs = mLbsService.get();
		}
		return lbs;
	}

	public Meter getMeter() {
		if (meter == null) {
			meter = mRegisterService.rpop();
		}
		return meter;
	}
	
	public void reset(boolean isSwitch) {
		if (isSwitch) {
			frame = null;
			meter = null;			
		} else {
			frame.reset();
			meter.running().reset();
		}
		this.state = State.FINISHED;
	}

	public ProtocolFrame getFrame() {
		return frame;
	}

	public void setFrame(ProtocolFrame frame) {
		this.frame = frame;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public MeterService getMeterService() {
		return meterService;
	}

	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
	}

	public void setmLbsService(MLbsService mLbsService) {
		this.mLbsService = mLbsService;
	}
	
	public MMeterService getmMeterService() {
		return mMeterService;
	}

	public void setmMeterService(MMeterService mMeterService) {
		this.mMeterService = mMeterService;
	}

	public MRegisterService getmRegisterService() {
		return mRegisterService;
	}

	public void setmRegisterService(MRegisterService mRegisterService) {
		this.mRegisterService = mRegisterService;
	}

	public MRelayService getmRelayService() {
		return mRelayService;
	}

	public void setmRelayService(MRelayService mRelayService) {
		this.mRelayService = mRelayService;
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
