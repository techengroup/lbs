package cn.techen.lbs.g4.common;

public enum Status {
	/**
     * DISCONNECT
     */
    DISCONNECT(0),
    /**
     * CONNECT
     */
    CONNECT(1);
//    /**
//     * LOGIN    
//     */
//    LOGIN(2),
//    /**
//     * HANDSHAKE
//     */
//    HANDSHAKE(3),
//    /**
//     * IDLE
//     */
//    IDLE(4),
//    /**
//     * BUSY
//     */
//    BUSY(5);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static Status valueOf(int value) {
        for (Status dir : Status.values()) {
            if (dir.value == value) {
                return dir;
            }
        }
        throw new IllegalArgumentException("unknown " + Status.class.getSimpleName() + " value: " + value);
    }
}
