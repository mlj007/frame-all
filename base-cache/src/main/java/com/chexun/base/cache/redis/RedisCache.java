package com.chexun.base.cache.redis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.ShardedJedis;

import com.chexun.base.cache.SessionCache;

public class RedisCache implements SessionCache, InitializingBean {
	//ShardedJedis jedis;
	private static RedisFactory redisFactory;
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Serializable> getSession(String root) {
		//jedis = RedisPool.getResource();
		//byte[] data = jedis.get(SerializeUtil.serialize(root));
		byte[] data = redisFactory.getRedisOperator().get(SerializeUtil.serialize(root));
		HashMap<String, Serializable> session = (HashMap<String, Serializable>) SerializeUtil.unserialize(data);
		return session;
	}

	public void setSession(String root, Map<String, Serializable> session, int exp) {
		//System.out.println("----test in RedisCache.setSession root:" + root + ",exp:" + exp);
		//jedis = RedisPool.getResource();
		//jedis.set(SerializeUtil.serialize(root), SerializeUtil.serialize(session));
		redisFactory.getRedisOperator().setex(SerializeUtil.serialize(root), exp * 60, SerializeUtil.serialize(session));
		//jedis.expire(root, exp * 60);
	}

	public Serializable getAttribute(String root, String name) {
		HashMap<String, Serializable> session = getSession(root);
		return session != null ? session.get(name) : null;
	}

	public void setAttribute(String root, String name, Serializable value, int exp) {
		//jedis = RedisPool.getResource();
		HashMap<String, Serializable> session = getSession(root);
		if (session == null) {
			session = new HashMap<String, Serializable>();
		}
		session.put(name, value);
		//jedis.set(SerializeUtil.serialize(root), SerializeUtil.serialize(session));
		//jedis.expire(root, exp * 60);
		redisFactory.getRedisOperator().setex(SerializeUtil.serialize(root), exp * 60, SerializeUtil.serialize(session));
	}

	public void clear(String root) {
		//jedis = RedisPool.getResource();
		//jedis.del(SerializeUtil.serialize(root));
		redisFactory.getRedisOperator().del2(root);
	}

	public boolean exist(String root) {
		//jedis = RedisPool.getResource();
		//boolean exist = jedis.get(root) != null;
		boolean exist = redisFactory.getRedisOperator().get(root) != null;
		return exist;
	}

	public void afterPropertiesSet() throws Exception {
		//jedis = RedisPool.getResource();
		//jedis = redisFactory.getRedisOperator().getJedis();
	}
	
//	public void add(String root, String name, Serializable value, int exp) {
//		jedis = RedisPool.getResource();
//		jedis.set(SerializeUtil.serialize(root+name), SerializeUtil.serialize(value));
//		jedis.expire(root, exp * 60);
//		try{
//			RedisPool.returnResource(jedis);
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
//	
//	public Object get(String root, String name) {
//		jedis = RedisPool.getResource();
//		byte[] ff = jedis.get(SerializeUtil.serialize(root+name));
//		Object o = SerializeUtil.unserialize(ff);
//		try{
//			RedisPool.returnResource(jedis);
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return o;
//	}
//
//	public void del(String root) {
//		jedis = RedisPool.getResource();
//		jedis.del(root);
//		try{
//			RedisPool.returnResource(jedis);
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
	
	public void addCount(String key, Integer count, int exp) {
		if(count == null ){
			return;
		}
		//jedis = RedisPool.getResource();
		
		Object o = get(key);
		Integer v = null;
		if(o != null){
			v = (Integer)o;
			v += count;
		}else{
			v = count;
		}
		//jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(v));
		//jedis.expire(key, exp * 60);
		redisFactory.getRedisOperator().setex(SerializeUtil.serialize(key), exp * 60, SerializeUtil.serialize(v));
		/*try{
			//RedisPool.returnResource(jedis);
		}catch(Exception ex){
			ex.printStackTrace();
		}*/
	}
	
	public void set(String key, Serializable value, int exp) {
		//jedis = RedisPool.getResource();
		//jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
		//jedis.expire(key, exp * 60);
		redisFactory.getRedisOperator().setex(SerializeUtil.serialize(key), exp * 60, SerializeUtil.serialize(value));
	}
	
	public Object get(String key) {
		//jedis = RedisPool.getResource();
		//byte[] ff = jedis.get(SerializeUtil.serialize(key));
		byte[] ff = redisFactory.getRedisOperator().get(SerializeUtil.serialize(key));
		Object o = SerializeUtil.unserialize(ff);
		return o;
	}
	
	public static void setRedisFactory(RedisFactory redisFactory) {
		RedisCache.redisFactory = redisFactory;
	}
	
}
