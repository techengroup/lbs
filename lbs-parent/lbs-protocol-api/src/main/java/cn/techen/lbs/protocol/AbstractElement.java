package cn.techen.lbs.protocol;

public abstract class AbstractElement implements Framing, IExplain {

	/**
	 * Value
	 */
	protected int value;
	
	/**
	 * Bytes Length
	 */
	protected int len;
	
	/**
	 * Title
	 */
	protected String title;
	
	/**
	 * Description
	 */
	protected String desc = "";
	
	public int getValue() {
		return value;
	}

	public int getLen() {
		return len;
	}

	public String getTitle() {
		return title;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toExplain() {	
		return String.format("[%s]H  %s", ProtocolUtil.int2HexString(value), desc);
	}

}
