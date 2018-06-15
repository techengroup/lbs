package cn.techen.lbs.db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Meter
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Meter extends MyModel {

	private static final long serialVersionUID = 7790695323805734224L;

	private Integer serialno;
	private Integer pointno;
	private String commaddr;
	private String logicaddr;
	private Integer status;
	private Integer protocol;
	private Integer moduleprotocol;
	private Integer integernumber;
	private Integer decimalnumber;
	private Integer customerclass;
	private Integer customersubclass;
	private Double longitude;
	private Double latitude;
	private Float distance;
	private Float angle;
	private Integer sector;
	private Integer districtX;
	private Integer districtY;
	private Integer signal;
	private Integer relay;
	private Integer grade;
	private Integer parent;
	private String path;
	private Integer pathtype;
	private Integer failTimes;
		
	private Running running = new Running();

	public Integer getSerialno() {
		return serialno;
	}

	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}

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

	public String getLogicaddr() {
		return logicaddr;
	}

	public void setLogicaddr(String logicaddr) {
		this.logicaddr = logicaddr;
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

	public Integer getIntegernumber() {
		return integernumber;
	}

	public void setIntegernumber(Integer integernumber) {
		this.integernumber = integernumber;
	}

	public Integer getDecimalnumber() {
		return decimalnumber;
	}

	public void setDecimalnumber(Integer decimalnumber) {
		this.decimalnumber = decimalnumber;
	}

	public Integer getCustomerclass() {
		return customerclass;
	}

	public void setCustomerclass(Integer customerclass) {
		this.customerclass = customerclass;
	}

	public Integer getCustomersubclass() {
		return customersubclass;
	}

	public void setCustomersubclass(Integer customersubclass) {
		this.customersubclass = customersubclass;
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

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
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

	public Integer getSignal() {
		return signal;
	}

	public void setSignal(Integer signal) {
		this.signal = signal;
	}

	public Integer getRelay() {
		return relay;
	}

	public void setRelay(Integer relay) {
		this.relay = relay;
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

	public Integer getPathtype() {
		return pathtype;
	}

	public void setPathtype(Integer pathtype) {
		this.pathtype = pathtype;
	}

	public Integer getFailTimes() {
		return failTimes;
	}

	public void setFailTimes(Integer failTimes) {
		this.failTimes = failTimes;
	}

	public Running running() {
		return running;
	}

	public class Running implements Serializable {
		private static final long serialVersionUID = 558832645553787804L;

		private String route = "";	
		private Date startTime;
		private Date endTime;
		private int result;
		private Integer relayId;
		private int retryTimes = 0;
		
		public String getRoute() {
			return route;
		}
		
		public void setRoute(String route) {
			this.route = route;
			startTime = new Date();
		}
		
		public Date getStartTime() {
			return startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public int getResult() {
			return result;
		}

		public Integer getRelayId() {
			return relayId;
		}

		public void setRelayId(Integer relayId) {
			this.relayId = relayId;
		}

		public int retryTimes() {
			return retryTimes;
		}

		public void success() {
			endTime = new Date();
			result = 1;
			status = 1;
		}
		
		public void fail() {
			endTime = new Date();
			result = 0;
			retryTimes++;
			if (retryTimes >= 2) {
				status = 0;
			}
		}
		
		public void reset() {
			startTime = new Date();
			endTime = null;
		}
	}
}
