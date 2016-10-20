/**
 *  ClassName: RedisOperator.java
 *  created on 2013-4-22  下午03:21:23
 *  Copyrights 2011-2013 车讯网 All rights reserved.
 */
package com.chexun.base.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 安李勇
 *
 */
public class RedisOperator {

	private JedisPool jedisPool;
	
	private static final String OK = "OK";
	
	@SuppressWarnings("unused")
	private RedisOperator(){}

	public RedisOperator(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	public void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}
	
	public boolean put(String key, String value) {
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.set(key, value)) ? true : false;
		} finally {
			returnResource(jedis);
		}
		
	}
	
	public String get(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.get(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	public byte[] get(byte[] key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.get(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	public boolean exists(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.exists(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	public long del(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.del(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
public long del2(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.del(SerializeUtil.serialize(key));
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回值的类型
	 * @param key
	 * @return
	 */
	public String type(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.type(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回满足给定pattern的所有key
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.keys(pattern);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 将key由oldname重命名为newname，若newname存在则删除newname表示的key
	 * @param oldkey
	 * @param newkey
	 * @return
	 */
	public boolean keys(String oldkey, String newkey) {
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.rename(oldkey, newkey)) ? true : false;
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回当前数据库中key的数目
	 * @return
	 */
	public long dbSize() {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.dbSize();
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 设定一个key的活动时间（s）
	 * @param key
	 * @param seconds
	 * @return
	 */
	public long expire(String key, int seconds) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.expire(key, seconds);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 获得一个key的活动时间
	 * @param key
	 * @return
	 */
	public long ttl(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.ttl(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 按索引查询
	 * @param index
	 * @return
	 */
	public String select(int index) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.select(index);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 删除当前选择数据库中的所有key
	 * @return
	 */
	public boolean flushDB() {
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.flushDB()) ? true : false;
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 删除所有数据库中的所有key
	 * @return
	 */
	public String flushAll() {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.flushAll();
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回库中多个string（keys）的value
	 * @param keys
	 * @return
	 */
	public List<String> mget(String...keys) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.mget(keys);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 如果不存在名称为key的string，则向库中添加string，名称为key，值为value
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setnx(String key, String value) {
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.setnx(key, value)) ? true : false;
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.setex(key, seconds, value)) ? true : false;
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 向库中添加string（名称为key，值为value）同时，设定过期时间 seconds
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 */
	public boolean setex(byte[] key, int seconds, byte[] value) {
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.setex(key, seconds, value)) ? true : false;
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 名称为key的string增1操作
	 * @param key
	 * @return
	 */
	public long incr(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.incr(key);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 名称为key的string减1操作
	 * @param key
	 * @return
	 */
	public long decr(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.decr(key);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 名称为key的string增加integer
	 * @param key
	 * @param integer
	 * @return
	 */
	public long incrby(String key, long integer) {
		
		Jedis jedis = getJedis();
		try {
			return jedis.incrBy(key, integer);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 名称为key的string减integer
	 * @param key
	 * @param integer
	 * @return
	 */
	public long decrby(String key, long integer) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.decrBy(key, integer);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 名称为key的string的值附加value
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean append(String key, String value) {
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.append(key, value)) ? true : false;
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.substr(key, start, end);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 在名称为key的list尾添加一个值为value的元素
	 * @param key
	 * @param value
	 * @return
	 */
	public long rpush(String key, String...values) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.rpush(key, values);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 在名称为key的list头添加一个值为value的 元素
	 * @param key
	 * @param value
	 * @return
	 */
	public long lpush(String key, String...values) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.lpush(key, values);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 返回名称为key的list的长度
	 * @param key
	 * @return
	 */
	public long llen(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.llen(key);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.lrange(key, start, end);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.ltrim(key, start, end);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的list中index位置的元素
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key, long index) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.lindex(key, index);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.lset(key, index, value)) ? true : false;
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.lrem(key, count, value);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回并删除名称为key的list中的首元素
	 * @param key
	 * @return
	 */
	public String lpop(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.lpop(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回并删除名称为key的list中的尾元素
	 * @param key
	 * @return
	 */
	public String rpop(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.rpop(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 向名称为key的set中添加元素member
	 * @param key
	 * @param values
	 * @return
	 */
	public long sadd(String key, String...member) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sadd(key, member);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 删除名称为key的set中的元素member
	 * @param key
	 * @param values
	 * @return
	 */
	public long srem(String key, String...member) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.srem(key, member);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的set的基数
	 * @param key
	 * @return
	 */
	public long scard(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.scard(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 测试member是否是名称为key的set的元素
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sismember(String key, String member) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sismember(key, member);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 求交集
	 * @param keys
	 * @return
	 */
	public Set<String> sinter(String...keys) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sinter(keys);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 求交集并将交集保存到dstkey的集合
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	public long sinterstore(String dstkey, String...keys) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sinterstore(dstkey, keys);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 求并集
	 * @param keys
	 * @return
	 */
	public Set<String> sunion(String...keys) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sunion(keys);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 求并集并将并集保存到dstkey的集合
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	public long sunionstore(String dstkey, String...keys) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sunionstore(dstkey, keys);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 求差集
	 * @param keys
	 * @return
	 */
	public Set<String> sdiff(String...keys) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sdiff(keys);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 求差集并将差集保存到dstkey的集合
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	public long sdiffstore(String dstkey, String...keys) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.sdiffstore(dstkey, keys);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的set的所有元素
	 * @param key
	 * @return
	 */
	public Set<String> smembers(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.smembers(key);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zadd(key, score, member);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 删除名称为key的zset中的元素member
	 * @param key
	 * @param member
	 * @return
	 */
	public long zrem(String key, String...member) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zrem(key, member);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zincrby(key, increment, member);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset（元素已按score从小到大排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
	 * @param key
	 * @param member
	 * @return
	 */
	public long zrank(String key, String member) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zrank(key, member);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset（元素已按score从小到大排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
	 * @param key
	 * @param member
	 * @return
	 */
	public long zrevrank(String key, String member) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zrevrank(key, member);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zrange(key, start, end);
		} finally {
			returnResource(jedis);
		}
		
	}
	/**
	 * 获取给定区间的元素，原始按照权重由高到低排序
	 *
	 * @param String  key
	 * @param long start
	 * @param long end
	 * @return Set<String>
	 * */
	public Set<String> zrevrange(String key, long start, long end) {
		//ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = getJedis();
		Set<String> set = sjedis.zrevrange(key, start, end);
		returnResource(sjedis);
		return set;
	}
	/**
	 * 返回名称为key的zset中score >= min且score <= max的所有元素
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zrangeByScore(key, min, max);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的zset中元素element的score
	 * @param key
	 * @param member
	 * @return
	 */
	public double zscore(String key, String member) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zscore(key, member);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zremrangeByRank(key, start, end);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.zremrangeByScore(key, start, end);
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hset(key, field, value);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中field对应的value
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hget(key, field);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中fields对应的value
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget(String key, String...fields) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hmget(key, fields);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 向名称为key的hash中添加hash
	 * @param key
	 * @param hash
	 * @return
	 */
	public boolean hmset(String key, Map<String, String> hash) {
		
		Jedis jedis = getJedis();
		
		try {
			return OK.equals(jedis.hmset(key, hash)) ? true : false;
		} finally {
			returnResource(jedis);
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
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hincrBy(key, field, value);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 名称为key的hash中是否存在键为field的域
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean hexists(String key, String field) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hexists(key, field);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 删除名称为key的hash中键为field的域
	 * @param key
	 * @param field
	 * @return
	 */
	public long hdel(String key, String field) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hdel(key, field);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中元素个数
	 * @param key
	 * @return
	 */
	public long hlen(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hlen(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中所有键
	 * @param key
	 * @return
	 */
	public Set<String> hkeys(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hkeys(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中所有键对应的value
	 * @param key
	 * @return
	 */
	public List<String> hvals(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hvals(key);
		} finally {
			returnResource(jedis);
		}
		
	}
	
	/**
	 * 返回名称为key的hash中所有的键（field）及其对应的value
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key) {
		
		Jedis jedis = getJedis();
		
		try {
			return jedis.hgetAll(key);
		} finally {
			returnResource(jedis);
		}
		
	}

	/**
	 * 获取集合中元素的数量
	 *
	 * @param String
	 *            key
	 * @return 如果返回0则集合不存在
	 * */
	public long zcard(String key) {
		//ShardedJedis sjedis = getShardedJedis();
		Jedis sjedis = getJedis();
		long len = sjedis.zcard(key);
		returnResource(sjedis);
		return len;
	}

	public Map<String, Object> runBatch(RedisBatchOper redisBatchOper) {
		Jedis jedis = getJedis();
		redisBatchOper.setJedis(jedis);
		redisBatchOper.run();
		returnResource(jedis);
		return redisBatchOper.getMap();
	}

}
