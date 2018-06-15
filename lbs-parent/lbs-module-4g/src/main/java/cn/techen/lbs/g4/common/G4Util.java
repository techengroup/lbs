package cn.techen.lbs.g4.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class G4Util {	
	/**
	 * ByteBuf to byte array
	 * @param msg
	 * @return
	 */
	public static byte[] bufToByte(ByteBuf buf) {
		byte[] data = null;		
		
		int readableBytes = buf.readableBytes();
		if(buf.hasArray() && buf.arrayOffset() == 0 && readableBytes == buf.capacity()) {
			data = buf.array();
		} else {
			data = new byte[readableBytes];
			buf.getBytes(0, data, 0, readableBytes);
		}

		return data;
	}
	
	/**
	 * Byte[] to ByteBuf
	 * @param data
	 * @return
	 */
	public static ByteBuf byteToBuf(byte[] data) {
		ByteBuf buf = null;
		
		if(data != null && data.length > 0) {
			int len = data.length;
			buf = Unpooled.buffer(len);
			buf.writeBytes(data);
		}		
		
		return buf;
	}
	
}
