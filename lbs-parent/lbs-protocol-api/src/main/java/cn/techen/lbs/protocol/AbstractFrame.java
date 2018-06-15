package cn.techen.lbs.protocol;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public abstract class AbstractFrame implements IExplain {
		
	/**
	 * Frame Config
	 */
	protected FrameConfig config;
	
	/**
	 * Frame Process
	 */
	protected final FrameProcess process = new FrameProcess();
	
	/**
	 * Frame Bytes
	 */
	protected byte[] bytes;

	/**
	 * Frame Config
	 * @return
	 */
	public FrameConfig config() {
		return config;
	}
	
	/**
	 * Frame Process
	 * @return
	 */
	public FrameProcess process() {
		return process;
	}
	
	/**
	 * Get Frame Bytes
	 * @return
	 */
	public byte[] getBytes() {
		return bytes;
	}	
	
	/**
	 * Set Frame Bytes
	 * @param datas
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	/**
	 * Decode
	 * @throws Exception
	 */
	public abstract void decode() throws Exception;

	/**
	 * Encode
	 * @throws Exception
	 */
	public abstract void encode() throws Exception;
	
	/**
	 * Frame Process
	 * @author ZY
	 */
	public class FrameProcess {

		public final Queue<Byte> queue = new LinkedList<Byte>(); 

		public final Vector<Byte> vector = new Vector<Byte>();

		/**
		 * Byte to Queue -- Decode
		 */
		public void byte2Queue() {
			for (byte b : bytes) {
				this.queue.add(b);
			}
		}
		
		/**
		 * Vector to Byte -- Encode
		 */
		public void vector2Byte() {
			bytes = new byte[vector.size()];
			for (int i = 0; i < vector.size(); i++) {
				bytes[i] = vector.get(i);
			}
		}
	}
	
}
