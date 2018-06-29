package cn.techen.lbs.db.model;

/**
 * Fn
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Fn extends MyModel {
	
	private static final long serialVersionUID = -4582003931515408109L;
	
	private Integer protocol;
	private Integer direction;
	private String operation;
	private String function;
	private String name;
	private String titles;
	private String elements;
	
	public Integer getProtocol() {
		return protocol;
	}
	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}	
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
	}
}
