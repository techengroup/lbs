package cn.techen.lbs.business.common;

import cn.techen.lbs.mm.api.MLbsService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolManagerService;

public class BusinessContext {
	private MLbsService mLbsService;
	private MTaskService<byte[]> mTaskService;
	private ProtocolManagerService protocolManagerService;
	private boolean logined = false;

	public MTaskService<byte[]> getmTaskService() {
		return mTaskService;
	}

	public void setmTaskService(MTaskService<byte[]> mTaskService) {
		this.mTaskService = mTaskService;
	}

	public MLbsService getmLbsService() {
		return mLbsService;
	}

	public void setmLbsService(MLbsService mLbsService) {
		this.mLbsService = mLbsService;
	}

	public ProtocolManagerService getProtocolManagerService() {
		return protocolManagerService;
	}

	public void setProtocolManagerService(ProtocolManagerService protocolManagerService) {
		this.protocolManagerService = protocolManagerService;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}
}
