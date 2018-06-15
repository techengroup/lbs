package cn.techen.lbs.task.report.manager;

import java.util.Date;

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
			context.fireReport(reportFrame);	
		}
	}

}
