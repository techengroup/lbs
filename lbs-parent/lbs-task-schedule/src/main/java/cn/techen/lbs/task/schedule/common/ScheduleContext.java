package cn.techen.lbs.task.schedule.common;

import cn.techen.lbs.db.api.ReportService;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.protocol.FrameConfig.Priority;

public class ScheduleContext {
	
	public final Priority PRIORITY = Priority.EVENT;
	
	private ReportService reportService;
	
	private MMeterService mMeterService;
	
	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public MMeterService getmMeterService() {
		return mMeterService;
	}

	public void setmMeterService(MMeterService mMeterService) {
		this.mMeterService = mMeterService;
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
