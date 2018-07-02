package cn.techen.lbs.task.schedule.manager;

import cn.techen.lbs.task.schedule.common.ScheduleContext;

public class ReadHandler extends AbstractHandler {

	@Override
	public void operate(ScheduleContext context) throws Exception {
//		if (State.RECIEVING == context.getState()) {
//			ProtocolFrame frame = context.getmTaskService().rpop(MTaskService.QUEUE_RETURN + context.PRIORITY.value());
//			if (frame != null) {
//				frame.setrOutTime(new Date());
//				context.fireDecode(frame);			
//			}
//		}
	}

}
