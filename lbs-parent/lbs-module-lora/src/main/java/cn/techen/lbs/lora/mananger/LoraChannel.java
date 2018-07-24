package cn.techen.lbs.lora.mananger;

import cn.techen.lbs.channel.rxtx.RxtxChannel;
import cn.techen.lbs.channel.rxtx.RxtxDeviceAddress;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.lora.common.LoraContext;

public class LoraChannel {
	
	private static LoraChannel instance = null;	
	private static RxtxChannel channel = null;
	
	/**
	 * 获取Lora设备（单例）
	 * @return
	 * @throws Exception
	 */
	public static LoraChannel getInstance(LoraContext context) throws Exception {
		if (instance == null || channel == null || !channel.isOpen()) {
			synchronized (LoraChannel.class) {
				if (instance == null || channel == null || !channel.isOpen()) {
					instance = new LoraChannel(context);
				}
			}
		}
		
		return instance;
	}

	private LoraChannel(LoraContext context) throws Exception {
		super();
		channel = new RxtxChannel();
		channel.connect(new RxtxDeviceAddress(Global.RunParams.get("LoraCOM").toString()));		
		channel.handler(new LoraChannelHandler(context));
	}

	/**
	 * 串口通道
	 * @return
	 */
	public RxtxChannel channel() {
		return channel;
	}

}
