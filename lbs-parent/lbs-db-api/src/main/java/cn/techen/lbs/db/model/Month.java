package cn.techen.lbs.db.model;

/**
 * Month
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Month extends MyModel {
	
	private static final long serialVersionUID = -1259916310491988243L;

	private String commaddr;
	private String logicaddr;
	private Integer protocol;
	private Integer moduleprotocol;
	private String route;
	private String dataId;//1:active energy0-4,2:negative energy0-4

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
	
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
}
