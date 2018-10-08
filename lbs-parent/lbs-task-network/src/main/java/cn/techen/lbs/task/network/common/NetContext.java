package cn.techen.lbs.task.network.common;

import java.util.Date;

import cn.techen.lbs.db.api.NodeService;
import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.mm.api.MNodeService;
import cn.techen.lbs.mm.api.MRelayService;
import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.protocol.ProtocolFrame;
import cn.techen.lbs.protocol.FrameConfig.Priority;
import cn.techen.lbs.protocol.FrameConfig.State;
import cn.techen.lbs.protocol.ProtocolManagerService;
import cn.techen.lbs.task.network.manager.ProcessHandler;

public class NetContext {
	
	public final Priority PRIORITY = Priority.NET;
	
	private State state = State.FINISHED;
	
	private NodeService nodeService;
	
	private MNodeService mNodeService;
	
	private MRelayService mRelayService;

	private MTaskService<ProtocolFrame> mTaskService;
	
	private ProtocolManagerService protocolManagerService;
	
	private ProcessHandler processHandler = new ProcessHandler();
	
	private Node node;
	
	public Node getNode() {
		if (node == null) {
			node = mNodeService.rpop();
		}
		return node;
	}
	
	public void reset() {
		node = null;
		state = State.FINISHED;
	}

	public State getState() {
		return state;
	}
	
	public void fireWrite() {
		try {
			processHandler.write(this);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
	}
	
	public void fireRead(ProtocolFrame frame) {
		try {
			processHandler.read(this, frame);
		} catch (Exception e) {
			processHandler.exceptionCaught(this, e.getCause());
		}
	}
	
	public void write(ProtocolFrame frame) {
		state = State.SENDING;
		mTaskService.lpush(MTaskService.QUEUE_SEND + PRIORITY.value(), frame);
		frame.setwInTime(new Date());
		state = State.RECIEVING;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public MNodeService getmNodeService() {
		return mNodeService;
	}

	public void setmNodeService(MNodeService mNodeService) {
		this.mNodeService = mNodeService;
	}

	public MRelayService getmRelayService() {
		return mRelayService;
	}

	public void setmRelayService(MRelayService mRelayService) {
		this.mRelayService = mRelayService;
	}

	public MTaskService<ProtocolFrame> getmTaskService() {
		return mTaskService;
	}

	public void setmTaskService(MTaskService<ProtocolFrame> mTaskService) {
		this.mTaskService = mTaskService;
	}

	public ProtocolManagerService getProtocolManagerService() {
		return protocolManagerService;
	}

	public void setProtocolManagerService(ProtocolManagerService protocolManagerService) {
		this.protocolManagerService = protocolManagerService;
	}
	
}
