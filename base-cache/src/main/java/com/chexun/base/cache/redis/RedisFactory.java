/**
 *  ClassName: RedisFactory.java
 *  created on 2013-4-19  下午01:12:30
 *  Copyrights 2011-2013 车讯网 All rights reserved.
 */
package com.chexun.base.cache.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author 安李勇
 * 
 */
public class RedisFactory {

	private ShardedJedisPool shardedJedisPool;
	
	private JedisPool jedisPool;
	
	private ShardedRedisOperator shardedRedisOperator;
	
	private RedisOperator redisOperator;
	
	private boolean enable = true;

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public ShardedRedisOperator getShardedRedisOperator() {
		if(shardedRedisOperator == null) {
			shardedRedisOperator = new ShardedRedisOperator(shardedJedisPool);
		}
		return shardedRedisOperator;
	}

	public void setShardedRedisOperator(ShardedRedisOperator shardedRedisOperator) {
		this.shardedRedisOperator = shardedRedisOperator;
	}

	public RedisOperator getRedisOperator() {
		if(redisOperator == null) {
			redisOperator = new RedisOperator(jedisPool);
		}
		return redisOperator;
	}

	public void setRedisOperator(RedisOperator redisOperator) {
		this.redisOperator = redisOperator;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
