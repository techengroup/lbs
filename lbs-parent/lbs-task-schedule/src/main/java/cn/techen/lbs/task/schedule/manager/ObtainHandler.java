package cn.techen.lbs.task.schedule.manager;

public class ObtainHandler extends AbstractHandler {

//	@Override
//	public void operate(ScheduleContext context) throws Exception {	
//		if (State.FINISHED == context.getState()) {	
//			ProtocolFrame eventFrame = context.eventQueue().poll();
//			if (isExec(context, eventFrame)) {
//				context.fireWrite(eventFrame);
//			}
//		}
//	}
//	
//	private boolean isExec(ScheduleContext context, ProtocolFrame frame) {
//		if (frame != null) {		
//			Date newTime = frame.getNewTime();
//			Date outTime = frame.getrOutTime();
//			
//			if (outTime == null) return true;
//			
//			long nDiff = new Date().getTime() - newTime.getTime();
//			long oDiff = new Date().getTime() - outTime.getTime();
//			
//			if (nDiff < Local.TIMEOUTMILLIS) return true;
//			
//			if (oDiff > Local.SUSPENDMILLIS) {
//				frame.reset();
//				return true;
//			} else {
//				context.eventQueue().add(frame);
//			}
//		}
//		
//		return false;
//	}
	
}
