package cn.techen.lbs.mm.api;

/**
 * MTaskService
 * @author ZY
 * @param <V>
 * @param <T>
 * @since 2018-03-14 16:55
 */
public interface MTaskService<V> extends MyService<String, V> {
	
	static final String QUEUE_SEND = "QUEUE:SEND:TASK:";
	static final String QUEUE_RETURN = "QUEUE:RETURN:TASK:";
	static final String UPQUEUE_SEND = "UPQUEUE:SEND:TASK";
	static final String UPQUEUE_RETURN = "UPQUEUE:RETURN:TASK";
	
	void lpush(String queueName, V entity);

	V rpop(String queueName);
	
}
