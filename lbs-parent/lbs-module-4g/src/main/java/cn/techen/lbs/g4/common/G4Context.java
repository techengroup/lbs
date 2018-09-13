package cn.techen.lbs.g4.common;

import cn.techen.lbs.g4.manager.FrameHandler;
import cn.techen.lbs.mm.api.MTaskService;
import io.netty.channel.ChannelHandlerContext;

public class G4Context {
	private MTaskService<byte[]> mTaskService;
	private ChannelHandlerContext ctx = null;
	private FrameHandler frameHandler = new FrameHandler();

	public ChannelHandlerContext channel() {
		return ctx;
	}
	
	public void fireLogin(ChannelHandlerContext ctx) {
		try {		
			this.ctx = ctx;
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
			this.ctx = null;
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
