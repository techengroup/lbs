package cn.techen.lbs.mm.redis.impl;

import cn.techen.lbs.mm.api.MTaskService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MTaskServiceImpl<V> implements MTaskService<V> {

	@Override
	public void lpush(String queueName, V entity) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.lpush(queueName.getBytes(), RedisSerializer.serialize(entity));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public V rpop(String queueName) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.lpop(queueName.getBytes());
			return (V) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
