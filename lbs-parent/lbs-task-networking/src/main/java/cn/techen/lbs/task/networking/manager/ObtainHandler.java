package cn.techen.lbs.task.networking.manager;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.task.networking.common.NetContext;
import cn.techen.lbs.protocol.FrameConfig.State;

public class ObtainHandler extends AbstractHandler {
	
	public ObtainHandler() {
		super();
	}

	@Override
	public void operate(NetContext context) throws Exception {		
		if (State.FINISHED == context.getState()) {
			Meter meter = context.getMeter();
			if (meter != null) {
				boolean routed = true;
				if (context.getFrame() == null) {	
					if (meter.getStatus() == -1 || meter.getFailTimes() < 2) {
						Meter relay = context.getmRelayService().getOptimal(meter.getSector(), 0);
						routed = route(context, "0/", Global.lbs.getCommaddr(), 0, 0, meter, relay);
					} else {
						routed = routeN(context, meter);
					}					
				}
				
				if (routed) getHandler().operate(context);
			}
		}		
	}
	
	private boolean route(NetContext context, String path, String pathAddr, int parent, int grade, Meter meter, Meter pRelay) {	
		if (pRelay == null) {
			meter.setGrade(grade);
			meter.setParent(parent);
			meter.setPath(path + meter.getId() + "/");
			meter.running().setRoute(pathAddr + "," + meter.getCommaddr());
			meter.running().setRelayId(parent);
		} else {
			if (meter.getDistance() > pRelay.getDistance()) {
				grade++;
				parent = pRelay.getId();
				path += pRelay.getId() + "/";
				pathAddr += "," + pRelay.getCommaddr();
				Meter nRelay = context.getmRelayService().getOptimal(meter.getSector(), grade);
				route(context, path, pathAddr, parent, grade, meter, nRelay);
			}
		}		
		
		return true;
	}
	
	private  boolean routeN(NetContext context, Meter meter) {	
		Meter relayN = context.getMeterService().selectRelay(meter);
		if (relayN != null) {
			meter.setGrade(relayN.getGrade() + 1);
			meter.setParent(relayN.getId());
			meter.setPath(relayN.getPath() + meter.getId() + "/");
			meter.setPathtype(1);
			meter.running().setRoute(relayN.running().getRoute() + "," + meter.getCommaddr());
			meter.running().setRelayId(relayN.getId());
			return true;
		} else {
			context.getMeterService().reNet(meter);
			context.reset(true);
		}
		
		return false;
	}
	
}
