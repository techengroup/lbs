package cn.techen.lbs.task.month.common;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import cn.techen.lbs.db.api.GeneralService;
import cn.techen.lbs.db.api.MeterService;
import cn.techen.lbs.db.common.DataConfig.ENERGY;
import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.mm.api.MMeterService;
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
	
	private MeterService meterService;	

	private MMeterService mMeterService;

	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;
	
	private ProcessHandler processHandler = new ProcessHandler();
	
	private Queue<Month> months = new LinkedList<Month>();
	
	private Month month;

	public Queue<Month> months() {
		return months;
	}
	
	public void load() throws ParseException {
		Date time = new Date();
		String ms = GlobalUtil.date2String(time, "yyyy-MM-01");
		time = GlobalUtil.string2Date(ms, "yyyy-MM-01");
		months.addAll(meterService.selectMonth(ENERGY.ACTIVE, time));//正向有功
//		months.addAll(meterService.selectMonth(ENERGY.NEGATIVE, time));//正向无功
	}
	
	public Month getMonth() {
		if (month == null) {
			month = months.poll();
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

	public MeterService getMeterService() {
		return meterService;
	}

	public MMeterService getmMeterService() {
		return mMeterService;
	}

	public void setmMeterService(MMeterService mMeterService) {
		this.mMeterService = mMeterService;
	}

	public void setMeterService(MeterService meterService) {
		this.meterService = meterService;
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
