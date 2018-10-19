package cn.techen.lbs.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ExecutorsTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
//	public void test() {
//		Queue<Integer> queue = new LinkedList<Integer>();
//		
//		for (int i = 0; i < 3; i++) {
//			queue.add(i);
//		}
//		
//		log.info("A:" + queue.poll());
//		
//		queue.add(0);
//		
//		for (Integer i : queue) {
//			log.info("A:" + i);
//		}
//		
//		for (int i = 0; i < 3; i++) {
//			log.info("A:" + queue.poll());
//		}
//	}
	
	private Runnable myRunnableSleep = new Runnable() {
        @Override
        public void run() {
            try {
//                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
	
	public void test() {
//		ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for(int i = 0; i<=20;i++){
//            executorService.execute(myRunnableSleep);
//        }
//        
//        log.info("**************************************");
//        
//        try {
//			Thread.sleep(200000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 5; i++) {
//        	 singleThreadExecutor.execute(new Runnable() {
//     			
//     			@Override
//     			public void run() {
//     				log.info("==The first single thread name:{}", Thread.currentThread().getName());				
//     			}
//     		});
//		}
//       
//        
//        ExecutorService singleThreadExecutor2 = Executors.newSingleThreadExecutor(); 
//        for (int i = 0; i < 5; i++) {
//	        singleThreadExecutor2.execute(new Runnable() {
//				
//				@Override
//				public void run() {
//					log.info("**The second single thread name:{}", Thread.currentThread().getName());				
//				}
//			});
//        }
        
        
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        log.info("==================================");
        
        pool.schedule(myRunnableSleep, 0, TimeUnit.SECONDS);
        
//        pool.execute(myRunnableSleep);
	}

}
