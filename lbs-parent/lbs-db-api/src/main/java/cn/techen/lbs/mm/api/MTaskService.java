package cn.techen.lbs.mm.api;

/**
 * MTaskService
 * @author ZY
 * @param <V>
 * @param <T>
 * @since 2018-03-14 16:55
 */
public interface MTaskService<V> extends MyService<String, V> {
	
	static final String QUEUE_LORA_SEND = "QUEUE:LORA:SEND:";
	static final String QUEUE_LORA_RECEIVE = "QUEUE:LORA:RECEIVE:";
	static final String QUEUE_4G_TANS_LORA = "QUEUE:4G:TRANS:LORA";
	static final String QUEUE_4G_SEND = "QUEUE:4G:SEND";
	static final String QUEUE_4G_RECEIVE = "QUEUE:4G:REVEIVE";
	
	void lpush(String queueName, V entity);

	V rpop(String queueName);
	
}
