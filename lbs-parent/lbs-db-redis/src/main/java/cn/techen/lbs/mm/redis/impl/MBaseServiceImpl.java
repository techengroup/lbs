package cn.techen.lbs.mm.redis.impl;

import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import redis.clients.jedis.Jedis;

public class MBaseServiceImpl implements MBaseService {
	
	@Override
	public boolean isConnected() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.isConnected();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return false;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}
	
	@Override
	public String flushDB() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

	@Override
	public String flushAll() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.flushAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
