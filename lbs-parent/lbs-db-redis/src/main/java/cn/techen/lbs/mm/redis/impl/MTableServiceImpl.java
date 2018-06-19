package cn.techen.lbs.mm.redis.impl;

import cn.techen.lbs.db.model.Table;
import cn.techen.lbs.mm.api.MTableService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MTableServiceImpl implements MTableService {

	@Override
	public void lpush(Table table) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.lpush(STORE_TABLE.getBytes(), RedisSerializer.serialize(table));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Table rpop() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.lpop(STORE_TABLE.getBytes());
			return (Table) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
