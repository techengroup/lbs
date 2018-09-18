package cn.techen.lbs.protocol.t376;

import cn.techen.lbs.protocol.FrameConfig;

public interface T376Config extends FrameConfig {			
	enum DIR {
        /**
         * Client -- Master Station
         */
        CLIENT(0),
        /**
         * Server -- LBS
         */
        SERVER(1);

        private final int value;

        DIR(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
        
        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Master Station-->LBS";
				break;
			case 0x01:
				str="LBS-->Master Station";
				break;
			default:
				throw new IllegalArgumentException("unknown " + DIR.class.getSimpleName() + " value: " + value);
			}
            return str;
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
	
	enum ACD {
		NO(0),
		YES(1);

        private final int value;

        ACD(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Without any event";
				break;
			case 0x01:
				str="With some event";
				break;
			default:
				throw new IllegalArgumentException("unknown " + ACD.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static ACD valueOf(int value) {
            for (ACD acd : ACD.values()) {
                if (acd.value == value) {
                    return acd;
                }
            }
            throw new IllegalArgumentException("unknown " + ACD.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum TPV {
		NO(0),
		YES(1);

        private final int value;

        TPV(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Without time tag";
				break;
			case 0x01:
				str="With time tag";
				break;
			default:
				throw new IllegalArgumentException("unknown " + TPV.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static TPV valueOf(int value) {
            for (TPV tpv : TPV.values()) {
                if (tpv.value == value) {
                    return tpv;
                }
            }
            throw new IllegalArgumentException("unknown " + TPV.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum AFN {
        CONFIRM(0x00),
        RESET(0x01),
        LINK(0x02),
        RELAY(0x03),
        SETPARAM(0x04),
        ACTION(0x05),
        APPROVE(0x06),
        OTHERTMLREPORT(0x08),
        GETTMLCONFIG(0x09),
        GETPARAM(0x0A),
        GETTASK(0x0B),
        GETREALTIME(0x0C),
        GETHISTORY(0x0D),
        GETEVENT(0x0E),
        FIRMWARE(0x0F),
        TANSFER(0x10);

        private final int value;

        AFN(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
        
        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Confirm";
				break;
			case 0x02:
				str="Link Detection";
				break;
			case 0x04:
				str="Set Parameter";
				break;
			case 0x0A:
				str="Get Parameter";
				break;
			case 0x0C:
				str="Get Real Data";
				break;
			case 0x0D:
				str="Get History Data";
				break;
			case 0x0E:
				str="Get Event";
				break;
			case 0x10:
				str="Transfer Data";
				break;
			default:
				throw new IllegalArgumentException("unknown " + AFN.class.getSimpleName() + " value: " + value);
			}
            return str;
        }

        public static AFN valueOf(int value) {
            for (AFN afn : AFN.values()) {
                if (afn.value == value) {
                    return afn;
                }
            }
            throw new IllegalArgumentException("unknown " + AFN.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum CON {
		NO(0),
		YES(1);

        private final int value;

        CON(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Don't need confirm for this frame";
				break;
			case 0x01:
				str="Need Confirm for this frame";
				break;
			default:
				throw new IllegalArgumentException("unknown " + CON.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static CON valueOf(int value) {
            for (CON con : CON.values()) {
                if (con.value == value) {
                    return con;
                }
            }
            throw new IllegalArgumentException("unknown " + CON.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum FUNC0 {
		CONFIRM(0),
		USERDATA(8),
		NODATA(9),
		LINK(11);

        private final int value;

        FUNC0(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Confirm last frame";
				break;
			case 0x08:
				str="Response user data, have data";
				break;
			case 0x09:
				str="Response user data, no data";
				break;
			case 0x0B:
				str="Response link";
				break;
			default:
				throw new IllegalArgumentException("unknown " + FUNC0.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static FUNC0 valueOf(int value) {
            for (FUNC0 func : FUNC0.values()) {
                if (func.value == value) {
                    return func;
                }
            }
            throw new IllegalArgumentException("unknown " + FUNC0.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum FUNC1 {
		REST(0),
		USERDATA(4),
		LINK(9),
		DATA1(10),
		DATA2(11);

        private final int value;

        FUNC1(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Request rest, need comfirm";
				break;
			case 0x04:
				str="Send user data, no response";
				break;
			case 0x09:
				str="Request link, need response";
				break;
			case 0x0A:
				str="Request data class 1, need response";
				break;
			case 0x0B:
				str="Request data class 2, need response";
				break;
			default:
				throw new IllegalArgumentException("unknown " + FUNC1.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static FUNC1 valueOf(int value) {
            for (FUNC1 func : FUNC1.values()) {
                if (func.value == value) {
                    return func;
                }
            }
            throw new IllegalArgumentException("unknown " + FUNC1.class.getSimpleName() + " value: " + value);
        }
    }
	
	DIR getDir();
	
	T376Config setDir(DIR dir);
	
	int getPrm();
	
	T376Config setPrm(int prm);
	
	ACD getAcd();
	
	T376Config setAcd(ACD acd);
	
	int getFcb();
	
	T376Config setFcb(int fcb);
	
	int getFcv();
	
	T376Config setFcv(int fcv);
	
	int getFunc();
	
	T376Config setFunc(int func);
	
	AFN getAfn();
	
	T376Config setAfn(AFN afn);
	
	TPV getTpv();
	
	T376Config setTpv(TPV tpv);
	
	int getFir();
	
	T376Config setFir(int fir);
	
	int getFin();
	
	T376Config setFin(int fin);
	
	CON getCon();
	
	T376Config setCon(CON con);
	
	int getSeq();
	
	T376Config setSeq(int seq);
	
	String getCommAddr();

	T376Config setCommAddr(String commAddr);
}
