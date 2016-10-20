package com.chexun.generation.common.dao;
import java.util.List;

import com.chexun.generation.entity.PageEntity;
/**
 * 
 * @ClassName  GenericDao
 * @package 
 * @description
 * @author  spencer
 * @Create Date: 2014-3-13 下午5:37:47
 *
 */
public interface GenericDao {
	
	public Long insert(String sqlKey, Object object) ;

	public Long delete(String sqlKey, Object object) ;

	public Long update(String key, Object object);

	public <T> T selectOne(String sqlKey, Object params);

	public <T> List<T> selectList(String sqlKey, Object params);
	
	//分页代码
	public <T> List<T> queryForListPage(String sqlKey, Object params, PageEntity page) ;
	
}
