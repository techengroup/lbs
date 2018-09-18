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
		context.getFrame().setrInTime(new Date());
		context.getmTaskService().lpush(MTaskService.QUEUE_RETURN + context.getPriority().value(), context.getFrame());		
		context.reset();
	}

}
