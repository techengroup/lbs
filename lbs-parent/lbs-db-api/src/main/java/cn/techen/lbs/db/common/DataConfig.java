package cn.techen.lbs.db.common;

public interface DataConfig {
	enum ENERGY {
        /**
         * 正向有功
         */
        ACTIVE(1),
        /**
         * 正向无功
         */
        NEGATIVE(2),
		/**
         * 反向有功
         */
        ACTIVE_(3),
        /**
         * 反向无功
         */
        NEGATIVE_(4);

        private final int value;

        ENERGY(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
        
        public String descOf() {
        	String str = "";
        	switch (value) {
			case 1:
				str="901F";
				break;
			case 2:
				str="903F";
				break;
			case 3:
				str="902F";
				break;
			case 4:
				str="904F";
				break;
			default:
				throw new IllegalArgumentException("unknown " + ENERGY.class.getSimpleName() + " value: " + value);
			}
            return str;
        }

        public static ENERGY valueOf(int value) {
            for (ENERGY energy : ENERGY.values()) {
                if (energy.value == value) {
                    return energy;
                }
            }
            throw new IllegalArgumentException("unknown " + ENERGY.class.getSimpleName() + " value: " + value);
        }
    }

}
