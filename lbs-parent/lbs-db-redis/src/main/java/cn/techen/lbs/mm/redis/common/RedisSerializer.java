package cn.techen.lbs.mm.redis.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RedisSerializer
 * @author ZY
 * @version V1.0 New Date 2014-08-19
 */
public final class RedisSerializer {
	private static final Logger logger = (Logger) LoggerFactory
			.getLogger(RedisSerializer.class.getName());
	
	private static final byte[] EMPTY_ARRAY = new byte[0];
	
	/**
	 * Converts an Object to a byte array.
	 * 
	 * @param object, the Object to serialize.
	 * @return, the byte array that stores the serialized object.
	 */
	public static byte[] serialize(Object object) {
		if (object == null)	return EMPTY_ARRAY;
		ByteArrayOutputStream byteOut = null;
		ObjectOutputStream ObjOut = null;
		try {
			byteOut = new ByteArrayOutputStream();
			ObjOut = new ObjectOutputStream(byteOut);
			ObjOut.writeObject(object);
			ObjOut.flush();
		} catch (IOException e) {
			logger.error(object.getClass().getName() + " serialized error !", e);
		} finally {
			try {
				if (null != ObjOut) {
					ObjOut.close();
				}
			} catch (IOException e) {
				ObjOut = null;
			}
		}
		return byteOut.toByteArray();
	}
	
	/**
	 * Converts a byte array to an Object.
	 * 
	 * @param byteArray, a byte array that represents a serialized Object.
	 * @return, an instance of the Object class.
	 */
	public static Object deserialize(byte[] bytes) {
		if (isEmpty(bytes)) return null;
		ObjectInputStream ObjIn = null;
		Object retVal = null;
		try {
			ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
			ObjIn = new ObjectInputStream(byteIn);
			retVal = ObjIn.readObject();
		} catch (Exception e) {
			logger.error("deserialized error  !", e);
		} finally {
			try {
				if (null != ObjIn) {
					ObjIn.close();
				}
			} catch (IOException e) {
				ObjIn = null;
			}
		}
		return retVal;
	}
	
	/**
	 * Byte array is empty
	 * @param data
	 * @return
	 */
	private static boolean isEmpty(byte[] bytes) {
		return (bytes == null) || (bytes.length == 0);
	}
	
}
