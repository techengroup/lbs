package cn.techen.lbs.protocol;

public interface ProtocolService {
	
	/**
	 * Get protocol code
	 * @return
	 */
	Integer getCode();
	
	/**
	 * Get protocol name
	 * @return
	 */
	String getName();
	
	/**
	 * Get protocol version
	 * @return
	 */
	String getVersion();
	
	/**
	 * Valid
	 * @param data
	 * @return
	 */
	public int valid(byte[] data);
	
	/**
	 * Decode
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public ProtocolConfig decode(byte[] data) throws Exception;
	
	/**
	 * Encode
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public byte[] encode(ProtocolConfig config) throws Exception;
	
}
