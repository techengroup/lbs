package cn.techen.lbs.device.common;

import java.util.LinkedList;
import java.util.Queue;

import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.device.manager.ProcessHandler;
import cn.techen.lbs.mm.api.MLbsService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.FrameConfig.State;

public class DeviceContext {
	public final Priority PRIORITY = Priority.DETECT;	
	private State state = State.FINISHED;
	private LBS lbs;
	private MLbsService mLbsService;
	private MTaskService<ProtocolFrame> mTaskService;
	private ProtocolManagerService protocolManagerService;	
	private ProcessHandler processHandler = new ProcessHandler();
	private Queue<ProtocolFrame> detectQueue = new LinkedList<ProtocolFrame>();
	
	public LBS getLbs() {
		return lbs;
	}

	public void setLbs(LBS lbs) {
		this.lbs = lbs;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public Queue<ProtocolFrame> detectQueue() {
		return detectQueue;
	}
	
	public void fireDecode(ProtocolFrame frame) {
		try {
			processHandler.decode(this, frame);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
	}
	
	public void fireEncode(Integer fn) {
		try {
			processHandler.encode(this, fn, lbs);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
	}
	
	public void fireWrite(ProtocolFrame frame) {
		try {
			processHandler.write(this, frame);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
	}

	public MLbsService getmLbsService() {
		return mLbsService;
	}

	public void setmLbsService(MLbsService mLbsService) {
		this.mLbsService = mLbsService;
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
