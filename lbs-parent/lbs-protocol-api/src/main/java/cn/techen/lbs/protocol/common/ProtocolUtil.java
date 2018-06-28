package cn.techen.lbs.protocol.common;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import cn.techen.lbs.protocol.AbstractData;

public final class ProtocolUtil {
	
	/**
	 * New Data
	 * @param dataClass
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static AbstractData newData(String dataClass, String types) throws Exception {				
		Class<?> c = Class.forName(String.format("cn.techen.lbs.protocol.type.%s", dataClass));
		Constructor<?> constructor = c.getConstructor(String.class);
		return (AbstractData) constructor.newInstance(types);
	}
	
//	public static AbstractData newData(String typeString) throws Exception {	
//		String[] types = typeString.split(",");
//		
//		Class<?> c = Class.forName(String.format("cn.techen.lbs.protocol.type.%s", types[0]));
//        Constructor<?> constructor = c.getConstructor(Integer.class, String.class, Integer.class, String.class);
//        if (types.length <= 2) {            
//    		return (AbstractData) constructor.newInstance(Integer.parseInt(types[1]), types[2], 0, "");			
//		} else {	        
//			return (AbstractData) constructor.newInstance(Integer.parseInt(types[1]), types[2], Integer.parseInt(types[3]), types[4]);
//		}
//	}
	
	/**
	 * New Data
	 * @param dataClass
	 * @param length
	 * @param format
	 * @param asc
	 * @param tableColumn
	 * @return
	 * @throws Exception
	 */
	public static AbstractData newData(String dataClass, Integer length, String format, Integer sort, String name) throws Exception {				
		Class<?> c = Class.forName(String.format("cn.techen.lbs.protocol.type.%s", dataClass));
        Constructor<?> constructor = c.getConstructor(Integer.class, String.class, Integer.class, String.class);
		return (AbstractData) constructor.newInstance(length, format, sort, name);
	}
	
	/**
	 * String Array to String
	 * @param array
	 * @param offset
	 * @return
	 */
	public static String StringArray2String(String[] array, String separator, int offset) {
		StringBuffer sb = new StringBuffer();

		for(int i = offset; i < array.length; i++){
			if (i == (array.length - 1)) {
				sb. append(array[i]);
			} else {
				sb. append(array[i] + separator);
			}
			
		} 
		return sb.toString();
	}
	
	/**
	 * Format
	 * @param format
	 * @param val
	 */
	public static String zeroFill(int num, int val) {
		return String.format("%0" + num + "d", val);
	}
	
	/**
	 * Format
	 * @param format
	 * @param val
	 */
	public static String zeroFill(int num, String val) {
		int len = val.length();
		if (len < num) {
			for (int i = 0; i < (num - len); i++) {
				val = "0" + val;
			}
		}
		
		return val;
	}
	
	/**
	 * PadLeft
	 * @param str
	 * @param num
	 * @param c
	 * @return
	 */
	public static String padLeft(String str, char r, int num) {
		int len = str.length();
		if (len < num) {
			for (int i = 0; i < (num - len); i++) {
				str = r + str;
			}
		}
		
		return str;
	}
	
	/**
	 * Byte to INT
	 * @param b
	 * @return
	 */
	public static int byte2Int(byte b) {
		return b & 0xFF;
	}
	
	/**
	 * INT to Byte
	 * @param b
	 * @return
	 */
	public static byte int2Byte(int b) {
		return (byte) (b & 0xFF);
	}
	
	/**
	 * Byte to INT
	 * @param b
	 * @return
	 */
	public static int byte2Int(byte[] b) {
		int val = 0;
		for (int i = 0; i < b.length; i++) {
			val = (val << 8) + (b[i] & 0xFF);
		}
		return val;
	}	
	
	/**
	 * Hex String to Int
	 * @param str
	 * @return
	 */
	public static int hexString2Int(String val) {
		return Integer.parseInt(val, 16);
	}
	
	/**
	 * Int to Hex String
	 * @param str
	 * @return
	 */
	public static String int2HexString(int val) {
		String str = Integer.toHexString(val);
		if (str.length() == 1) {
			str = 0 + str;
		}
		return str;
	}
	
	/**
	 * BCD to Decimal String
	 * @param b
	 * @return
	 */
	public static String bcd2Str(byte b) {
		StringBuffer temp = new StringBuffer(2);
		temp.append((byte) ((b & 0xf0) >> 4));
		temp.append((byte) (b & 0x0f));
		return temp.toString();
		//return String.format("%02d", Integer.parseInt(temp.toString()));
	}

	/**
	 * BCD to String
	 * @param bytes
	 * @return
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString();
	}
	
	/**
	 * Byte to Hex String
	 * @param b
	 * @return
	 */
	public static String byte2HexString(byte b) {
		return zeroFill(2, Integer.toHexString(b & 0xFF));
	}
	
	/**
	 * Byte Array to String
	 * @param bytes
	 * @return
	 */
	public static String byte2HexString(byte[] bytes, boolean isblank) {
		if (bytes == null) return "";
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append(byte2HexString(bytes[i]));
			if (isblank && i < (bytes.length-1) ) {
				temp.append(" ");
			}
		}
		return temp.toString();
	}
	
	/**
	 * Byte to Binary
	 * @param b
	 * @return
	 */
	public static String byte2BinaryString(byte b) {
		return zeroFill(8, Integer.toBinaryString(b));
	}
	/**
	 * INT to Binary
	 * @param i
	 * @return
	 */
	public static String int2BinaryString(int i, boolean isblank) {		
		StringBuffer temp = new StringBuffer();
		String bStr = Integer.toBinaryString(i);
		int len = (int) Math.ceil((double)bStr.length()/8);
		for (int j = 0; j < len; j++) {
			String str = zeroFill(8*len, bStr);
			
			temp.append(str.substring(j*4, (j+1)*4));
			if (isblank) {
				temp.append(" ");
			}
			temp.append(str.substring((j+1)*4, (j+2)*4));
		}		
		
		return temp.toString();
	} 
	
	/**
	 * Byte Array to Binary
	 * @param bytes
	 * @param isblank
	 * @return
	 */
	public static String byte2BinaryString(byte[] bytes, boolean isblank) {	
		if (bytes == null) return "";
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String str = byte2BinaryString(bytes[i]);			
			temp.append(str.substring(0, 4));
			if (isblank) {
				temp.append(" ");
			}
			temp.append(str.substring(4));
			if (isblank && i < (bytes.length-1) ) {
				temp.append(" ");
			}
		}
		return temp.toString();
	}
	
	/**
	 * String to Byte Array
	 * @param val
	 * @return
	 */
	public static byte[] hexString2Byte(String val) {
		int len = val.length()/2;
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			bytes[i] = (byte)hexString2Int(val.substring(i*2, (i+1)*2));
		}
		return bytes;
	}

	/**
	 * String to BCD
	 * @param asc
	 * @return
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}	
	
	/**
	 * byte[] to List<Byte>
	 * @param list
	 * @return
	 */
	public static List<Byte> byte2List(byte[] data) {
		List<Byte> list = new ArrayList<Byte>();
		for (byte d : data) {
			list.add(d);
		}
		return list;
	}
	
	/**
	 * List<Byte> to byte[]
	 * @param list
	 * @return
	 */
	public static byte[] list2byte(List<Byte> list) {
		byte[] bytes = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			bytes[i] = list.get(i);
		}
		return bytes;
	}
}
