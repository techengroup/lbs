package cn.techen.lbs.protocol;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractType {
	
	/**
	 * Data Type String
	 */
	protected String dataTypes;
	
	/**
	 * Byte List
	 */
	protected List<Byte> byteList = new ArrayList<Byte>();

	/**
	 * Get Data Type String
	 * @return
	 */
	public String getDataTypes() {
		return dataTypes;
	}
	
	/**
	 * Get Byte List
	 * @return
	 */
	public List<Byte> getByteList() {
		return byteList;
	}
	
	/**
	 * Extract Data Type String
	 * @param types
	 * @return
	 */
	protected String extract(String types) {
		int pos = types.indexOf(",");
		if (pos >= 0) {
			dataTypes = types.substring(pos+1);
			return types.substring(0, pos);
		} else {
			if (types.length() > 0) {
				dataTypes = "";
				return types;
			}
		}
		
		return null;
	}
	
}
