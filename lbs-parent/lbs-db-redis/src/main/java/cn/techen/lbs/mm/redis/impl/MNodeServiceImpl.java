package cn.techen.lbs.mm.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.techen.lbs.db.model.Node;
import cn.techen.lbs.mm.api.MNodeService;
import cn.techen.lbs.mm.redis.common.RedisPool;
import cn.techen.lbs.mm.redis.common.RedisSerializer;
import redis.clients.jedis.Jedis;

public class MNodeServiceImpl implements MNodeService {
	@Override
	public Long size(String key) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			return jedis.llen(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}
	
	@Override
	public void put(Node node) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			jedis.hset(DB_NODE.getBytes(), node.getCommaddr().getBytes(), RedisSerializer.serialize(node));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public void put(List<Node> nodes) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			for (Node node : nodes) {
				jedis.hset(DB_NODE.getBytes(), node.getCommaddr().getBytes(), RedisSerializer.serialize(node));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
	}

	@Override
	public Node get(String commAddr) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			byte[] b = jedis.hget(DB_NODE.getBytes(), commAddr.getBytes());
			return (Node) RedisSerializer.deserialize(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RedisPool.getInstance().returnResource(jedis);
		}
		return null;
	}

	@Override
	public List<Node> get() {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getInstance().getResource();
			Map<byte[], byte[]> maps = jedis.hgetAll(DB_NODE.getBytes());
			List<byte[]> list = (List<byte[]>) maps.values();
			List<Node> mList = new ArrayList<Node>();
			for (byte[] b : list) {
				mList.add((Node) RedisSerializer.deserialize(b));
			}
			return mList;
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
