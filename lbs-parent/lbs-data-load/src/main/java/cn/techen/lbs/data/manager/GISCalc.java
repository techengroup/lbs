package cn.techen.lbs.data.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.db.api.NodeService;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Node;

public class GISCalc implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private NodeService nodeService;
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(Local.INTERVALMILLIS);
				
				if (Global.GISReady) start();
			} catch (InterruptedException e) {
				logger.error(Global.getStackTrace(e));
			} catch (Exception e) {		
				logger.error(Global.getStackTrace(e));
			}
		}
	}
	
	public void start() throws Exception {
		List<Node> nodes = nodeService.selectGIS();
		logger.info("Get GIS meter[{}] need caculate from database...", nodes.size());
		
		for (Node node : nodes) {
			double distance = Local.distance(Global.lbs.getLatitude(), Global.lbs.getLongitude(), node.getLatitude(), node.getLongitude());		
			float angle = (float) Local.angle(Global.lbs.getLatitude(), Global.lbs.getLongitude(), node.getLatitude(), node.getLongitude());
			int sector = Local.sector(angle);
			int districtX = Local.districtX(distance);
			int districtY = Local.districtY(angle);
			
			node.setDistance(distance);
			node.setAngle(angle);
			node.setSector(sector);
			node.setDistrictX(districtX);
			node.setDistrictY(districtY);
		}
		
		if (nodes != null && nodes.size() > 0) {
			nodeService.updateGIS(nodes);
		}		
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	
}
