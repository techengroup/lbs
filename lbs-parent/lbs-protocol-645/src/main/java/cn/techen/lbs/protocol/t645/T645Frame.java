package cn.techen.lbs.protocol.t645;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t645.common.Local;

public class T645Frame extends AbstractFrame {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);

	/**
	 * Frame Header
	 */
	private Header head = new Header();

	/**
	 * Device Address
	 */
	private Address addrField = new Address();

	/**
	 * Control Code
	 */
	private ControlField controlField = new ControlField();

	/**
	 * Data Length
	 */
	private DataLen dataLen = new DataLen();

	/**
	 * Data Field
	 */
	private DataField dataField = new DataField();

	/**
	 * Check Code
	 */
	private CS cs = new CS();

	/**
	 * Tailer
	 */
	private Tailer tail = new Tailer();

	public T645Frame() {
		super();
		this.config = new DefaultT645Config();
	}

	@Override
	public void decode() throws Exception {
		if (bytes.length >= 9) {			
			process().byte2Queue();

			head.decode(this);
			addrField.decode(this);
			head.decode(this);
			controlField.decode(this);
			dataLen.decode(this);
			minus33H();
			dataField.decode(this);
			cs.decode(this);
			tail.decode(this);
			
			logger.debug(toExplain());
		} else {
			throw new RuntimeException("Frame size is not enough.");
		}
	}

	@Override
	public void encode() throws Exception {
		dataField.encode(this);
		plus33H();
		dataLen.encode(this);
		controlField.encode(this);
		head.encode(this);
		addrField.encode(this);
		head.encode(this);
		cs.encode(this);
		tail.encode(this);
		
		process.vector2Byte();
		
		logger.debug(toExplain());
	}

	@Override
	public String toExplain() {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n%s [%d]B [%s]H\r\n");
		sb.append("%s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%2s%-6s:%3s: %s\r\n");
		sb.append("%s\r\n");
		return String.format(sb.toString(), "T645Frame", bytes.length, ProtocolUtil.byte2HexString(bytes, true)
				, "{"
				, "", head.getTitle(), head.getLen(), head.toExplain()
				, "", addrField.getTitle(), addrField.getLen(), addrField.toExplain()
				, "", head.getTitle(), head.getLen(), head.toExplain()
				, "", controlField.getTitle(), controlField.getLen(), controlField.toExplain()
				, "", dataLen.getTitle(), dataLen.getLen(), dataLen.toExplain()
				, "", dataField.getTitle(), dataField.getLen(), dataField.toExplain()
				, "", cs.getTitle(), cs.getLen(), cs.toExplain()
				, "", tail.getTitle(), tail.getLen(), tail.toExplain()
				, "}");
	}
	
	private void minus33H() {
		int size = process.queue.size();
		if (size > 2) {
			byte[] bs = new byte[size];
			for (int i = 0; i < size; i++) {
				if (i < (size - 2)) {
					bs[i] = (byte) (process.queue.poll() - 0x33);
				} else {
					bs[i] = process.queue.poll();
				}
			}
			
			for (byte b : bs) {
				process.queue.add(b);
			}
		}
	}
	
	private void plus33H() {
		int size = process.vector.size();
		if (size > 0) {
			byte[] bs = new byte[size];
			for (int i = 0; i < size; i++) {
				bs[i] = (byte) (process.vector.get(i) + 0x33);
			}
			
			process.vector.clear();			
			for (byte b : bs) {
				process.vector.add(b);
			}			
		}
		
	}
}
