package cn.techen.lbs.mm.redis.impl;

import cn.techen.lbs.db.model.LBS;
import cn.techen.lbs.mm.api.MLbsService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MLbsServiceImpl implements MLbsService {

	@Override
	public void set(LBS lbs) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.set(TABLENAME.getBytes(), RedisSerializer.serialize(lbs));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public LBS get() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.get(TABLENAME.getBytes());
			return (LBS) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
