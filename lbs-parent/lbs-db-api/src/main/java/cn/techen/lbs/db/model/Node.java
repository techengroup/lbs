package cn.techen.lbs.db.model;

/**
 * Node
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Node extends MyModel {
	
	private static final long serialVersionUID = 7531706532671275897L;
	
	private Integer pointno;
	private String commaddr;
	private Integer status;
	private Integer protocol;
	private Integer moduleprotocol;
	private Double longitude;
	private Double latitude;
	private Double distance;
	private Float angle;
	private Integer sector;
	private Integer districtX;
	private Integer districtY;
	private Integer grade;
	private Integer parent;
	private String path;
	private String route;
	private Integer relay;
	private Integer deviceclass;
	//Extend Properties
	private Node relayNode;
	private Integer rssi;
	/**
	 *  0: relay is not prime or second node in identical sector or other node in other sector; 
	 *  1: node and relay is in identical sector, relay is root, prime or second relay
	 */
//	private int relayClass;

	public Integer getPointno() {
		return pointno;
	}

	public void setPointno(Integer pointno) {
		this.pointno = pointno;
	}

	public String getCommaddr() {
		return commaddr;
	}

	public void setCommaddr(String commaddr) {
		this.commaddr = commaddr;
	}

	public Integer getDeviceclass() {
		return deviceclass;
	}

	public void setDeviceclass(Integer deviceclass) {
		this.deviceclass = deviceclass;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public Integer getModuleprotocol() {
		return moduleprotocol;
	}

	public void setModuleprotocol(Integer moduleprotocol) {
		this.moduleprotocol = moduleprotocol;
	}
	
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Float getAngle() {
		return angle;
	}

	public void setAngle(Float angle) {
		this.angle = angle;
	}

	public Integer getSector() {
		return sector;
	}

	public void setSector(Integer sector) {
		this.sector = sector;
	}

	public Integer getDistrictX() {
		return districtX;
	}

	public void setDistrictX(Integer districtX) {
		this.districtX = districtX;
	}

	public Integer getDistrictY() {
		return districtY;
	}

	public void setDistrictY(Integer districtY) {
		this.districtY = districtY;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Integer getRelay() {
		return relay;
	}

	public void setRelay(Integer relay) {
		this.relay = relay;
	}

	public boolean isRepeater() {
		return (deviceclass==0) ? true : false;
	}

	public Node getRelayNode() {
		return relayNode;
	}

	public void setRelayNode(Node relayNode) {
		this.relayNode = relayNode;
	}

	public Integer getRssi() {
		return rssi;
	}

	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}
	
}
