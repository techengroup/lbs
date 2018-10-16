package cn.techen.lbs.lora;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.channel.rxtx.RxtxChannel;
import cn.techen.lbs.channel.rxtx.RxtxDeviceAddress;
import cn.techen.lbs.channel.rxtx.RxtxEventListener;
import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.lora.common.Local;
import cn.techen.lbs.lora.common.LoraContext;
import cn.techen.lbs.lora.common.LoraRxtx;
import cn.techen.lbs.lora.mananger.AbstractHandler;
import cn.techen.lbs.lora.mananger.ObtainHandler;
import cn.techen.lbs.lora.mananger.ReadHandler;
import cn.techen.lbs.lora.mananger.WriteHandler;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.lora.mananger.StoreHandler;

public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private LoraContext context;
	
	private AbstractHandler readHandler;
	
	private AbstractHandler storeHandler;
	
	private AbstractHandler obtainHandler;
	
	public void start() {
		initHandler();
		
		logger.info("Lora module init is starting......");
		Thread init = new Thread(new InitThread());
		init.start();
		
		logger.info("Lora module obtain is starting......");
		Thread obtain = new Thread(new ObtainThread());
		obtain.start();
		
		logger.info("Lora module read is starting......");
		Thread read = new Thread(new ReadThread());
		read.start();
	}
	
	private void initHandler() {		
		readHandler = new ReadHandler();
		storeHandler = new StoreHandler();
		readHandler.setHandler(storeHandler);
		
		obtainHandler = new ObtainHandler();		
		AbstractHandler writeHandler = new WriteHandler();
		obtainHandler.setHandler(writeHandler);
	}

	public void setContext(LoraContext context) {
		this.context = context;
	}
	
	protected class InitThread implements Runnable {
		
		@Override
		public void run() {
			while (true) {				
				try {
					if (!Global.LoraReady) {
						try {
							LoraRxtx loraRxtx = LoraRxtx.getInstance();
							loraRxtx.connect(new RxtxDeviceAddress(Global.RunParams.get("LoraCOM").toString()));
							loraRxtx.addEventListener(new RxtxEventListener() {
								@Override
								public void channelActive(RxtxChannel channel) throws Exception {
									logger.info("Connected Lora serial port:{}.", channel.remoteAddress());
									Global.LoraReady = true;
								}
								
								@Override
								public void channelRead(RxtxChannel channel, byte[] data) throws Exception {
									logger.debug(ProtocolUtil.byte2HexString(data, true));
									context.byteBuffer.addAll(ProtocolUtil.byte2List(data));
								}
								
								@Override
								public void channelInactive(RxtxChannel channel) throws Exception {
									logger.info("Disconnected lora serial port:{}.", channel.remoteAddress());
									channel.disconnect();
									Global.LoraReady = false;
								}
								
								@Override
								public void exceptionCaught(RxtxChannel channel, Throwable cause) {
									logger.error(Global.getStackTrace(cause));
									channel.disconnect();
									Global.LoraReady = false;
								}
							});
							
							Global.LoraReady = true;
						} catch (Exception e) {
							logger.error(Global.getStackTrace(e));
							Global.LoraReady = false;
						}
					}
					
					Thread.sleep(Local.DETECTMILLIS);
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
	}

	protected class ObtainThread implements Runnable {
		private Date lastTime = new Date();

		@Override
		public void run() {
			while (true) {
				try {
					long worktime = new Date().getTime() - lastTime.getTime();
					
					if (worktime < Local.WORKMILLIS) {
						Thread.sleep(Local.INTERVALMILLIS);						
					} else {
						Thread.sleep(Local.RESTMILLIS);						
						lastTime = new Date();
					}
					
					if (Global.LoraReady) {
						obtainHandler.operate(context);			
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));
				}				
			}
		}		
	}
	
	protected class ReadThread implements Runnable {
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (Global.LoraReady) {
						try {
							readHandler.operate(context);
						} catch (Exception e) {
							logger.error(Global.getStackTrace(e));
						}			
					} 
					
					if (context.getFrame() != null) {
						if (context.getFrame().isTimeout()) {
							storeHandler.operate(context);
						}	
					}
				} catch (Exception e) {
					logger.error(Global.getStackTrace(e));					
				}				
			}
		}		
	}

}
