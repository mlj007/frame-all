package com.mlj.ecbiz.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.dao.permission.SysResourceDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SysResource
 * User:
 * Date: 2017-10-02
 */
 @Repository("sysResourceDao")
public class SysResourceDaoImpl extends GenericDaoImpl implements SysResourceDao{

    public Long addSysResource(SysResource sysResource) {
        return this.insert("com.mlj.ecbiz.model.permission.SysResourceMapper.createSysResource",sysResource);
    }
	public Long insertSysResource(SysResource sysResource){
		return this.insert("com.mlj.ecbiz.model.permission.SysResourceMapper.insertSysResource",sysResource);
	}
    public Long deleteSysResourceById(Long id){
        return this.delete("com.mlj.ecbiz.model.permission.SysResourceMapper.deleteSysResourceById",id);
    }
    public Long deleteSysResourceByObj(SysResource sysResource){
        return this.delete("com.mlj.ecbiz.model.permission.SysResourceMapper.deleteSysResourceByObj",sysResource);
    }
    
	public Long deleteSysResourceByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysResourceMapper.deleteSysResourceByIdList",ids);
	}
    public Long updateSysResource(SysResource sysResource) {
        return this.update("com.mlj.ecbiz.model.permission.SysResourceMapper.updateSysResource",sysResource);
    }
    
    public Long updateSysResourceByObj(SysResource sysResource){
    	return this.update("com.mlj.ecbiz.model.permission.SysResourceMapper.updateSysResourceByObj",sysResource);
    }
    
    public Long updateSysResourceByObjAndConditions(SysResource s,SysResource c){
    	Map<String,SysResource> map = new HashMap<String,SysResource>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysResourceMapper.updateSysResourceByObjAndConditions",map);
    }
	public void batchUpdateSysResource(List<SysResource> records){
		this.update("com.mlj.ecbiz.model.permission.SysResourceMapper.batchUpdateSysResource",records);
	}
    public SysResource getSysResourceById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceById",id);
    }
    
    public SysResource getSysResourceByObj(SysResource sysResource) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceByObj",sysResource);
    }

    
    public List<SysResource> getSysResourceListByObj(SysResource sysResource){
        return this.selectList("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceListByObj",sysResource);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysResource> getSysResourceListPage(SysResource sysResource,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysResource);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysResourceCountByObj(SysResource sysResource){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceCountByObj", sysResource);
    }
    
    public List<SysResource> getSysResourcePage(SysResource sysResource,PageEntity page) {
        Integer objectscount = getSysResourceCountByObj(sysResource);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysResourceListPage(sysResource,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysResourceIdByObj(SysResource sysResource) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceIdByObj",sysResource);
    }

    public List<Long> getSysResourceIdList(SysResource sysResource) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceIdList",sysResource);
    }
    
    public List<Long> getSysResourceIdListByObj(SysResource sysResource){
        return this.selectList("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceIdListByObj",sysResource);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysResourceIdListPage(SysResource sysResource,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysResource);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysResourceMapper.getSysResourceIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysResourceIdPage(SysResource sysResource,PageEntity page) {
        Integer objectscount = getSysResourceCountByObj(sysResource);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysResourceIdListPage(sysResource,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
