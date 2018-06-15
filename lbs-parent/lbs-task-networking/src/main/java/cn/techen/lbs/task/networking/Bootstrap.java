package cn.techen.lbs.task.networking;

import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.task.networking.common.Local;
import cn.techen.lbs.task.networking.common.NetContext;
import cn.techen.lbs.task.networking.manager.AbstractHandler;
import cn.techen.lbs.task.networking.manager.DecodeHandler;
import cn.techen.lbs.task.networking.manager.EncodeHandler;
import cn.techen.lbs.task.networking.manager.ObtainHandler;
import cn.techen.lbs.task.networking.manager.ReadHandler;
import cn.techen.lbs.task.networking.manager.WriteHandler;
import cn.techen.lbs.task.networking.manager.StoreHandler;

public class Bootstrap {
	
	private MBaseService mBaseService;
	
	private NetContext context;

	private AbstractHandler obtain;
	
	private AbstractHandler read;

	public void start() {
		initHandler();
		
		Thread net = new Thread(new NetThread());
		net.start();
	}
	
	private void initHandler() {
		obtain = new ObtainHandler();
		AbstractHandler encode = new EncodeHandler();
		AbstractHandler write = new WriteHandler();
		obtain.setHandler(encode);
		encode.setHandler(write);
		read = new ReadHandler();
		AbstractHandler decode = new DecodeHandler();
		AbstractHandler store = new StoreHandler();
		read.setHandler(decode);
		decode.setHandler(store);
	}

	public void setmBaseService(MBaseService mBaseService) {
		this.mBaseService = mBaseService;
	}

	public void setContext(NetContext context) {
		this.context = context;
	}

	public MBaseService getmBaseService() {
		return mBaseService;
	}

	protected class NetThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					if (mBaseService.loaded()) {
						obtain.operate(context);
						read.operate(context);
					}
				} catch (Exception e) {
					e.printStackTrace();
					context.reset(true);
				}				
			}
		}		
	}
	
}
