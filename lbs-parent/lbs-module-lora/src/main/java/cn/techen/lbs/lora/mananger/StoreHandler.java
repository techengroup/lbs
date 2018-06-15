package cn.techen.lbs.lora.mananger;

import java.util.Date;

import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.mm.api.MTaskService;

public class StoreHandler extends AbstractHandler {

	public StoreHandler() {
		super();
	}

	@Override
	public void operate(LoraContext context) throws Exception {
		int flag = context.getFlag();
		if (flag == 1 || flag == 3) {
			context.getFrame().setrInTime(new Date());
			context.getmTaskService().lpush(MTaskService.QUEUE_RETURN + context.getFrame().getPriority().value(), context.getFrame());		
			context.setFrame(null);
		}		
		if (flag == 2 || flag == 3) {
			context.getReportFrame().setrInTime(new Date());
			context.getmTaskService().lpush(MTaskService.QUEUE_RETURN + context.getReportFrame().getPriority().value(), context.getReportFrame());	
			context.setReportFrame(null);
		}
		context.setFlag(0);
	}

}
