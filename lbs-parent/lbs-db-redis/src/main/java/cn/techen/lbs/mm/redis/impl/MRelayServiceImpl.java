package cn.techen.lbs.mm.redis.impl;

import java.util.List;

import cn.techen.lbs.db.model.Meter;
import cn.techen.lbs.mm.api.MRelayService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MRelayServiceImpl implements MRelayService {

	@Override
	public void put(Meter meter) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			if (meter.getRelay() == 1) {
				String key = meter.getSector().toString() + ":" + meter.getGrade().toString();
				jedis.hset(DB_METER_RELAY.getBytes(), key.getBytes(), RedisSerializer.serialize(meter));
			}
			if (meter.getRelay() == 2) {
				String key = meter.getSector().toString() + ":" + meter.getGrade().toString() + ":"
						+ meter.getDistrictY().toString();
				jedis.hset(DB_METER_RELAY.getBytes(), key.getBytes(), RedisSerializer.serialize(meter));
			}
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
				if (meter.getRelay() == 1) {
					String key = meter.getSector().toString() + ":" + meter.getGrade().toString();
					jedis.hset(DB_METER_RELAY.getBytes(), key.getBytes(), RedisSerializer.serialize(meter));
				}
				if (meter.getRelay() == 2) {
					String key = meter.getSector().toString() + ":" + meter.getGrade().toString() + ":"
							+ meter.getDistrictY().toString();
					jedis.hset(DB_METER_RELAY.getBytes(), key.getBytes(), RedisSerializer.serialize(meter));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Meter get(Integer sector, Integer grade, Integer districtY) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			String key = sector.toString() + ":" + grade.toString() + ":" + districtY.toString();
			byte[] b = jedis.hget(DB_METER_RELAY.getBytes(), key.getBytes());
			return (Meter) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

	@Override
	public Meter getOptimal(Integer sector, Integer grade) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			String key = sector.toString() + ":" + grade.toString();
			byte[] b = jedis.hget(DB_METER_RELAY.getBytes(), key.getBytes());
			return (Meter) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
