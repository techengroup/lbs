package cn.techen.lbs.protocol.t645;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import cn.techen.lbs.protocol.FrameConfig;

public interface T645Config extends FrameConfig {
	
	enum DIR {
        /**
         * Client -- LBS --> Meter
         */
        CLIENT(0),
        /**
         * Server -- Meter --> LBS
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
				str="LBS --> Meter";
				break;
			case 0x01:
				str="Meter --> LBS";
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
	
	enum Answer {
		SUCCESS(0),
		EXCEPTION(1);

        private final int value;

        Answer(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Meter response success";
				break;
			case 0x01:
				str="Meter response exception";
				break;
			default:
				throw new IllegalArgumentException("unknown " + Answer.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static Answer valueOf(int value) {
            for (Answer aFlag : Answer.values()) {
                if (aFlag.value == value) {
                    return aFlag;
                }
            }
            throw new IllegalArgumentException("unknown " + Answer.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum FollowUp {
		NONE(0),
		HAVE(1);

        private final int value;

        FollowUp(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="NONE follow-up frame";
				break;
			case 0x01:
				str="Have follow-up frame";
				break;
			default:
				throw new IllegalArgumentException("unknown " + Answer.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static FollowUp valueOf(int value) {
            for (FollowUp fFlag : FollowUp.values()) {
                if (fFlag.value == value) {
                    return fFlag;
                }
            }
            throw new IllegalArgumentException("unknown " + FollowUp.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum Control {
        READ(0x11),
        WRITE(0x14),
        ACTION(0x1E);

        private final int value;

        Control(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
        
        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x11:
				str="Read";
				break;
			case 0x14:
				str="Write";
				break;
			case 0x1E:
				str="Action";
				break;
			default:
				throw new IllegalArgumentException("unknown " + Control.class.getSimpleName() + " value: " + value);
			}
            return str;
        }

        public static Control valueOf(int value) {
            for (Control control : Control.values()) {
                if (control.value == value) {
                    return control;
                }
            }
            throw new IllegalArgumentException("unknown " + Control.class.getSimpleName() + " value: " + value);
        }
    }
	
	DIR getDir();
	
	T645Config setDir(DIR dir);
	
	Answer getAnswer();
	
	T645Config setAnswer(Answer flag);
	
	FollowUp getFollowUp();
	
	T645Config setFollowUp(FollowUp flag);
	
	Control getControl();
	
	T645Config setControl(Control control);
	
	String getCommAddr();

	T645Config setCommAddr(String commAddr);

	List<String> func();
	
	Queue<Object> unit();

	Map<String, Object> data();
	
}
