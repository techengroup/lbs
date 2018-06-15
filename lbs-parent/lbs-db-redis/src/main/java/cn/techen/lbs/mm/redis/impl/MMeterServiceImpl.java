package cn.techen.lbs.mm.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.mm.api.MMeterService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MMeterServiceImpl implements MMeterService {
	@Override
	public void put(Meter meter) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.hset(DB_METER.getBytes(), meter.getCommaddr().getBytes(), RedisSerializer.serialize(meter));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public void put(List<Meter> meters) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			for (Meter meter : meters) {
				jedis.hset(DB_METER.getBytes(), meter.getCommaddr().getBytes(), RedisSerializer.serialize(meter));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Meter get(String commAddr) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.hget(DB_METER.getBytes(), commAddr.getBytes());
			return (Meter) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

	@Override
	public List<Meter> get() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			Map<byte[], byte[]> maps = jedis.hgetAll(DB_METER.getBytes());
			List<byte[]> list = (List<byte[]>) maps.values();
			List<Meter> mList = new ArrayList<Meter>();
			for (byte[] b : list) {
				mList.add((Meter) RedisSerializer.deserialize(b));
			}
			return mList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
