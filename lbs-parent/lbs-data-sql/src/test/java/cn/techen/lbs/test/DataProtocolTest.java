package cn.techen.lbs.test;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class DataProtocolTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Data-Protocol");
	
	public void testA() {
		 Queue<Object> datas = new LinkedList<Object>();
		 datas.add(1);
		 datas.add(2);
		 datas.add(3);
		 datas.add(4);
		 datas.add(5);
		 
		 
		 String string = String.format("%s,%s", datas.poll(), datas.poll());
		 
		
		 
		 log.info("format：{} : Size {}", string, datas.size());
		 
		 
		 
	}
}
