package cn.techen.lbs.channel.rxtx;

import java.net.SocketAddress;

/**
 * A {@link SocketAddress} subclass to wrap the serial port address of a RXTX
 * device (e.g. COM1, /dev/ttyUSB0).
 */
public class RxtxDeviceAddress extends SocketAddress {
	
	private static final long serialVersionUID = 8373028592468463513L;
	
	private final String value;

	/**
     * Creates a RxtxDeviceAddress representing the address of the serial port.
     *
     * @param value the address of the device (e.g. COM1, /dev/ttyUSB0, ...)
     */
    public RxtxDeviceAddress(String value) {
        this.value = value;
    }

    /**
     * @return The serial port address of the device (e.g. COM1, /dev/ttyUSB0, ...)
     */
    public String value() {
        return value;
    }
}
