package com.chexun.base.cache.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.chexun.base.cache.QueryProvider;
import com.chexun.base.cache.SessionCache;
import com.chexun.base.cache.redis.RedisPool;
import com.chexun.base.cache.redis.SerializeUtil;
import com.chexun.base.cache.util.Properties;
import com.chexun.base.cache.util.SerializableList;

public class CacheQueryProvider implements QueryProvider, InitializingBean {
	private static final String PREFIX_LIST = "LIST_";
	private static final String PREFIX_OBJ = "OBJ_";
	private static final String PREFIX_HASHMAP = "LIST_";
	private SessionCache sessionCache;
	private int cacheTimeout = 60;

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(sessionCache);
		String session = Properties.getProperties("project", "cacheTimeout");
		this.cacheTimeout = (session != null && !session.equals("")) ? Integer
				.parseInt(session) : 60;
	}

	public void setSessionCache(SessionCache sessionCache) {
		this.sessionCache = sessionCache;
	}

	/**
	 * 设置session过期时间
	 *
	 * @param sessionTimeout
	 *            分钟
	 */
	public void setCacheTimeout(int cacheTimeout) {
		Assert.isTrue(cacheTimeout > 0);
		this.cacheTimeout = cacheTimeout;
	}

	@Override
	public List<?> queryList(String module, String key) {
		Map<String, Serializable> queryCache = sessionCache.getSession(module);
		if (queryCache != null) {
			return (List<?>) queryCache.get(PREFIX_LIST + key);
		} else {
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setList(String module, String key, List<?> list,int expire) {
		Map<String, Serializable> queryCache = sessionCache.getSession(module);
		if (queryCache == null) {
			queryCache = new HashMap<String, Serializable>();
		}
		queryCache.put(PREFIX_LIST + key, new SerializableList(list));
		sessionCache.setSession(module, queryCache, expire);
	}

	@Override
	public Serializable queryObj(String module, String key) {
		Map<String, Serializable> queryCache = sessionCache.getSession(module);
		if (queryCache != null) {
			return (Serializable) queryCache.get(PREFIX_OBJ + key);
		} else {
			return null;
		}
	}

	@Override
	public void setObj(String module, String key, Serializable obj,int expire) {
		Map<String, Serializable> queryCache = sessionCache.getSession(module);
		if (queryCache == null) {
			queryCache = new HashMap<String, Serializable>();
		}
		queryCache.put(PREFIX_OBJ + key, obj);
		sessionCache.setSession(module, queryCache, expire);
	}

	@Override
	public void deleteObj(String module, String key) {
		Map<String, Serializable> queryCache = sessionCache.getSession(module);
		if (queryCache != null) {
			if(queryCache.get(PREFIX_OBJ + key)!=null) {
				queryCache.remove(PREFIX_OBJ + key);
			}
			sessionCache.setSession(module, queryCache, cacheTimeout);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Serializable> queryMap(String module, String key) {
		HashMap<String, Serializable> queryCache = (HashMap<String, Serializable>) sessionCache
				.getSession(module);
		if (queryCache != null) {
			return (HashMap<String, Serializable>) queryCache
					.get(PREFIX_HASHMAP + key);
		} else {
			return null;
		}
	}

	@Override
	public void setMap(String module, String key,
			HashMap<String, Serializable> map,int expire) {
		HashMap<String, Serializable> queryCache = (HashMap<String, Serializable>) sessionCache
				.getSession(module);
		if (queryCache == null) {
			queryCache = new HashMap<String, Serializable>();
		}
		queryCache.put(PREFIX_HASHMAP + key, map);
		sessionCache.setSession(module, queryCache, expire);
	}

	@Override
	public void clear(String module) {
		if (!StringUtils.isBlank(module)) {
			sessionCache.clear(module);
		}
	}

	
//	public void add(String root, String name, Serializable value, int exp) {
//		sessionCache.add(root, name, value, exp);
//	}
//	
//	public Object get(String root, String name) {
//		return sessionCache.get(root, name);
//	}
//
//	public void del(String root) {
//		sessionCache.del(root);
//	}
	
	public void addCount(String key, Integer count, int exp){
		sessionCache.addCount(key, count, exp);
	}
	
	public void set(String key, Serializable value, int exp){
		sessionCache.set(key, value, exp);
	}
	
	public Object get(String key){
		return sessionCache.get(key);
	}
}
