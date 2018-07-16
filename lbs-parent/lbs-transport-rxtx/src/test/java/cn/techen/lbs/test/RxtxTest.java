package cn.techen.lbs.test;

import junit.framework.TestCase;

public class RxtxTest extends TestCase {
	
	public void testGetMeter() {
//		RxtxChannel channel = new RxtxChannel();		
//		channel.config().setBaudrate(9600);
//		
//		try {
//			channel.connect(new RxtxDeviceAddress("COM3"));
//			String byteStr = "68 11 01 00 02 00 00 00 00 00 00 00 03 17 09 03 40 00 23 05 16 ";
//			byteStr = byteStr.replace(" ", "");
//			channel.write(hexString2Byte(byteStr));
//			byte[] bytes = channel.read();
//			if (bytes != null) {
//				System.out.println("Get data:" + bytes.length);
//			}
//			channel.disconnect();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("success.");
		hexString2Byte("");
	}
	
	private byte[] hexString2Byte(String val) {
		int len = val.length()/2;
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			bytes[i] = (byte)hexString2Int(val.substring(i*2, (i+1)*2));
		}
		return bytes;
	}
	
	private int hexString2Int(String val) {
		return Integer.parseInt(val, 16);
	}

}
