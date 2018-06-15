package cn.techen.lbs.task.report;

import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.task.report.common.Local;
import cn.techen.lbs.task.report.common.ReportContext;
import cn.techen.lbs.task.report.manager.AbstractHandler;
import cn.techen.lbs.task.report.manager.ObtainHandler;
import cn.techen.lbs.task.report.manager.ReadHandler;
import cn.techen.lbs.task.report.manager.ReportHandler;
import cn.techen.lbs.task.report.manager.SuspendHandler;

public class Bootstrap {
	
	private MBaseService mBaseService;
	
	private ReportContext context;
	
	private AbstractHandler report;
	
	private AbstractHandler obtain;
	
	private AbstractHandler read;
	
	private AbstractHandler suspend;

	public void start() {
		initHandler();
		
		Thread reportT = new Thread(new ReportThread());
		reportT.start();
		
		Thread event = new Thread(new EventThread());
		event.start();
		
		Thread suspendT = new Thread(new SuspendThread());
		suspendT.start();
	}
	
	private void initHandler() {
		report = new ReportHandler();
		obtain = new ObtainHandler();
		read = new ReadHandler();
		suspend = new SuspendHandler();
	}

	public void setmBaseService(MBaseService mBaseService) {
		this.mBaseService = mBaseService;
	}

	public void setContext(ReportContext context) {
		this.context = context;
	}

	public MBaseService getmBaseService() {
		return mBaseService;
	}

	protected class ReportThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					report.operate(context);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
	
	protected class EventThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.INTERVALMILLIS);
					
					obtain.operate(context);
					read.operate(context);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
	
	protected class SuspendThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(Local.SUSPENDMILLIS);
					
					suspend.operate(context);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}		
	}
	
}
