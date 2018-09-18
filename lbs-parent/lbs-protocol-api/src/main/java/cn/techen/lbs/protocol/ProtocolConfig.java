package cn.techen.lbs.protocol;

import java.io.Serializable;

public interface ProtocolConfig extends IConfig, Serializable {
	enum DIR {
        /**
         * Client -- for meter is LBS, for LBS is MS
         */
        CLIENT(0),
        /**
         * Server -- for LBS is meter, for MS is LBS
         */
        SERVER(1);

        private final int value;

        DIR(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static DIR valueOf(int value) {
            for (DIR dir : DIR.values()) {
                if (dir.value == value) {
                    return dir;
                }
            }
            throw new IllegalArgumentException("unknown " + DIR.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum OPERATION {
        GET(1),
        SET(2),
        ACTION(3),
        TRANSPORT(4),
        PROXY(5),
        REPORT(6),
        LOGIN(20),
        HEARTBEAT(21),
        LOGOUT(22),
        CONFIRM(90);

        private final int value;

        OPERATION(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static OPERATION valueOf(int value) {
            for (OPERATION op : OPERATION.values()) {
                if (op.value == value) {
                    return op;
                }
            }
            throw new IllegalArgumentException("unknown " + OPERATION.class.getSimpleName() + " value: " + value);
        }
    }
	
	String getCommAddr();
	
	ProtocolConfig setCommAddr(String commAddr);
	
	DIR getDir();

	ProtocolConfig setDir(DIR dir);

	OPERATION getOperation();

	ProtocolConfig setOperation(OPERATION operation);
	
}
