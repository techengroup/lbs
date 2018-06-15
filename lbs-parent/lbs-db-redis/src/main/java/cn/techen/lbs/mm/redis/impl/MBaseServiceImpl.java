package cn.techen.lbs.mm.redis.impl;

import cn.techen.lbs.mm.api.MBaseService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import redis.clients.jedis.Jedis;

public class MBaseServiceImpl implements MBaseService {

	private boolean isLoad = false;

	@Override
	public boolean loaded() {
		return isLoad;
	}

	@Override
	public void setLoaded(boolean result) {
		this.isLoad = result;
	}

	@Override
	public String flushdb() {
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

}
