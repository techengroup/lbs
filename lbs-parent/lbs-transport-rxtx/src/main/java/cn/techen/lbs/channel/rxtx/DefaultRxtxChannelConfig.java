package cn.techen.lbs.channel.rxtx;

final class DefaultRxtxChannelConfig implements RxtxChannelConfig {
	private volatile int baudrate = 9600;
//    private volatile boolean dtr;
//    private volatile boolean rts;
    private volatile Stopbits stopbits = Stopbits.STOPBITS_1;
    private volatile Databits databits = Databits.DATABITS_8;
    private volatile Paritybit paritybit = Paritybit.NONE;
//    private volatile int waitTime;
    private volatile int readTimeout = 1000;
    
//    DefaultRxtxChannelConfig(RxtxChannel channel) {
//       this.channel = channel;
//    }

    public RxtxChannelConfig setBaudrate(final int baudrate) {
        this.baudrate = baudrate;
        return this;
    }

    public RxtxChannelConfig setStopbits(final Stopbits stopbits) {
        this.stopbits = stopbits;
        return this;
    }

    public RxtxChannelConfig setDatabits(final Databits databits) {
        this.databits = databits;
        return this;
    }

    public RxtxChannelConfig setParitybit(final Paritybit paritybit) {
        this.paritybit = paritybit;
        return  this;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public Stopbits getStopbits() {
        return stopbits;
    }

    public Databits getDatabits() {
        return databits;
    }

    public Paritybit getParitybit() {
        return paritybit;
    }

	public RxtxChannelConfig setReadTimeout(int readTimeout) {
		if (readTimeout < 0) {
            throw new IllegalArgumentException("readTime must be >= 0");
        }
        this.readTimeout = readTimeout;
        return this;
	}

	public int getReadTimeout() {
		 return readTimeout;
	}

}
