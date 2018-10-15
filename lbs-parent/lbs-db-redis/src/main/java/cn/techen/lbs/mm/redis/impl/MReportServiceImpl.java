package cn.techen.lbs.mm.redis.impl;

import java.util.List;

import cn.techen.lbs.db.model.Report;
import cn.techen.lbs.mm.api.MReportService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MReportServiceImpl implements MReportService {
	
	@Override
	public Long size(String key) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.llen(DB_REPORT.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

	@Override
	public void lpush(Report report) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.lpush(DB_REPORT.getBytes(), RedisSerializer.serialize(report));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public void lpush(List<Report> reports) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			for (Report report : reports) {
				jedis.lpush(DB_REPORT.getBytes(), RedisSerializer.serialize(report));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Report rpop() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.rpop(DB_REPORT.getBytes());
			return (Report) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
