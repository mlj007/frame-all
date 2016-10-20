package com.chexun.base.cache.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.chexun.base.cache.IMemcachedCache;
import com.chexun.base.cache.SessionCache;
import com.chexun.base.cache.util.MemcacheClient;

public class MemcachedDangaCache implements SessionCache, InitializingBean {
	private IMemcachedCache client;

	@SuppressWarnings("unchecked")
	public HashMap<String, Serializable> getSession(String root) {
		return (HashMap<String, Serializable>) client.get(root);
	}

	public void setSession(String root, Map<String, Serializable> session, int exp) {
		client.put(root, session, new Date(System.currentTimeMillis() + exp * 60 * 1000));

	}

	public Serializable getAttribute(String root, String name) {
		HashMap<String, Serializable> session = getSession(root);
		return session != null ? session.get(name) : null;
	}

	public void setAttribute(String root, String name, Serializable value, int exp) {
		HashMap<String, Serializable> session = getSession(root);
		if (session == null) {
			session = new HashMap<String, Serializable>();
		}
		session.put(name, value);
		Date expDate = new Date(System.currentTimeMillis() + exp * 60 * 1000);
		client.put(root, session, expDate);
	}

	public void clear(String root) {
		client.remove(root);
	}

	public boolean exist(String root) {
		return client.get(root) != null;
	}

	public void afterPropertiesSet() throws Exception {
		client = MemcacheClient.getCache();
	}

	
	
	public void addCount(String key, Integer count, int exp){
		
	}
	
	public void set(String key, Serializable value, int exp){}
	
	public Object get(String key){
		return null;
	}
}
