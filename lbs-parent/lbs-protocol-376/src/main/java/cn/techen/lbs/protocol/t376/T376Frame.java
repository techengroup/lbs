package cn.techen.lbs.protocol.t376;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.common.ProtocolUtil;
import cn.techen.lbs.protocol.t376.common.Local;

public class T376Frame extends AbstractFrame {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);

	/**
	 * Frame Header
	 */
	private Header head = new Header();

	/**
	 * Data Length
	 */
	private DataLen dataLen = new DataLen();

	/**
	 * Control Code
	 */
	private ControlField ctrlField = new ControlField();

	/**
	 * Device Address
	 */
	private AddressField addrField = new AddressField();

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

	public T376Frame() {
		super();
		this.config = new DefaultT376Config();
	}

	@Override
	public void decode() throws Exception {
		if (bytes.length >= 9) {
			process().byte2Queue();

			head.decode(this);
			dataLen.decode(this);			
			head.decode(this);
			ctrlField.decode(this);
			addrField.decode(this);
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
		addrField.encode(this);
		ctrlField.encode(this);
		head.encode(this);
		dataLen.encode(this);		
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
		return String.format(sb.toString(), "T376Frame", bytes.length, ProtocolUtil.byte2HexString(bytes, true)
				, "{"
				, "", head.getTitle(), head.getLen(), head.toExplain()
				, "", dataLen.getTitle(), dataLen.getLen(), dataLen.toExplain()
				, "", head.getTitle(), head.getLen(), head.toExplain()
				, "", ctrlField.getTitle(), ctrlField.getLen(), ctrlField.toExplain()
				, "", addrField.getTitle(), addrField.getLen(), addrField.toExplain()
				, "", dataField.getTitle(), dataField.getLen(), dataField.toExplain()
				, "", cs.getTitle(), cs.getLen(), cs.toExplain()
				, "", tail.getTitle(), tail.getLen(), tail.toExplain()
				, "}");
	}

}
