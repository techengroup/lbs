package cn.techen.lbs.business.manager;

import java.util.HashMap;
import java.util.Map;

import cn.techen.lbs.business.common.BusinessContext;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;

public class BusinessProcesser {
	
	private Map<Integer, AbstractHandler> handlerMap = new HashMap<Integer, AbstractHandler>();
	
	public BusinessProcesser() {
		handlerMap.put(OPERATION.GET.value(), new GetHandler());
		handlerMap.put(OPERATION.SET.value(), new SetHandler());
		handlerMap.put(OPERATION.ACTION.value(), new ActionHandler());
		handlerMap.put(OPERATION.TRANSPORT.value(), new TransportHandler());
		handlerMap.put(OPERATION.LOGIN.value(), new LoginHandler());
		handlerMap.put(OPERATION.HEARTBEAT.value(), new HeartbeatHandler());
		handlerMap.put(OPERATION.LOGOUT.value(), new LogoutHandler());
		handlerMap.put(OPERATION.CONFIRM.value(), new ConfirmHandler());		
	}
		
	public void operate(BusinessContext context) throws Exception {		
		byte[] data = context.getmTaskService().rpop(MTaskService.QUEUE_4G_RECEIVE);
		AbstractHandler handler = null;
		
		if (data != null && data.length > 0) {
			if (data.length == 1) {
				handler = handlerMap.get((int)data[0]);
				handler.operate(context, null);
			} else {				
				ProtocolConfig config = context.getProtocolManagerService().getProtocol(Global.lbs.getProtocol()).decode(data);
				handler = handlerMap.get(config.getOperation().value());
				if (handler != null) {
					handler.operate(context, config);
				}
			}
		}
	}
	
}
