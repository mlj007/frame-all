package com.chexun.base.cache;

import java.io.Serializable;
import java.util.Map;

import com.chexun.base.cache.redis.RedisPool;
import com.chexun.base.cache.redis.SerializeUtil;

public interface SessionCache {
	public Serializable getAttribute(String root, String name);

	public void setAttribute(String root, String name, Serializable value, int exp);

	public void clear(String root);

	public boolean exist(String root);

	public Map<String, Serializable> getSession(String root);

	public void setSession(String root, Map<String, Serializable> session, int exp);
	
//	public void add(String root, String name, Serializable value, int exp);
//	public Object get(String root, String name);
//	public void del(String root);
	
	
	public void addCount(String key, Integer count, int exp);
	
	public void set(String key, Serializable value, int exp);
	
	public Object get(String key);
}
