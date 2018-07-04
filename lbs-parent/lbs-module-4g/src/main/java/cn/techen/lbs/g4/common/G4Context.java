package cn.techen.lbs.g4.common;

import cn.techen.lbs.g4.manager.FrameHandler;
import cn.techen.lbs.mm.api.MTaskService;
import io.netty.channel.ChannelHandlerContext;

public class G4Context {
	private MTaskService<byte[]> mTaskService;
	private ChannelHandlerContext ctx = null;
	private FrameHandler frameHandler = new FrameHandler();
	private Status status = Status.DISCONNECT;

	public ChannelHandlerContext channel() {
		return ctx;
	}

	public void setChannel(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void fireLogin() {
		try {		
			frameHandler.login(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fireHeartbeat() {
		try {		
			frameHandler.heartBeat(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fireRead(byte[] data) {
		try {
			frameHandler.read(this, data);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fireLogout() {
		try {		
			frameHandler.logout(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MTaskService<byte[]> getmTaskService() {
		return mTaskService;
	}

	public void setmTaskService(MTaskService<byte[]> mTaskService) {
		this.mTaskService = mTaskService;
	}
	
}
