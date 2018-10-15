package cn.techen.lbs.db.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.techen.lbs.db.model.Node;

/**
 * NodeService
 * @author ZY
 * @since 2018-03-14 16:55
 */
public interface NodeService extends IService<Node> {
	
	List<Node> selectByTime(Date time);

	List<Node> selectGIS();

	int updateGIS(List<Node> entitys);

    List<Node> selectUnregister();

	Node selectPrimeRelay(int sector, double distance);

	int selectExecTimesWithOptimalRelay(int nodeId, int relayId);
	
	Node selectSecondaryRelay(int nodeId, int sector, double distance);

	int selectSecondaryRelayAmount(int nodeId);

	Node selectOtherRepeater(int nodeId, int sector, int sRange, int districtX, int xRange);

	Node selectOtherRelay(int nodeId, int sector, int sRange, int districtX, int xRange, float angle);

	int clearRoute(int nodeId);

	int saveSuccess(int nodeId, String commAddr, int grade, int parent, String path, String route, int relay,
			Date startTime, Date endTime, int RSSI);

	int saveFailSingle(int nodeId, String commAddr, int parent, String path, String route, Date startTime, Date endTime,
			Integer RSSI);

	int saveFailCompletely(int nodeId, String commAddr, int parent, String path, String route, Date startTime,
			Date endTime, Integer RSSI, int status);
	
	int selectSuccessNodeAfterNode(int sector, double distance);

	Map<String, Integer> selectTotalAndFailNode(int sector, int districtX);

	int selectSuccessNodeBeforeNode(int sector, double distance);

	List<Node> selectOptimalNode(int sector, int districtX);

	int optimalRelay(List<Node> nodes);
	
}
