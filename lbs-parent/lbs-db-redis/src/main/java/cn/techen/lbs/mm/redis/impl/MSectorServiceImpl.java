package cn.techen.lbs.mm.redis.impl;

import java.util.List;

import cn.techen.lbs.db.model.Sector;
import cn.techen.lbs.mm.api.MSectorService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MSectorServiceImpl implements MSectorService {

	@Override
	public void put(Sector sector) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			String key = sector.getSector().toString() + ":" + sector.getDistrictX().toString();
			jedis.hset(DB_SECTOR.getBytes(), key.getBytes(), RedisSerializer.serialize(sector));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public void put(List<Sector> sectors) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			for (Sector sector : sectors) {
				String key = sector.getSector().toString() + ":" + sector.getDistrictX().toString();
				jedis.hset(DB_SECTOR.getBytes(), key.getBytes(), RedisSerializer.serialize(sector));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Sector get(Integer sector, Integer districtX) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			String key = sector.toString() + ":" + districtX.toString();
			byte[] b = jedis.hget(DB_SECTOR.getBytes(), key.getBytes());
			return (Sector) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
