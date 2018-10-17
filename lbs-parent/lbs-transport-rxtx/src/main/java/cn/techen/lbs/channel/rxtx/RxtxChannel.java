package cn.techen.lbs.channel.rxtx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketAddress;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class RxtxChannel {

	private final RxtxChannelConfig config;
	private RxtxDeviceAddress deviceAddress;
	private SerialPort serialPort;

	public RxtxChannel() {
		super();
		config = new DefaultRxtxChannelConfig();
	}

	/**
	 * Rxtx配置
	 * @return
	 */
	public RxtxChannelConfig config() {
		return config;
	}

	/**
	 * Rxtx设备地址
	 * @return
	 */
	public RxtxDeviceAddress remoteAddress() {
		return deviceAddress;
	}

	/**
	 * 链接设备
	 * @param remoteAddress
	 * @throws Exception
	 */
	public void connect(SocketAddress remoteAddress) throws Exception {
		try {
			RxtxDeviceAddress remote = (RxtxDeviceAddress) remoteAddress;
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(remote.value());
			CommPort commPort = portIdentifier.open(getClass().getName(), 3000);
			if ((commPort instanceof SerialPort)) {
				this.serialPort = ((SerialPort) commPort);
				try {
					serialPort.setSerialPortParams(config().getBaudrate(), config().getDatabits().value(),
							config().getStopbits().value(), config().getParitybit().value());
				} catch (UnsupportedCommOperationException e) {
					disconnect();
					throw new Exception("Don't support to set parameters for serialport.", e.getCause());
				}
			} else {
				throw new Exception("The rxtx port is not a serialport.");
			}
		} catch (NoSuchPortException e) {
			throw new Exception("The rxtx port is not existed.", e.getCause());
		} catch (PortInUseException e) {
			throw new Exception("The rxtx port is occupied.", e.getCause());
		}
	}

	/**
	 * 断开设备
	 * 
	 * @throws Exception
	 */
	public void disconnect() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
			serialPort = null;
		}
	}
	
	/**
	 * 添加Listener
	 * @param rxtxEventListener
	 * @throws Exception
	 */
	public void addEventListener(final RxtxEventListener rxtxEventListener) throws Exception  {
		serialPort.addEventListener(new SerialPortEventListener() {
			public void serialEvent(SerialPortEvent serialPortEvent) {
				switch (serialPortEvent.getEventType()) {
				case SerialPortEvent.DSR: // 4数据设备准备好
					try {
						rxtxEventListener.channelActive(RxtxChannel.this);						
					} catch (Exception e) {
						rxtxEventListener.exceptionCaught(RxtxChannel.this, e.getCause());
					}
					break;
				case SerialPortEvent.BI: // 10通讯中断
					try {
						rxtxEventListener.channelInactive(RxtxChannel.this);						
					} catch (Exception e) {
						rxtxEventListener.exceptionCaught(RxtxChannel.this, e.getCause());
					}
					break;
				case SerialPortEvent.DATA_AVAILABLE: // 1读到可用数据时激活
					try {
						Thread.sleep(100);
						rxtxEventListener.channelRead(RxtxChannel.this, read());
					} catch (Exception e) {
						rxtxEventListener.exceptionCaught(RxtxChannel.this, e.getCause());
					}
					break;			
				}
			}
		});


		serialPort.notifyOnDSR(true);
		serialPort.notifyOnDataAvailable(true);
		serialPort.notifyOnBreakInterrupt(true);
	}

//	protected void addLinstener(final RxtxEventListener rch) throws Exception {
//		serialPort.addEventListener(new SerialPortEventListener() {
//			public void serialEvent(SerialPortEvent serialPortEvent) {
//				switch (serialPortEvent.getEventType()) {
//				case SerialPortEvent.BI: // 10通讯中断
//					try {
//						rch.channelInactive(RxtxChannel.this);						
//					} catch (Exception e) {
//						rch.exceptionCaught(RxtxChannel.this, e.getCause());
//					}
//					break;
//				// case SerialPortEvent.OE: // 7溢位错误
//				//
//				// case SerialPortEvent.FE: // 9帧错误
//				//
//				// case SerialPortEvent.PE: // 8奇偶校验错
//				//
//				// case SerialPortEvent.CD: // 6载波检测
//				//
//				// case SerialPortEvent.CTS: // 3清除发送
//				//
//				// case SerialPortEvent.DSR: // 4数据设备准备好
//				// System.out.println("===============DSR.");
//				// break;
//				// case SerialPortEvent.RI: // 5振铃指示
//				//
//				// case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2输出缓冲区已清空
//				// System.out.println("===============OUTPUT_BUFFER_EMPTY.");
//				// break;
//				case SerialPortEvent.DATA_AVAILABLE: // 1读到可用数据时激活
//					try {
//						rch.channelRead(RxtxChannel.this, read());
//					} catch (Exception e) {
//						rch.exceptionCaught(RxtxChannel.this, e.getCause());
//					}
//					break;
//				}
//			}
//		});
//
//		serialPort.notifyOnDataAvailable(true);
//		serialPort.notifyOnBreakInterrupt(true);
//	}

	/**
	 * 读数据
	 * @return
	 * @throws Exception
	 */
	public byte[] read() throws Exception {
		byte[] bytes = null;
		InputStream in = null;
		try {
			in = serialPort.getInputStream();
			for (;;) {
				int bufflenth = in.available();
				if (0 == bufflenth) {
					break;
				}
				bytes = new byte[bufflenth];
				in.read(bytes);
			}
		} catch (NullPointerException e) {
			throw new Exception("Serailport is null.", e.getCause());
		} catch (IOException e) {
			throw new Exception("Read data fail.", e.getCause());
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return bytes;
	}

	/**
	 * 写数据
	 * @param data
	 */
	public void write(byte[] data) throws Exception {
		OutputStream out = null;
		try {
			out = serialPort.getOutputStream();
			out.write(data);
			out.flush();
		} catch (NullPointerException e) {
			throw new Exception("Serailport is null.", e.getCause());
		} catch (IOException e) {
			throw new Exception("Send data fail.", e.getCause());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
