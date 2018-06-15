package cn.techen.lbs.mm.redis.impl;

import java.util.List;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.mm.api.MRegisterService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MRegisterServiceImpl implements MRegisterService {

	@Override
	public void lpush(Meter meter) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.lpush(DB_METER_UNREGISTER.getBytes(), RedisSerializer.serialize(meter));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public void lpush(List<Meter> meters) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			for (Meter meter : meters) {
				jedis.lpush(DB_METER_UNREGISTER.getBytes(), RedisSerializer.serialize(meter));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Meter rpop() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.rpop(DB_METER_UNREGISTER.getBytes());
			return (Meter) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
