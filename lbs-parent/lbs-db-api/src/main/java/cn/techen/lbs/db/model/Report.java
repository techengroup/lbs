package cn.techen.lbs.db.model;

import java.io.Serializable;

/**
 * Report
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Report implements Serializable {

	private static final long serialVersionUID = 2223443567719545055L;
	
	private Integer meterid;
	private String commaddr;
	private String route;
	private Integer signal;
	private String content;
	private Integer result;
	
	public Integer getMeterid() {
		return meterid;
	}
	
	public void setMeterid(Integer meterid) {
		this.meterid = meterid;
	}
	
	public String getCommaddr() {
		return commaddr;
	}
	
	public void setCommaddr(String commaddr) {
		this.commaddr = commaddr;
	}
	
	public String getRoute() {
		return route;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}

	public Integer getSignal() {
		return signal;
	}

	public void setSignal(Integer signal) {
		this.signal = signal;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}

}
