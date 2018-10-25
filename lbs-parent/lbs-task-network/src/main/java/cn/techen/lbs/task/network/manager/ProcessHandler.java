package cn.techen.lbs.task.network.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.task.network.common.Local;
import cn.techen.lbs.task.network.common.NetContext;

public class ProcessHandler {
	
	public void write(NetContext context) throws Exception {	
		Node node = context.getNode();
		
		route(context, node);
		
		if (node.getRelayNode() != null) {
			write(context, encodeFrame(context, node));
		} else {
			context.reset();
		}		
	}
	
	public void read(NetContext context, ProtocolFrame frame)  throws Exception {
		Node node = context.getNode();
		byte[] readBytes = frame.getReadBytes();
		
		if (readBytes != null && readBytes.length > 8) {					
			ProtocolService protocolService = context.getProtocolManagerService()
					.getProtocol(node.getModuleprotocol());
			ProtocolConfig config = protocolService.decode(readBytes);
			String readAddr = ProtocolUtil.getCommAddr(config.getCommAddr());
			node.setRssi(Integer.parseInt(config.runs().get("RSSI").toString()));
			
			if (node.getCommaddr().equals(readAddr)) {				
				if (Integer.parseInt(Global.RunParams.get("LoraSignalThreshold").toString()) <= node.getRssi()) {
					success(context, node, frame);					
				} else {
					fail(context, frame, node);
				}	
			} else {
				//TODO need consider
				context.reset();
			}		
		} else {//timeout
			fail(context, frame, node);
		}
	}
	
	public void exceptionCaught(NetContext context, Throwable cause) {
		cause.printStackTrace();
    	context.reset();
    }
	
	private void route(NetContext context, Node node) {
		Node relay = context.getNodeService().selectPrimeRelay(node.getSector(), node.getDistance());		
		
		if (relay == null) {
			int count = context.getNodeService().selectExecTimesWithOptimalRelay(node.getId(), Global.lbs.getId());
			
			if (count < 4) {//prime route try 2 times, try 2 times at same prime route, other route only try 1 times.
				Node root = new Node();
				root.setId(Global.lbs.getId());
				root.setCommaddr(Global.lbs.getCommaddr());
				root.setPath(Global.lbs.getId().toString());
				root.setRoute(Global.lbs.getCommaddr());
				node.setRelayNode(root);
				node.setOptimalRelay(true);
			} else {				
				secondaryRoute(context, node);
			}
		} else {
			int count = context.getNodeService().selectExecTimesWithOptimalRelay(node.getId(), relay.getId());
			
			if (count < 4) {//prime route try 2 times, try 2 times at same prime route, other route only try 1 times.
				node.setRelayNode(relay);
				node.setOptimalRelay(true);
			} else {
				secondaryRoute(context, node);
			}
		}
	}
	
	private void secondaryRoute(NetContext context, Node node) {
		Node relay = context.getNodeService().selectSecondaryRelay(node.getId(), node.getSector(), node.getDistance());
		
		if (relay == null) {
			otherRoute(context, node);
		} else {
			int count = context.getNodeService().selectSecondaryRelayAmount(node.getId());
			
			if (count < 2) {
				node.setRelayNode(relay);
			} else {
				otherRoute(context, node);
			}			
		}
	}
	
	private void otherRoute(NetContext context, Node node) {
		Node repeater = context.getNodeService().selectOtherRepeater(node.getId()
				, node.getSector(), Local.SEEKRELAYSECTORRANGE, node.getDistrictX(), Local.SEEKREPEATERDISTRICTXRANGE);	
		
		if (repeater == null) {
			Node relay = context.getNodeService().selectOtherRelay(node.getId()
					, node.getSector(), Local.SEEKRELAYSECTORRANGE, node.getDistrictX(), Local.SEEKRELAYDISTRICTXRANGE, node.getAngle());
			
			if (relay == null) {
				context.getNodeService().clearRoute(node.getId());
			} else {
				node.setRelayNode(relay);
			}
		} else {
			node.setRelayNode(repeater);
		}
	}
	
	private ProtocolFrame encodeFrame(NetContext context, Node node) throws Exception {		
		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(node.getModuleprotocol());
		ProtocolConfig config = new DefaultProtocolConfig();
		String route = node.getRelayNode().getRoute() + "," + node.getCommaddr();
		config.setCommAddr(route).setDir(DIR.CLIENT).setOperation(OPERATION.LOGIN);
		config.runs().put("CHANNEL", Global.lbs.getChannel());
		config.funcs().add("2");			
		config.units().add(node.getId());
		
		byte[] frame = protocolService.encode(config);
		ProtocolFrame pFrame = new ProtocolFrame();
		pFrame.setWriteBytes(frame);
		if (node.getRelayNode().getGrade() > 0) {
			pFrame.setWriteTimes(Local.WRITETIMES_RELAY);
		} else {
			pFrame.setWriteTimes(Local.WRITETIMES);				
		}
		pFrame.setTimeout(Local.TIMEOUT);
		
		return pFrame;
	}
	
	private void write(NetContext context, ProtocolFrame frame)  throws Exception {
		context.setState(State.SENDING);
		frame.setwInTime(new Date());
		context.getmTaskService().lpush(MTaskService.QUEUE_LORA_SEND + context.PRIORITY.value(), frame);
		context.setState(State.RECIEVING);
	}
	
	private void success(NetContext context, Node node, ProtocolFrame frame) throws Exception {
		if (node.isRepeater()) {
			node.setRelay(2);//Node is repeater device, so it must be optimal relay. 2 means optimal relay			
		} else {
			node.setRelay(0);
		}
		
		context.getNodeService().saveSuccess(node.getId(), node.getCommaddr()
				, node.getRelayNode().getGrade() + 1, node.getRelayNode().getId()
				, node.getRelayNode().getPath() + "/" + node.getId()
				, node.getRelayNode().getRoute() + "," + node.getCommaddr()
				, node.getRelay() , frame.getwInTime(), frame.getrOutTime(), node.getRssi());
		
		context.reset();
	}
	
	private void fail(NetContext context, ProtocolFrame frame, Node node) throws Exception {
		frame.increaseRetryTimes();
		int mod = frame.getRetryTimes() % 2;
		if (mod != 0) {
			context.getNodeService().saveFailSingle(node.getId(), node.getCommaddr()
					, node.getRelayNode().getId()
					, node.getRelayNode().getPath() + "/" + node.getId()
					, node.getRelayNode().getRoute() + "," + node.getCommaddr()
					, frame.getwInTime(), frame.getrOutTime(), node.getRssi());
			
			write(context, frame);						
		} else {
			context.getNodeService().saveFailCompletely(node.getId(), node.getCommaddr()
					, node.getRelayNode().getId()
					, node.getRelayNode().getPath() + "/" + node.getId()
					, node.getRelayNode().getRoute() + "," + node.getCommaddr()
					, frame.getwInTime(), frame.getrOutTime(), node.getRssi(), 0);
					
			if (node.isOptimalRelay()) {
				confirmRelayOrNot(context, node);
			}
			context.reset();
		}
	}
	
	private void confirmRelayOrNot(NetContext context, Node node) {
		Map<String, Integer> map = context.getNodeService().selectTotalAndFailNode(node.getSector(), node.getDistrictX());
		
		if (map != null && !map.isEmpty()) {
			int total = map.get("total");
			int fail = map.get("fail");
			
			if (total == fail && total >= 3) {				
				Node beforeNearestRelay = node.getRelayNode();
				Node beforeSuccessNode = context.getNodeService().selectSuccessNodeBeforeNode(node.getSector(), node.getDistance());
				
				if (beforeSuccessNode != null) {
					int s2s = node.getDistrictX() - beforeSuccessNode.getDistrictX();//在此结点与其最近成功注册表的距离
					int r2s = beforeSuccessNode.getDistrictX();//最近成功注册表与最近最优结点的距离					
					if (beforeNearestRelay != null) r2s = beforeSuccessNode.getDistrictX() - beforeNearestRelay.getDistrictX();
					
					if (r2s >= 5 && s2s >= 1) {
						confirmRelays(context, node.getSector(), beforeNearestRelay.getDistance(), beforeSuccessNode.getDistance());
					}
				}
			}
		}
	}
	
	private void confirmRelays(NetContext context, int sector, double priviousRelayDistance, double nearSuccessNodeDistance) {
		List<Node> nodes = context.getNodeService().selectOptimalNode(sector, priviousRelayDistance, nearSuccessNodeDistance);
		
		if (nodes != null && !nodes.isEmpty()) {
			int x = 0;
			int y = 0;
			int r = 0;		
			int id = 0;
			
			for (Node node : nodes) {
				if (x < node.getDistrictX()) {
					x = node.getDistrictX();
					y = node.getDistrictY();
					r = node.getRssi();
					id = node.getId();
				} else if (x == node.getDistrictX()) {
					if (r < node.getRssi()) {
						if (y == 1) {
							int ar = node.getRssi() - r;
							if (ar > 5) {
								x = node.getDistrictX();
								y = node.getDistrictY();
								r = node.getRssi();
								id = node.getId();
							}
						}
						if (node.getDistrictY() == 1) {
							int ar = r - node.getRssi();
							if (ar > 5) {
								x = node.getDistrictX();
								y = node.getDistrictY();
								r = node.getRssi();
								id = node.getId();
							}
						}
					}
				}			
			}
			
			for (Node node : nodes) {
				if (node.getId() == id) {
					node.setRelay(2);//最优中继
				} else {
					node.setRelay(1);//备用中继
				}
			}
			
			context.getNodeService().optimalRelay(nodes);
		}
	}
	
}
