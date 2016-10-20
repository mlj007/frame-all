/**
 *  ClassName: ShardedRedisOperator.java
 *  created on 2013-4-19  上午09:14:20
 *  Copyrights 2011-2013 车讯网 All rights reserved.
 */
package com.chexun.base.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author 安李勇
 * 
 */
public class ShardedRedisOperator {

	private ShardedJedisPool shardedJedisPool;
	
	private static final String OK = "OK";
	
	@SuppressWarnings("unused")
	private ShardedRedisOperator(){}

	public ShardedRedisOperator(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public ShardedJedis getShardedJedis() {
		return shardedJedisPool.getResource();
	}

	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
	}

	public boolean put(String key, String value) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return OK.equals(shardedJedis.set(key, value)) ? true : false;
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	public String get(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.get(key);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	public <T> boolean put(String key, T entity) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return OK.equals(shardedJedis.set(key, JSONObject.fromObject(entity).toString())) ? true : false;
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String key, Class<?> clz) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return (T)JSONObject.toBean(JSONObject.fromObject(shardedJedis.get(key)), clz);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	public boolean exists(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.exists(key);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	public long del(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.del(key);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 返回值的类型
	 * @param key
	 * @return
	 */
	public String type(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.type(key);
		} finally {
			returnResource(shardedJedis);
		}
	}
	/**
	 * 向库中添加string（名称为key，值为value）同时，设定过期时间 seconds
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 */
	public boolean setex(String key, int seconds, String value) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return OK.equals(shardedJedis.setex(key, seconds, value)) ? true : false;
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 名称为key的string增1操作
	 * @param key
	 * @return
	 */
	public long incr(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.incr(key);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 名称为key的string减1操作
	 * @param key
	 * @return
	 */
	public long decr(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.decr(key);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 名称为key的string增加integer
	 * @param key
	 * @param integer
	 * @return
	 */
	public long incrby(String key, long integer) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.incrBy(key, integer);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 名称为key的string减integer
	 * @param key
	 * @param integer
	 * @return
	 */
	public long decrby(String key, long integer) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.decrBy(key, integer);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 名称为key的string的值附加value
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean append(String key, String value) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return OK.equals(shardedJedis.append(key, value)) ? true : false;
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 返回名称为key的string的value的子串
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public String substr(String key, int start, int end) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.substr(key, start, end);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 在名称为key的list尾添加一个值为value的元素
	 * @param key
	 * @param value
	 * @return
	 */
	public long rpush(String key, String...values) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.rpush(key, values);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 在名称为key的list头添加一个值为value的 元素
	 * @param key
	 * @param value
	 * @return
	 */
	public long lpush(String key, String...values) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.lpush(key, values);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 返回名称为key的list的长度
	 * @param key
	 * @return
	 */
	public long llen(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.llen(key);
		} finally {
			returnResource(shardedJedis);
		}
	}
	
	/**
	 * 返回名称为key的list中start至end之间的元素（下标从0开始，下同）
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key, long start, long end) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.lrange(key, start, end);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 截取名称为key的list，保留start至end之间的元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public String ltrim(String key, long start, long end) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.ltrim(key, start, end);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的list中index位置的元素
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key, long index) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.lindex(key, index);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 给名称为key的list中index位置的元素赋值为value
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean lset(String key, long index, String value) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return OK.equals(shardedJedis.lset(key, index, value)) ? true : false;
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 删除count个名称为key的list中值为value的元素。count为0，删除所有值为value的元素，count>0从头至尾删除count个值为value的元素，count<0从尾到头删除|count|个值为value的元素。
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 */
	public long lrem(String key, long count, String value) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.lrem(key, count, value);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回并删除名称为key的list中的首元素
	 * @param key
	 * @return
	 */
	public String lpop(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.lpop(key);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回并删除名称为key的list中的尾元素
	 * @param key
	 * @return
	 */
	public String rpop(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.rpop(key);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 向名称为key的set中添加元素member
	 * @param key
	 * @param values
	 * @return
	 */
	public long sadd(String key, String...member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.sadd(key, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 删除名称为key的set中的元素member
	 * @param key
	 * @param values
	 * @return
	 */
	public long srem(String key, String...member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.srem(key, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的set的基数
	 * @param key
	 * @return
	 */
	public long scard(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.scard(key);
		}finally{
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 测试member是否是名称为key的set的元素
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sismember(String key, String member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.sismember(key, member);
		}finally{
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序。
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public long zadd(String key, double score, String member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zadd(key, score, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 删除名称为key的zset中的元素member
	 * @param key
	 * @param member
	 * @return
	 */
	public long zrem(String key, String...member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zrem(key, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 如果在名称为key的zset中已经存在元素member，则该元素的score增加increment；否则向集合中添加该元素，其score的值为increment
	 * @param key
	 * @param increment
	 * @param member
	 * @return
	 */
	public double zrem(String key, double increment, String member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zincrby(key, increment, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset（元素已按score从小到大排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
	 * @param key
	 * @param member
	 * @return
	 */
	public long zrank(String key, String member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zrank(key, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset（元素已按score从小到大排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
	 * @param key
	 * @param member
	 * @return
	 */
	public long zrevrank(String key, String member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zrevrank(key, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset（元素已按score从小到大排序）中的index从start到end的所有元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrange(String key, long start, long end) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zrange(key, start, end);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset中score >= min且score <= max的所有元素
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zrangeByScore(key, min, max);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset中元素element的score
	 * @param key
	 * @param member
	 * @return
	 */
	public double zscore(String key, String member) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zscore(key, member);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 删除名称为key的zset中rank >= start且rank <= end的所有元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public double zremrangeByRank(String key, long start, long end) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zremrangeByRank(key, start, end);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 删除名称为key的zset中score >= start且score <= end的所有元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public double zremrangeByScore(String key, double start, double end) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.zremrangeByScore(key, start, end);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 向名称为key的hash中添加元素field<—>value
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public long hset(String key, String field, String value) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hset(key, field, value);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中field对应的value
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hget(key, field);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中fields对应的value
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget(String key, String...fields) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hmget(key, fields);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 向名称为key的hash中添加hash
	 * @param key
	 * @param hash
	 * @return
	 */
	public boolean hmset(String key, Map<String, String> hash) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return OK.equals(shardedJedis.hmset(key, hash)) ? true : false;
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 将名称为key的hash中field的value增加integer
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public long hincrBy(String key, String field, long value) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hincrBy(key, field, value);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 名称为key的hash中是否存在键为field的域
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean hexists(String key, String field) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hexists(key, field);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 删除名称为key的hash中键为field的域
	 * @param key
	 * @param field
	 * @return
	 */
	public long hdel(String key, String field) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hdel(key, field);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中元素个数
	 * @param key
	 * @return
	 */
	public long hlen(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();

		try {
			return shardedJedis.hlen(key);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中所有键
	 * @param key
	 * @return
	 */
	public Set<String> hkeys(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hkeys(key);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中所有键对应的value
	 * @param key
	 * @return
	 */
	public List<String> hvals(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hvals(key);
		} finally {
			returnResource(shardedJedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中所有的键（field）及其对应的value
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key) {
		
		ShardedJedis shardedJedis = getShardedJedis();
		
		try {
			return shardedJedis.hgetAll(key);
		} finally {
			returnResource(shardedJedis);
		}
		
	}

}
