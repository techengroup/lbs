package cn.techen.lbs.business.common;

import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.db.api.ParamService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolManagerService;

public class BusinessContext {
	private GeneralService generalService;
	private ParamService paramService;
	private MTaskService<byte[]> mTaskService;
	private MTaskService<ProtocolConfig> mConfigService;
	private ProtocolManagerService protocolManagerService;
	private boolean logined = false;

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}
	
	public GeneralService getGeneralService() {
		return generalService;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	public ParamService getParamService() {
		return paramService;
	}

	public void setParamService(ParamService paramService) {
		this.paramService = paramService;
	}

	public MTaskService<byte[]> getmTaskService() {
		return mTaskService;
	}

	public void setmTaskService(MTaskService<byte[]> mTaskService) {
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
