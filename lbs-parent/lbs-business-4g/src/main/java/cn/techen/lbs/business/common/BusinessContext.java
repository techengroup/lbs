package cn.techen.lbs.business.common;

import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.mm.api.MLbsService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolManagerService;

public class BusinessContext {
	private GeneralService generalService;
	private MLbsService mLbsService;
	private MTaskService<byte[]> mTaskService;
	private ProtocolManagerService protocolManagerService;
	private boolean logined = false;

	public GeneralService getGeneralService() {
		return generalService;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
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
