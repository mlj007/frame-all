/**
 *  ClassName: RedisBatchOper.java
 *  created on 2013-5-15  下午03:42:16
 *  Copyrights 2011-2013 车讯网 All rights reserved.
 */
package com.chexun.base.cache.redis;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

/**
 * @author 安李勇
 *
 */
public abstract class RedisBatchOper {
	
	private Map<String, Object> map;
	
	private Jedis jedis;

	public RedisBatchOper() {
		map = new HashMap<String, Object>();
	}
	
	public RedisBatchOper(Map<String, Object> map) {
		this.map = map;
	}
	
	public Jedis getJedis() {
		return jedis;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public abstract void run();
	
}
