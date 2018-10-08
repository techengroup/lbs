package cn.techen.lbs.mm.redis.impl;

import java.util.List;

import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.mm.api.MNodeService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MNodeServiceImpl implements MNodeService {

	@Override
	public Long size() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.llen(DB_NODE_UNREGISTER.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

	@Override
	public void lpush(Node node) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.lpush(DB_NODE_UNREGISTER.getBytes(), RedisSerializer.serialize(node));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public void lpush(List<Node> nodes) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			for (Node node : nodes) {
				jedis.lpush(DB_NODE_UNREGISTER.getBytes(), RedisSerializer.serialize(node));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Node rpop() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.rpop(DB_NODE_UNREGISTER.getBytes());
			return (Node) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

}
