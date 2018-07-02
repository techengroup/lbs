package cn.techen.lbs.task.report.manager;

import java.util.Date;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.task.report.common.ReportContext;
import cn.techen.lbs.protocol.FrameConfig.Priority;

public class ReportHandler extends AbstractHandler {

	@Override
	public void operate(ReportContext context) throws Exception {		
		ProtocolFrame reportFrame = context.getmTaskService().rpop(MTaskService.QUEUE_RETURN + Priority.REPORT.value());
		if (reportFrame != null) {
			reportFrame.setrOutTime(new Date());
			
			String route = reportFrame.getCommAddr();
			String commAddr = getRealAddr(route);	
			if (commAddr != null && !commAddr.isEmpty()) {
				Meter meter = context.getmMeterService().get(commAddr);
				
				Report report = new Report();
				report.setMeterid(meter.getId());
				report.setCommaddr(commAddr);
				report.setRoute(route);
				report.setReporttime(reportFrame.getNewTime());
				context.getReportService().save(report);
			}
		}
	}
	
	private String getRealAddr(String commAddr) {
		String[] addrs = commAddr.split(",");
		return addrs[addrs.length-1];
	}

}
