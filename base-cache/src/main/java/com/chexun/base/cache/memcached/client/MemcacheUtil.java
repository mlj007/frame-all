package com.chexun.base.cache.memcached.client;

import com.chexun.base.cache.ICacheManager;
import com.chexun.base.cache.IMemcachedCache;
import com.chexun.base.cache.memcached.CacheUtil;
import com.chexun.base.cache.memcached.MemcachedCacheManager;

public class MemcacheUtil {

	private final static String DEFAULT_CONFIG = "memcached.xml";

	private static ICacheManager<IMemcachedCache> manager;

	private static final String DEFAULT_MCLIENT_NAME = "mclient1";

	@SuppressWarnings("rawtypes")
	private static ICacheManager getManager() {

		// 初始化缓存管理
		if (manager == null) {
			manager = CacheUtil.getCacheManager(IMemcachedCache.class, MemcachedCacheManager.class.getName());
			manager.setConfigFile(DEFAULT_CONFIG);
			manager.start();
		}
		return manager;
	}

	@SuppressWarnings("static-access")
	public void setManager(ICacheManager<IMemcachedCache> manager) {
		this.manager = manager;
	}

	@SuppressWarnings("unused")
	public static IMemcachedCache getCacheInstatce(String cachename) {

		if (manager == null) {
			getManager();
		}

		IMemcachedCache memcache = manager.getCache(cachename);

		return manager.getCache(cachename == null ? DEFAULT_MCLIENT_NAME : cachename);

	}

}
