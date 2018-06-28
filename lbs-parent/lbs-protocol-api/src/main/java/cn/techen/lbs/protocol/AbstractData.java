package cn.techen.lbs.protocol;

public abstract class AbstractData extends AbstractType implements Framing, IExplain {
	
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
	 * Sort -- 0:DESC, 1:ASC
	 */
	protected int sort;
	
	/**
	 * Name
	 */
	protected String name;

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

	public int getSort() {
		return sort;
	}

	public String getName() {
		return name;
	}

	public Object getContent() {
		return content;
	}
	
	public String getDesc() {
		return desc;
	}
	
	@Override
	public String toExplain() {
		return String.format("| %s |", desc);
	}
}
