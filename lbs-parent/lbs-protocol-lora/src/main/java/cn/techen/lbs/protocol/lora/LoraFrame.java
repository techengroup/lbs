package cn.techen.lbs.protocol.lora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.protocol.AbstractFrame;
import cn.techen.lbs.protocol.ProtocolUtil;

public class LoraFrame extends AbstractFrame {
	private static final Logger logger = (Logger) LoggerFactory  
            .getLogger("Lora-Protocol");
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
	private ControlCode ctrlCode = new ControlCode();

	/**
	 * Information Field
	 */
	private InfoField infoField = new InfoField();

	/**
	 * Signal Strength
	 */
	private RSSI rssi = new RSSI();

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

	public LoraFrame() {
		super();
		this.config = new DefaultLoraConfig();
	}

	@Override
	public void decode() throws Exception {
		if (bytes.length >= 9) {
			process().byte2Queue();

			head.decode(this);
			dataLen.decode(this);
			ctrlCode.decode(this);
			infoField.decode(this);
			rssi.decode(this);
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
		rssi.encode(this);
		infoField.encode(this);
		ctrlCode.encode(this);
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
		sb.append("\r\n%s\r\n");
		sb.append("%11s%-6s:%3s: [%s]H\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s%-6s:%3s: %s\r\n");
		sb.append("%11s\r\n");
		return String.format(sb.toString(), "LoraFrame {"
				, "", "Frame", String.valueOf(bytes.length), ProtocolUtil.byte2HexString(bytes, true)
				, "", head.getTitle(), head.getLen(), head.toExplain()
				, "", dataLen.getTitle(), dataLen.getLen(), dataLen.toExplain()
				, "", ctrlCode.getTitle(), ctrlCode.getLen(), ctrlCode.toExplain()
				, "", infoField.getTitle(), infoField.getLen(), infoField.toExplain()
				, "", rssi.getTitle(), rssi.getLen(), rssi.toExplain()
				, "", addrField.getTitle(), addrField.getLen(), addrField.toExplain()
				, "", dataField.getTitle(), dataField.getLen(), dataField.toExplain()
				, "", cs.getTitle(), cs.getLen(), cs.toExplain()
				, "", tail.getTitle(), tail.getLen(), tail.toExplain()
				, "}");
	}

}
