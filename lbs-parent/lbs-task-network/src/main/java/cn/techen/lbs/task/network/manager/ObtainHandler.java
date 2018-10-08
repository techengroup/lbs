package cn.techen.lbs.task.network.manager;

import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.network.common.NetContext;

public class ObtainHandler extends AbstractHandler {
	
	public ObtainHandler() {
		super();
	}

	@Override
	public void operate(NetContext context) throws Exception {		
		if (State.FINISHED == context.getState()) {
			Node node = context.getNode();
			if (node != null) {
				context.fireWrite();
			}
		}		
	}
	
}
