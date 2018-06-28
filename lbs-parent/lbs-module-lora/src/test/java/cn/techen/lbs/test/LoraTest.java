package cn.techen.lbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.lora.mananger.LoraChannel;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import junit.framework.TestCase;

public class LoraTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Lora");
	
	public void test() {
		String byteStr = "68 11 01 00 02 00 00 00 00 00 00 00 03 17 09 03 40 00 23 05 16 ";
		byteStr = byteStr.replace(" ", "");
		byte[] bytes = ProtocolUtil.hexString2Byte(byteStr);
		try {
			LoraChannel.getInstance(null).channel().write(bytes);
			log.info(ProtocolUtil.byte2HexString(bytes, true));
//			Thread.sleep(60000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
