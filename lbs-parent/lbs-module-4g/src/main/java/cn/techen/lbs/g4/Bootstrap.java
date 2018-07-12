package cn.techen.lbs.g4;

import cn.techen.lbs.db.common.Global;
import cn.techen.lbs.g4.common.G4Context;
import cn.techen.lbs.g4.common.Local;
import cn.techen.lbs.g4.common.Status;
import cn.techen.lbs.g4.manager.Client;
import cn.techen.lbs.g4.manager.WriteHandler;

public class Bootstrap {	
	
	private G4Context context;
	private Client client;
	private WriteHandler writeHandler;
	
	public void start() {
		client = new Client(context);
		writeHandler = new WriteHandler();
		
		Thread clientThread = new Thread(new ClientThread());
		clientThread.start();
		
		Thread send = new Thread(new SendThread());
		send.start();
	}

	public void setContext(G4Context context) {
		this.context = context;
	}
	
	protected class ClientThread implements Runnable {
		private String host;
		private Integer port;
		private String addr;
		private String host0;
		private Integer port0;
		private String host1;
		private Integer port1;
		private int master = 0; //only 0 or 1
		private int fail = 0;
		private boolean isSwap = false;
		
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.CONNECTINTERVALMILLIS);		
					
					if (Global.DATAReady) {
						disconnect();
						connect();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}
		
		private void connect() {
			if (context.getStatus() == Status.DISCONNECT) {
				if (isSwap) {
					host = host0;
					port = port0;
					fail = 0;
					master = 0;
				} else {
					fail++;
					
					if (fail >= 5) {						
						if (host1 != null && port1 != null) master = master^1;
						
						if (master == 0) {
							host = host0;
							port = port0;
						} else {
							host = host1;
							port = port1;
						}
						
						fail = 0;
					}
				}				
				
				client.connect(host, port);
			}
		}
		
		private void disconnect() {	
			String newAddr = Global.lbs.getCommaddr();
			String newHost0 = Global.lbs.getIp();
			Integer newPort0 = Global.lbs.getPort();
			String newHost1 = Global.lbs.getIp1();
			Integer newPort1 = Global.lbs.getPort1();
			
			if (addr == null || !newAddr.equals(addr) || !newHost0.equals(host0) || !newPort0.equals(port0)) {	
				addr = newAddr;
				host0 = newHost0;
				port0 = newPort0;
				isSwap = true;
				if (context.getStatus() == Status.CONNECT) client.disconnect();
			} else {
				isSwap = false;
			}
			if (newHost1 != null && newPort1 != null) {
				host1 = newHost1;
				port1 = newPort1;
			}
		}
	}

	protected class SendThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					writeHandler.operate(context);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
}
