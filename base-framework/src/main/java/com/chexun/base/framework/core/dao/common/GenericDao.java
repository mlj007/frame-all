package com.chexun.base.framework.core.dao.common;
import java.util.List;

import com.chexun.base.framework.core.entity.PageEntity;
/**
 * 
 * @ClassName  GenericDao
 * @package net.sns.framework.core.common.dao
 * @description
 * @author  spencer
 * @Create Date: 2013-5-25 下午5:37:47
 *
 */
public interface GenericDao {
	
	public Long insert(String sqlKey, Object object) ;

	public Long delete(String sqlKey, Object object) ;

	public Long update(String key, Object object);

	public <T> T selectOne(String sqlKey, Object params);
	
	public <T> T selectOneFromWriter(String sqlKey, Object params);

	public <T> List<T> selectList(String sqlKey, Object params);
	
	//分页代码
	public <T> List<T> queryForListPage(String sqlKey, Object params, PageEntity page) ;
	
}
