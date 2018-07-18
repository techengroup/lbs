package cn.techen.lbs.db.model;

/**
 * Param
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Param extends MyModel {
	
	private static final long serialVersionUID = 4685341492306959699L;
	
	private String key;
	private String value;
	private String desc;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
