package cn.techen.lbs.test;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class ReportTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");
	
	public void test() {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for (int i = 0; i < 3; i++) {
			queue.add(i);
		}
		
		log.info("A:" + queue.poll());
		
		queue.add(0);
		
		for (Integer i : queue) {
			log.info("A:" + i);
		}
		
		for (int i = 0; i < 3; i++) {
			log.info("A:" + queue.poll());
		}
	}

}
