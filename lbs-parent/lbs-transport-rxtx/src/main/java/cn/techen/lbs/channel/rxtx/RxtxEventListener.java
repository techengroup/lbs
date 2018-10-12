package cn.techen.lbs.channel.rxtx;

public interface RxtxEventListener {
		
	void channelActive(RxtxChannel channel) throws Exception;
	
	void channelRead(RxtxChannel channel, byte[] data) throws Exception;
	
	void channelInactive(RxtxChannel channel) throws Exception;
	
	void exceptionCaught(RxtxChannel channel, Throwable cause);
}
