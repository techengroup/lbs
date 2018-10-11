package cn.techen.lbs.lora.common;

import cn.techen.lbs.channel.rxtx.RxtxChannel;

public class LoraRxtx extends RxtxChannel {
	
	private static LoraRxtx instance = null;
	
	/**
	 * 获取Lora串口设备（单例）
	 * @return
	 * @throws Exception
	 */
	public static LoraRxtx getInstance() throws Exception {
		if (instance == null) {
			synchronized (LoraRxtx.class) {
				if (instance == null) {
					instance = new LoraRxtx();
				}
			}
		}
		
		return instance;
	}

	private LoraRxtx() {
		super();
	}	

}
