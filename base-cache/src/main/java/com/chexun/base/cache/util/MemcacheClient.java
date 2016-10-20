package com.chexun.base.cache.util;

import com.chexun.base.cache.ICacheManager;
import com.chexun.base.cache.IMemcachedCache;
import com.chexun.base.cache.memcached.CacheUtil;
import com.chexun.base.cache.memcached.MemcachedCacheManager;

public class MemcacheClient {
	public static final String PROPERTIES_CONFIG_NAME = "project";

	/**
	 * config
	 */
	private final static String DEFAULT_CONFIG = "memcached.xml";
	/**
	 * config_key
	 */
	private final static String CONFIG_KEY = "configuration";
	/**
	 * default client name
	 */
	private static final String DEFAULT_MCLIENT_NAME = "mclient1";
	/**
	 * config key
	 */
	private static final String MCLIENT_KEY = "client";
	/**
	 * 
	 */
	private static ICacheManager<IMemcachedCache> cacheManager;

	/**
	 * 是否初始化
	 */
	private static volatile boolean isInit = false;

	/**
	 * init configuration
	 */
	public static void init() {
		cacheManager = CacheUtil.getCacheManager(IMemcachedCache.class,
				MemcachedCacheManager.class.getName());
		String config = Properties.getProperties(PROPERTIES_CONFIG_NAME,
				CONFIG_KEY);
		cacheManager.setConfigFile(config == null ? DEFAULT_CONFIG : config);
		cacheManager.start();
		isInit = true;
	}

	/**
	 * 
	 * 获取cache
	 * 
	 * @return:IMemcachedCache
	 */
	public final static IMemcachedCache getCache() {
		if (!isInit) {
			init();
		}
		String client = Properties.getProperties(PROPERTIES_CONFIG_NAME,
				MCLIENT_KEY);
		return cacheManager.getCache(client == null ? DEFAULT_MCLIENT_NAME
				: client);
	}
}
