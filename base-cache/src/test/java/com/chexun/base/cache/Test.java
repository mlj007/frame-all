package com.chexun.base.cache;

import org.junit.Assert;

import com.chexun.base.cache.ICacheManager;
import com.chexun.base.cache.IMemcachedCache;
import com.chexun.base.cache.memcached.CacheUtil;
import com.chexun.base.cache.memcached.MemcachedCacheManager;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ICacheManager<IMemcachedCache> manager = CacheUtil.getCacheManager(
				IMemcachedCache.class, MemcachedCacheManager.class.getName());
		manager.setConfigFile("memcached.xml");
		manager.setResponseStatInterval(5 * 1000);
		manager.start();

		try {
			IMemcachedCache cache = manager.getCache("mclient1");

			cache.remove("key1");
			cache.remove("key2你好");

			cache.put("key1", "1");
			cache.put("key2你好", "你好123");

			System.out.println(cache.get("key1"));
			System.out.println(cache.get("key2你好"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
