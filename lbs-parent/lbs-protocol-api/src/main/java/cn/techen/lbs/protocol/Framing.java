package cn.techen.lbs.protocol;

public interface Framing {
	/**
	 * Decode
	 * @param frame
	 */
	void decode(AbstractFrame frame) throws Exception;

	/**
	 * Encode
	 * @param frame
	 */
	void encode(AbstractFrame frame) throws Exception;
	
}
