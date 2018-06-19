package cn.techen.lbs.db.model;

import java.io.Serializable;

/**
 * Relay
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Column implements Serializable {
	
	private static final long serialVersionUID = -7235575994585468437L;
	
	private String name;
	private String type;
	private Object content;
	private boolean primary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
}
