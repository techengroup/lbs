package cn.techen.lbs.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.DefaultProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig;
import cn.techen.lbs.protocol.ProtocolConfig.DIR;
import cn.techen.lbs.protocol.ProtocolConfig.OPERATION;
import cn.techen.lbs.protocol.common.Elements;
import cn.techen.lbs.protocol.impl.T645Proxy;
import junit.framework.TestCase;

public class T645ProtocolTest extends TestCase {
	private static Logger log = (Logger) LoggerFactory.getLogger("Lora-Protocol");

	static {		
		Elements.getInstace().put("9110:1", "OCT_STRING,4,xxxxxx.xx,1");
		Elements.getInstace().put("9111:1", "OCT_STRING,4,xxxxxx.xx,1");
		Elements.getInstace().put("9112:1", "OCT_STRING,4,xxxxxx.xx,1");
		Elements.getInstace().put("9113:1", "OCT_STRING,4,xxxxxx.xx,1");
		Elements.getInstace().put("9114:1", "OCT_STRING,4,xxxxxx.xx,1");
		Elements.getInstace().put("911F:1", "OCT_STRING,4,xxxxxx.xx,1"
				+ ":OCT_STRING,4,xxxxxx.xx,1"
				+ ":OCT_STRING,4,xxxxxx.xx,1"
				+ ":OCT_STRING,4,xxxxxx.xx,1"
				+ ":OCT_STRING,4,xxxxxx.xx,1");
		Elements.getInstace().put("C429:1", "BIT_STRING,4,XXXXXXXX,1");
	}
	
	public void test() {
		ProtocolConfig config = new DefaultProtocolConfig();
		config.setCommAddr("00000003").setDir(DIR.CLIENT).setOperation(OPERATION.GET);
		config.funcs().add("9110");
		
		T645Proxy t654Proxy = new T645Proxy();
		try {
			log.info("Encode frame.");
			byte[] frame = t654Proxy.encode(config);
			log.info("Decode frame.");
			t654Proxy.decode(frame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyMMddHHmmss", Locale.ENGLISH);
		try {
			Date time = sDateFormat.parse("180720115311");
			log.info(time.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
