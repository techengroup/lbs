package cn.techen.lbs.protocol.lora;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import cn.techen.lbs.protocol.FrameConfig;

public interface LoraConfig extends FrameConfig {
	
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
	
	enum Channel {
		CHANNEL_0(0),
		CHANNEL_1(1),
		CHANNEL_2(2),
		CHANNEL_3(3),
		CHANNEL_4(4),		
		CHANNEL_5(5),
		CHANNEL_6(6),		
		CHANNEL_7(7);

        private final int value;

        Channel(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static Channel valueOf(int value) {
            for (Channel channel : Channel.values()) {
                if (channel.value == value) {
                    return channel;
                }
            }
            throw new IllegalArgumentException("unknown " + Channel.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum Relay {
		RELAY_0(0),
		RELAY_1(1),
		RELAY_2(2),
		RELAY_3(3),
		RELAY_4(4);

        private final int value;

        Relay(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static Relay valueOf(int value) {
            for (Relay relay : Relay.values()) {
                if (relay.value == value) {
                    return relay;
                }
            }
            throw new IllegalArgumentException("unknown " + Relay.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum Addr {
		LONG(0),
		SHORT(1);

        private final int value;

        Addr(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
        
        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="Long";
				break;
			case 0x01:
				str="Short";
				break;
			default:
				throw new IllegalArgumentException("unknown " + Addr.class.getSimpleName() + " value: " + value);
			}
            return str;
        }

        public static Addr valueOf(int value) {
            for (Addr addr : Addr.values()) {
                if (addr.value == value) {
                    return addr;
                }
            }
            throw new IllegalArgumentException("unknown " + Addr.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum Module {
		MASTER(0),
		SLAVE(1);

        private final int value;

        Module(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public String descOf() {
        	String str = "";
        	switch (value) {
			case 0x00:
				str="LBS Lora Module";
				break;
			case 0x01:
				str="Meter Lora Module";
				break;
			default:
				throw new IllegalArgumentException("unknown " + Module.class.getSimpleName() + " value: " + value);
			}
            return str;
        }
        
        public static Module valueOf(int value) {
            for (Module model : Module.values()) {
                if (model.value == value) {
                    return model;
                }
            }
            throw new IllegalArgumentException("unknown " + Module.class.getSimpleName() + " value: " + value);
        }
    }
	
	enum Control {
        NET(0x01),
        REPORT(0x02),
        READ(0x03),
        WRITE(0x04),
        TRANSPORT(0x05);

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
			case 0x01:
				str="Networking";
				break;
			case 0x02:
				str="Report";
				break;
			case 0x03:
				str="Read";
				break;
			case 0x04:
				str="Write";
				break;
			case 0x09:
				str="Transfer";
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
	
	LoraConfig setDir(DIR dir);
	
	Channel getChannel();
	
	LoraConfig setChannel(Channel channel);
	
	Relay getRelay();
	
	LoraConfig setRelay(Relay relay);
	
	Addr getAddr();
	
	LoraConfig setAddr(Addr addr);
	
	Module getModule();
	
	LoraConfig setModule(Module module);
	
	Control getControl();
	
	LoraConfig setControl(Control control);
	
	int getRSSI();
	
	LoraConfig setRSSI(int rssi);
	
	String getSourceAddr();

	LoraConfig setSourceAddr(String sourceAddr);

	List<String> getRelayAddrs();

	LoraConfig addRelayAddrs(String... relayAddrs);

	String getTargetAddr();

	LoraConfig setTargetAddr(String targetAddr);
	
	List<String> func();
	
	Queue<Object> unit();

	Map<String, Object> data();
	
}
