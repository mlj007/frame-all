package com.chexun.base.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface QueryProvider {

	public List<?> queryList(String module, String key);

	public void setList(String module, String key, List<?> list,int expire);
	
	public Serializable queryObj(String module, String key);

	public void setObj(String module, String key, Serializable obj,int expire);
	
	public void deleteObj(String module, String key);
	
	public HashMap<String,Serializable> queryMap(String module, String key);

	public void setMap(String module, String key,  HashMap<String,Serializable> map,int expire);

	public void clear(String module);
	
//	public void add(String root, String name, Serializable value, int exp);
//	
//	public Object get(String root, String name);
//
//	public void del(String root);
	
	public void addCount(String key, Integer count, int exp);
	
	public void set(String key, Serializable value, int exp);
	
	public Object get(String key);

}
