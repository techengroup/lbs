package cn.techen.lbs.task.month.common;

import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.db.api.MonthService;
import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.api.MMonthService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.task.month.manager.ProcessHandler;

public class MonthContext {
	
	public final Priority PRIORITY = Priority.MONTH;
	
	private State state = State.FINISHED;
	
	private GeneralService generalService;
	
	private MonthService monthService;	
	
	private MMonthService mMonthService;

	private MMeterService mMeterService;

	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;
	
	private ProcessHandler processHandler = new ProcessHandler();
	
	private Month month;
	
	public Month getMonth() {
		if (month == null) {
			month = mMonthService.rpop();
		}
		
		return month;
	}
	
	public void reset() {
		month = null;
		this.state = State.FINISHED;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public void fireEncode(Month month) {
		try {
			processHandler.encode(this, month);
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
	
	public MonthService getMonthService() {
		return monthService;
	}

	public void setMonthService(MonthService monthService) {
		this.monthService = monthService;
	}
	public MMeterService getmMeterService() {
		return mMeterService;
	}

	public void setmMeterService(MMeterService mMeterService) {
		this.mMeterService = mMeterService;
	}

	public MMonthService getmMonthService() {
		return mMonthService;
	}

	public void setmMonthService(MMonthService mMonthService) {
		this.mMonthService = mMonthService;
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
