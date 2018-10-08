package cn.techen.lbs.task.network.manager;

import java.util.List;

import javax.imageio.event.IIOReadWarningListener;

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
			int count = context.getNodeService().selectExecTimesWithRelay(Global.lbs.getId());
			
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
			int count = context.getNodeService().selectExecTimesWithRelay(relay.getId());
			
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
			node.setRelayNode(relay);
		}
	}
	
	private void otherRoute(NetContext context, Node node) {
		Node relay = context.getNodeService().selectOtherRelay(node.getId()
				, node.getSector(), Local.SectorRange, node.getDistrictX(), Local.XRange, node.getAngle());
		
		if (relay == null) {
			context.getNodeService().clearRoute(node.getId());
		} else {
			node.setRelayNode(relay);
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
		
		confirmRelay(context, node);
	}
	
	private void confirmRelay(NetContext context, Node node) {
		int afterSuccessNodeAmount = context.getNodeService().selectSuccessNodeAfterNode(node.getSector(), node.getDistance());
		
		if (afterSuccessNodeAmount <= 0) {
			int beforeSuccessNodeAmount = context.getNodeService().selectSuccessNodeBeforeNode(node.getSector(), node.getDistrictX(), Local.BeforeXRange);
			
			if (beforeSuccessNodeAmount <= 0) {
				List<Node> optimalNodes = context.getNodeService().selectOptimalNode(node.getSector(), node.getDistrictX());
				
				if (optimalNodes != null && optimalNodes.size() > 0) {
					confirmOptimalRelays(context, optimalNodes);
				}
			}
		}
	}
	
	private void confirmOptimalRelays(NetContext context, List<Node> nodes) {
		int x0 = 0;
		int x1 = 0;
		int x2 = 0;
		int s0 = 0;
		int s1 = 0;
		int s2 = 0;
		int id0 = 0;
		int id1 = 0;
		int id2 = 0;
		int r0 = 0;
		int r1 = 0;
		int r2 = 0;
		
		for (Node node : nodes) {
			if (node.getDistrictY() == 0) {
				id0 = node.getId();
				x0 = node.getDistrictX();
				s0 = node.getRssi();
			} else if (node.getDistrictY() == 1) {
				id1 = node.getId();
				x1 = node.getDistrictX();
				s1 = node.getRssi();
			} else if (node.getDistrictY() == 2) {
				id2 = node.getId();
				x2 = node.getDistrictX();
				s2 = node.getRssi();
			}
		}
		
		if (x0 == x1 && x1 == x2) {
			if (s0 > s1 && s0 > s2) {
				r0 = 2;
				r1 = r2 = 1;
			}
			if (s1 > s0 && s1 > s2) {
				r1 = 2;
				r0 = r2 = 1;
			}
			if (s2 > s1 && s2 > s0) {
				r2 = 2;
				r0 = r1 = 1;
			}
		} else {
			if (x0 > x1 && x0 > x2) {
				r0 = 2;
				r1 = r2 = 1;
			}
			if (x1 > x0 && x1 > x2) {
				r1 = 2;
				r0 = r2 = 1;
			}
			if (x2 > x1 && x2 > x0) {
				r2 = 2;
				r0 = r1 = 1;
			}
		}
		
		context.getNodeService().optimalRelay(id0, r0, id1, r1, id2, r2);
	}
	
}
