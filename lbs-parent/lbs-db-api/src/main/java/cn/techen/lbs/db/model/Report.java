package cn.techen.lbs.db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Report
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Report implements Serializable {

	private static final long serialVersionUID = 2223443567719545055L;
	
	private Integer meterid;
	private Date reporttime;
	private String commaddr;
	private String route;
	private Integer result;
	private Integer signal;
	
	public Integer getMeterid() {
		return meterid;
	}
	
	public void setMeterid(Integer meterid) {
		this.meterid = meterid;
	}
	
	public Date getReporttime() {
		return reporttime;
	}
	
	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
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
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getSignal() {
		return signal;
	}

	public void setSignal(Integer signal) {
		this.signal = signal;
	}

}
