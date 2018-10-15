package cn.techen.lbs.mm.redis.impl;

import java.util.List;

import cn.techen.lbs.db.model.Month;
import cn.techen.lbs.mm.api.MMonthService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MMonthServiceImpl implements MMonthService {

	@Override
	public Long size(String key) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.llen(DB_MONTH.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

	@Override
	public void lpush(Month month) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.lpush(DB_MONTH.getBytes(), RedisSerializer.serialize(month));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public void lpush(List<Month> months) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			for (Month month : months) {
				jedis.lpush(DB_MONTH.getBytes(), RedisSerializer.serialize(month));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Month rpop() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.rpop(DB_MONTH.getBytes());
			return (Month) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
