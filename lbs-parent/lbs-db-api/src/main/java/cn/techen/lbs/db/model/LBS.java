package cn.techen.lbs.db.model;

/**
 * LBS
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class LBS extends MyModel {
	
	private static final long serialVersionUID = -7600840886253312824L;
	
	private String commaddr;
	private String moduleaddr;
	private String logicaddr;
	private Integer protocol;
	private Double longitude;
	private Double latitude;
	private Integer channel;
	private String ip;
	private Integer port;
	private String ip1;
	private Integer port1;
	
	public String getCommaddr() {
		return commaddr;
	}
	
	public void setCommaddr(String commaddr) {
		this.commaddr = commaddr;
	}
	
	public String getModuleaddr() {
		return moduleaddr;
	}
	
	public void setModuleaddr(String moduleaddr) {
		this.moduleaddr = moduleaddr;
	}
	
	public String getLogicaddr() {
		return logicaddr;
	}

	public void setLogicaddr(String logicaddr) {
		this.logicaddr = logicaddr;
	}
	
	public Integer getProtocol() {
		return protocol;
	}
	
	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
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

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getIp1() {
		return ip1;
	}
	
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}
	
	public Integer getPort1() {
		return port1;
	}
	
	public void setPort1(Integer port1) {
		this.port1 = port1;
	}
	
}
