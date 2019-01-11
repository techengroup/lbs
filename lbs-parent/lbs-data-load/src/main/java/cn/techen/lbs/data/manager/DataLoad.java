package cn.techen.lbs.data.manager;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.data.common.Local;
import cn.techen.lbs.db.api.FnService;
import cn.techen.lbs.db.api.LbsService;
import cn.techen.lbs.db.api.NodeService;
import cn.techen.lbs.db.api.ParamService;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.db.model.Fn;
import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.db.model.Param;
import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.mm.api.MNodeService;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.common.FnNames;
import cn.techen.lbs.protocol.common.Titles;

public class DataLoad implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private LbsService lbsService;
	private ParamService paramService;
	private FnService fnService;
	private NodeService nodeService;	
	private MBaseService mBaseService;
	private MNodeService mNodeService;
	
	@Override
	public void run() {
		while (true) {
			try {				
				if (Global.DBReady && Global.MBReady) start();
				
				Thread.sleep(Local.INTERVALMILLIS);
			} catch (Exception e) {	
				logger.error(Global.getStackTrace(e));
			}
		}
	}
	
	public void start() throws Exception {
		LBS lbs = null;
		List<Fn> fns = null;
		List<Param> params = null;
		List<Node> nodes = null;
		
		if (Local.LASTTIME == null) {
			mBaseService.flushDB();
			
			Local.LASTTIME = new Date();
			
			lbs = lbsService.selectByKey(0);
			params = paramService.selectAll();
			fns = fnService.selectAll();
			nodes = nodeService.selectAll();
			
			if (lbs == null) {
				logger.error("There is no any lbs in the database...");
				return;
			}
			if (params == null || params.size() <= 0) {
				logger.error("There is no any run param in the database...");
				return;
			}
			if (fns == null || fns.size() <= 0) {
				logger.error("There is no any protocol function in the database...");
				return;
			}
			
			load(lbs, params, fns, nodes);
		} else {
			Date nowTime = new Date();
			
			lbs = lbsService.selectByTime(Local.LASTTIME);
			fns = fnService.selectByTime(Local.LASTTIME);
			nodes = nodeService.selectByTime(Local.LASTTIME);
			params = paramService.selectByTime(Local.LASTTIME);
			
			Local.LASTTIME = nowTime;
			
			load(lbs, params, fns, nodes);
		}
	}
	
	private void load(LBS lbs, List<Param> params, List<Fn> fns, List<Node> nodes) {

		if (lbs != null) {			
			if (lbs.getChannel() != null) {
				Global.ChannelReady = true;
			}
			if (lbs.getLongitude() != null && lbs.getLatitude() != null) {
				Global.GISReady = true;
			}
			if (lbs.getIp() != null && lbs.getPort() != null) {
				Global.IPReady = true;
			}
			Global.lbs = lbs;
			logger.info("LBS parameters have been modified...");
		}
		
		if (params != null && params.size() > 0) {
			for (Param param : params) {
				Global.RunParams.put(param.getKey(), param.getValue());
			}
			logger.info("Load run parameter[{}] from database...", params.size());
		}	
		
		if (fns != null && fns.size() > 0) {
			for (Fn fn : fns) {
				String key = fn.getProtocol() + ":" + fn.getDirection() + ":" + fn.getOperation() + ":" + fn.getFunction();
				FnNames.getInstace().put(key, (fn.getName() == null) ? "" : fn.getName());				
				Elements.getInstace().put(key, (fn.getElements() == null) ? "" : fn.getElements());
				Titles.getInstace().put(key, (fn.getTitles() == null) ? "" : fn.getTitles());
			}
			logger.info("Load protocol function[{}] from database...", fns.size());
		}	
		
		if (nodes != null && nodes.size() > 0) {
			mNodeService.put(nodes);
			logger.info("Load node[{}] from database...", nodes.size());
		}
	}
	
	public void setLbsService(LbsService lbsService) {
		this.lbsService = lbsService;
	}

	public void setParamService(ParamService paramService) {
		this.paramService = paramService;
	}

	public void setFnService(FnService fnService) {
		this.fnService = fnService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setmBaseService(MBaseService mBaseService) {
		this.mBaseService = mBaseService;
	}

	public void setmNodeService(MNodeService mNodeService) {
		this.mNodeService = mNodeService;
	}
	
}
