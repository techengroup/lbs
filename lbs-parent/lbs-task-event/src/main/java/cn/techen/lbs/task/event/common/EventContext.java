package cn.techen.lbs.task.event.common;

import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.db.api.ReportService;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MReportService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.event.manager.ProcessHandler;
import cn.techen.lbs.protocol.ProtocolManagerService;

public class EventContext {
	
	public final Priority PRIORITY = Priority.EVENT;
	
	private State state = State.FINISHED;
	
	private GeneralService generalService;
	
	private ReportService reportService;
	
	private MMeterService mMeterService;
	
	private MReportService mReportService;

	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;
	
	private ProcessHandler processHandler = new ProcessHandler();
	
	private Report report;

	public Report getReport() {
		if (report == null) {
			report = mReportService.rpop();
		}
		return report;
	}
	
	public void reset() {
		report = null;
		this.state = State.FINISHED;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void fireEncode(Report report) {
		try {
			processHandler.encode(this, report);
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

	public GeneralService getGeneralService() {
		return generalService;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

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

	public MReportService getmReportService() {
		return mReportService;
	}

	public void setmReportService(MReportService mReportService) {
		this.mReportService = mReportService;
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
