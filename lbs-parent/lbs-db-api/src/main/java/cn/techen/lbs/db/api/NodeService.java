package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.List;

import cn.techen.lbs.db.model.Node;

/**
 * NodeService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface NodeService extends IService<Node> {

    List<Node> selectUnregister();

	List<Node> selectRelay();

	Node selectRelay(Node entity);
	
	int updateSuccess(Node entity);

	int updateFail(Node entity, boolean changeStatus);
	
	int updateRelay(Node entity);

	int reNet(Node entity);

	Node selectPrimeRelay(int sector, double distance);

	int selectExecTimesWithRelay(int relayId);
	
	Node selectSecondaryRelay(int nodeId, int sector, double distance);

	Node selectOtherRelay(int nodeId, int sector, int sRange, int districtX, int xRange, float angle);

	int clearRoute(int nodeId);

	int sucess(int nodeId, String commAddr, int grade, int parent, String path, String route, int relay, Date startTime,
			Date endTime, int RSSI);

	int fail(int nodeId, String commAddr, int parent, String path, String route, Date startTime, Date endTime,
			int RSSI);

	int fail(int nodeId, String commAddr, int parent, String path, String route, Date startTime, Date endTime, int RSSI,
			int status);

	int selectSuccessNodeAfterNode(int sector, double distance);

	int selectSuccessNodeBeforeNode(int sector, int districtX, int xRange);

	List<Node> selectOptimalNode(int sector, int districtX);

	int optimalRelay(int id0, int relay0, int id1, int relay1, int id2, int relay2);

	
}
