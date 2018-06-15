package cn.techen.lbs.protocol;

public interface FrameConfig {
	enum State {
		FINISHED(0),
	    SENDING(1),
	    RECIEVING(2);

	    private final int value;

	    State(int value) {
	        this.value = value;
	    }

	    public int value() {
	        return value;
	    }

	    public static State valueOf(int value) {
	        for (State state : State.values()) {
	            if (state.value == value) {
	                return state;
	            }
	        }
	        throw new IllegalArgumentException("unknown " + State.class.getSimpleName() + " value: " + value);
	    }
    }
	
	enum Priority {
		NETWORKING(0),
	    HISTDATA(1),
	    EVENT(2),
	    REALTIME(3),
	    REPORT(99);

	    private final int value;

	    Priority(int value) {
	        this.value = value;
	    }

	    public int value() {
	        return value;
	    }

	    public static Priority valueOf(int value) {
	        for (Priority priority : Priority.values()) {
	            if (priority.value == value) {
	                return priority;
	            }
	        }
	        throw new IllegalArgumentException("unknown " + Priority.class.getSimpleName() + " value: " + value);
	    }
    }	
}
