package cn.techen.lbs.protocol;

public abstract class AbstractData implements Framing {
	
	/**
	 * Bytes
	 */
	protected byte[] bytes;
	
	/**
	 * Bytes Length
	 */
	protected int len;
	
	/**
	 * Format
	 */
	protected String format;
	
	/*
	 * ASC -- 0:DESC, 1:ASC
	 */
	protected int asc;
	
	/**
	 * Table and column -- Table name-Column name
	 */
	protected String tableColumn;

	/**
	 * Content
	 */
	protected Object content;
	
	/**
	 * Desc
	 */
	protected String desc;
		
	public byte[] getByte() {
		return bytes;
	}

	public int getLen() {
		return len;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public String getFormat() {
		return format;
	}

	public int getAsc() {
		return asc;
	}

	public String getTableColumn() {
		return tableColumn;
	}

	public Object getContent() {
		return content;
	}
	
	public String getDesc() {
		return desc;
	}
}
