package cn.techen.lbs.task.report.common;

import java.util.LinkedList;
import java.util.Queue;

import cn.techen.lbs.db.api.ReportService;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.task.report.manager.ProcessHandler;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.FrameConfig.State;

public class ReportContext {
	
	public final Priority PRIORITY = Priority.EVENT;
	
	private State state = State.FINISHED;
	
	private ReportService reportService;
	
	private MMeterService mMeterService;
	
	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;
	
	private Queue<ProtocolFrame> eventQueue = new LinkedList<ProtocolFrame>();
	
	private Queue<ProtocolFrame> suspendQueue = new LinkedList<ProtocolFrame>();
	
	private ProcessHandler processHandler = new ProcessHandler();

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Queue<ProtocolFrame> eventQueue() {
		return eventQueue;
	}
	
	public Queue<ProtocolFrame> suspendQueue() {
		return suspendQueue;
	}
	
	public void fireReport(ProtocolFrame reportFrame) {
		try {
			processHandler.report(this, reportFrame);
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
	
	public void fireWrite(ProtocolFrame frame) {
		try {
			processHandler.write(this, frame);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
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
