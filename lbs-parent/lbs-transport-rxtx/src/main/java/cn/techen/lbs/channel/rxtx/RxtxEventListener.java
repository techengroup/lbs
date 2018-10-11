package cn.techen.lbs.channel.rxtx;

public interface RxtxEventListener {
		
	void channelRead(RxtxChannel channel, byte[] data) throws Exception;
	
	void channelInactive(RxtxChannel channel) throws Exception;
	
	public void exceptionCaught(RxtxChannel channel, Throwable cause);
}
