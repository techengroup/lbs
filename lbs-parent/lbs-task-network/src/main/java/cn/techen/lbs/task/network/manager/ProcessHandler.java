package cn.techen.lbs.task.network.manager;

import java.util.List;
import java.util.Map;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.ProtocolService;
import cn.techen.lbs.task.network.common.Local;
import cn.techen.lbs.task.network.common.NetContext;

public class ProcessHandler {
	
	public void write(NetContext context) throws Exception {	
		Node node = context.getNode();
		
		route(context, node);
		
		if (node.getRelayNode() != null) {
			context.write(encodeFrame(context, node));
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
				if (Integer.parseInt(Global.RunParams.get("LoraSignalThreshold").toString()) < node.getRssi()) {
					success(context, node, frame);					
				} else {
					frame.increaseRetryTimes();
					int mod = frame.getRetryTimes() % 2;
					if (mod != 0) {
						fail(context, frame, node);
						context.write(frame);						
					} else {
						failCompletely(context, frame, node);
						context.reset();
					}
				}	
			} else {
				//TODO need consider
				context.reset();
			}		
		}
	}
	
	public void exceptionCaught(NetContext context, Throwable cause) {
		cause.printStackTrace();
    	context.reset();
    }
	
	private void route(NetContext context, Node node) {
		Node relay = context.getNodeService().selectPrimeRelay(node.getSector(), node.getDistance());		
		
		if (relay == null) {
			int count = context.getNodeService().selectExecTimesWithOptimalRelay(Global.lbs.getId());
			
			if (count < 4) {//prime route try 2 times, try 2 times at same prime route, other route only try 1 times.
				Node root = new Node();
				root.setId(Global.lbs.getId());
				root.setCommaddr(Global.lbs.getCommaddr());
				root.setGrade(0);
				root.setPath(Global.lbs.getId()+"/");
				root.setRoute(Global.lbs.getId()+",");
				node.setRelayNode(root);
			} else {				
				secondaryRoute(context, node);
			}
		} else {
			int count = context.getNodeService().selectExecTimesWithOptimalRelay(relay.getId());
			
			if (count < 4) {//prime route try 2 times, try 2 times at same prime route, other route only try 1 times.
				node.setRelayNode(relay);
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
				, node.getSector(), Local.SectorRange, node.getDistrictX(), Local.XRange);	
		
		if (repeater == null) {
			Node relay = context.getNodeService().selectOtherRelay(node.getId()
					, node.getSector(), Local.SectorRange, node.getDistrictX(), Local.XRange, node.getAngle());
			
			if (relay == null) {
				context.getNodeService().clearRoute(node.getId());
			} else {
				node.setRelayNode(relay);
			}
		}
	}
	
	private ProtocolFrame encodeFrame(NetContext context, Node node) throws Exception {		
		ProtocolService protocolService = context.getProtocolManagerService().getProtocol(node.getModuleprotocol());
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr(node.getRoute()).setDir(DIR.CLIENT).setOperation(OPERATION.LOGIN);
		config.runs().put("CHANNEL", Global.lbs.getChannel());
		config.funcs().add("2");			
		config.units().add(node.getId());
		
		byte[] frame = protocolService.encode(config);
		ProtocolFrame pFrame = new ProtocolFrame();
		pFrame.setWriteBytes(frame);
		if (node.getGrade() > 0) {
			pFrame.setWriteTimes(Local.WRITETIMES_RELAY);
		} else {
			pFrame.setWriteTimes(Local.WRITETIMES);				
		}
		pFrame.setTimeout(Local.TIMEOUT);
		
		return pFrame;
	}
	
	private void success(NetContext context, Node node, ProtocolFrame frame) throws Exception {
		if (node.isRepeater()) {
			node.setRelay(2);//Node is repeater device, so it must be optimal relay. 2 means optimal relay			
		} else {
			node.setRelay(0);
		}
		
		context.getNodeService().sucess(node.getId(), node.getCommaddr()
				, node.getRelayNode().getGrade() + 1, node.getRelayNode().getId()
				, node.getRelayNode().getPath() + "/" + node.getId()
				, node.getRelayNode().getRoute() + "," + node.getCommaddr()
				, node.getRelay() , frame.getNewTime(), frame.getReadTime(), node.getRssi());
	}
	
	private void fail(NetContext context, ProtocolFrame frame, Node node) {
		context.getNodeService().fail(node.getId(), node.getCommaddr()
		, node.getRelayNode().getId()
		, node.getRelayNode().getPath() + "/" + node.getId()
		, node.getRelayNode().getRoute() + "," + node.getCommaddr()
		, frame.getNewTime(), frame.getReadTime(), node.getRssi());
	}
	
	private void failCompletely(NetContext context, ProtocolFrame frame, Node node) {
		context.getNodeService().fail(node.getId(), node.getCommaddr()
		, node.getRelayNode().getId()
		, node.getRelayNode().getPath() + "/" + node.getId()
		, node.getRelayNode().getRoute() + "," + node.getCommaddr()
		, frame.getNewTime(), frame.getReadTime(), node.getRssi(), 0);
		
		confirmRelayOrNot(context, node);
	}
	
	private void confirmRelayOrNot(NetContext context, Node node) {
		int afterSuccessNodeAmount = context.getNodeService().selectSuccessNodeAfterNode(node.getSector(), node.getDistance());
		
		if (afterSuccessNodeAmount <= 0) {
			if (confirmCriticalPoint(context, node.getSector(), node.getDistance(), node.getDistrictX(), 0)) {
				confirmRelays(context, node);
			}
		}
	}
	
	private boolean confirmCriticalPoint(NetContext context, int sector, double distance, int districtX, int deep) {
		if (deep > 0) districtX--;
		if (districtX <= 0) {		
			Map<String, Integer> map = context.getNodeService().selectTotalAndFailNode(sector, districtX);
			
			if (map != null && !map.isEmpty()) {
				int total = map.get("total");
				int fail = map.get("fail");
				
				if (deep == 0) {
					if (total == (fail+1)) {
						deep++;
						confirmCriticalPoint(context, sector, distance, districtX, deep);
					}
				} else {
					if (deep < 3) {
						if (total == fail) {
							deep++;
							confirmCriticalPoint(context, sector, distance, districtX, deep);
						}
					} else {
						int count = context.getNodeService().selectSuccessNodeBeforeNode(sector, distance);
						
						if (count > 0) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	private void confirmRelays(NetContext context, Node ONode) {
		List<Node> nodes = context.getNodeService().selectOptimalNode(ONode.getSector(), ONode.getDistrictX());
		
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
