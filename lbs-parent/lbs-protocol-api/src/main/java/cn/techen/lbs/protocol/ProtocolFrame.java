package cn.techen.lbs.protocol;

import java.io.Serializable;
import java.util.Date;

public class ProtocolFrame implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -9173997359455055876L;
	
	private String commAddr;
	
	private byte[] writeBytes;
	
	private Date newTime;
	
	private Date wInTime;
	
	private Date wOutTime;
	
	private Date writeTime;
	
	private int writeTimes = 1;
	
	private byte[] readBytes;
	
	private Date readTime;
	
	private Date rInTime;
	
	private Date rOutTime;
	
	private int retryTimes = 0;
	
	private long timeout = 5000;

	public ProtocolFrame() {
		super();
		newTime = new Date();
	}

	public String getCommAddr() {
		return commAddr;
	}

	public void setCommAddr(String commAddr) {
		this.commAddr = commAddr;
	}

	public byte[] getWriteBytes() {
		return writeBytes;
	}

	public void setWriteBytes(byte[] writeBytes) {
		this.writeBytes = writeBytes;
	}

	public Date getNewTime() {
		return newTime;
	}

	public Date getwInTime() {
		return wInTime;
	}

	public void setwInTime(Date wInTime) {
		this.wInTime = wInTime;
	}

	public Date getwOutTime() {
		return wOutTime;
	}

	public void setwOutTime(Date wOutTime) {
		this.wOutTime = wOutTime;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	public int getWriteTimes() {
		return writeTimes;
	}

	public void setWriteTimes(int writeTimes) {
		this.writeTimes = writeTimes;
	}

	public byte[] getReadBytes() {
		return readBytes;
	}

	public void setReadBytes(byte[] readBytes) {
		this.readBytes = readBytes;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Date getrInTime() {
		return rInTime;
	}

	public void setrInTime(Date rInTime) {
		this.rInTime = rInTime;
	}

	public Date getrOutTime() {
		return rOutTime;
	}

	public void setrOutTime(Date rOutTime) {
		this.rOutTime = rOutTime;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void increaseRetryTimes() {
		this.retryTimes++;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		if (timeout > 5000) {
			this.timeout = timeout;
		}
	}
	
	public boolean isTimeout( ) {		
		if (writeTime != null) {
			Date nowTime = new Date();			
			long diff = nowTime.getTime() - writeTime.getTime();
			
			if (diff > timeout) {
				return true;
			}
		} else {
			writeTime = new Date();
		}
		
		return false;
	}

	public void reset() {
		wInTime = null;
		wOutTime = null;
		writeTime = null;
		readTime = null;
		rInTime = null;
		rOutTime = null;
	}
	
}
