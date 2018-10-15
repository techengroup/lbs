package cn.techen.lbs.task.report.common;

import cn.techen.lbs.db.api.ReportService;
import cn.techen.lbs.mm.api.MNodeService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.protocol.FrameConfig.Priority;

public class ReportContext {
	
	public final Priority PRIORITY = Priority.REPORT;
	
	private ReportService reportService;
	
	private MNodeService mNodeService;
	
	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public MNodeService getmNodeService() {
		return mNodeService;
	}

	public void setmNodeService(MNodeService mNodeService) {
		this.mNodeService = mNodeService;
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
