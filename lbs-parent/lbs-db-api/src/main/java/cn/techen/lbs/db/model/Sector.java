package cn.techen.lbs.db.model;

import java.io.Serializable;

/**
 * Relay
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Sector implements Serializable {

	private static final long serialVersionUID = 351392984028818120L;
	
	private Integer sector;
	private Integer districtX;
	private Integer total;
	private Integer fail;
	
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getFail() {
		return fail;
	}
	public void setFail(Integer fail) {
		this.fail = fail;
	}
	
}
