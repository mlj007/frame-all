package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.dao.permission.SysResourceDao;
import com.mlj.ecbiz.service.permission.SysResourceService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysResource管理接口
 * User: 
 * Date: 2017-10-02
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService{

 	@Autowired
    private SysResourceDao sysResourceDao;
    /**
     * 添加SysResource
     * @param sysResource 要添加的SysResource
     * @return id
     */
    public Long addSysResource(SysResource sysResource){
    	Long res = sysResourceDao.addSysResource(sysResource);
    	return res;
    }
	public Long insertSysResource(SysResource sysResource){
		Long res = sysResourceDao.insertSysResource(sysResource);
		
    	return res;
	}
    /**
     * 根据id删除一个SysResource
     * @param id 要删除的id
     */
    public Long deleteSysResourceById(Long id){
    	return sysResourceDao.deleteSysResourceById(id);
    }
	public Long deleteSysResourceByObj(SysResource sysResource){
        return sysResourceDao.deleteSysResourceByObj(sysResource);
    }
    public Long deleteSysResourceByIdList(List<Long > ids){
    	
    	return sysResourceDao.deleteSysResourceByIdList(ids);
    }
    /**
     * 修改SysResource
     * @param sysResource 要修改的SysResource
     */
    public Long updateSysResource(SysResource sysResource){
     	return sysResourceDao.updateSysResource(sysResource);
    }
    
    public Long updateSysResourceByObj(SysResource sysResource){
    	return sysResourceDao.updateSysResourceByObj(sysResource);
    }
    
    public Long updateSysResourceByObjAndConditions(SysResource s,SysResource c){
    	return sysResourceDao.updateSysResourceByObjAndConditions(s,c);
    }
	public void batchUpdateSysResource(List<SysResource> records){
		sysResourceDao.batchUpdateSysResource(records);
	}
    /**
     * 根据id获取单个SysResource对象
     * @param id 要查询的id
     * @return SysResource
     */
    
    public Integer getSysResourceCountByObj(SysResource sysResource){
    	return sysResourceDao.getSysResourceCountByObj(sysResource);
    }
    


    public SysResource getSysResourceById(Long id){
    	return sysResourceDao.getSysResourceById( id);
    }
    
     public SysResource getSysResourceByObj(SysResource sysResource) {
        return sysResourceDao.getSysResourceByObj(sysResource);
    }


    
    public List<SysResource> getSysResourceListByObj(SysResource sysResource){
        return sysResourceDao.getSysResourceListByObj(sysResource);
    }
    public List<SysResource> getSysResourceListPage(SysResource sysResource,Integer offset,Integer limit){
        return sysResourceDao.getSysResourceListPage(sysResource,offset,limit);
    }
    
    public List<SysResource> getSysResourcePage(SysResource sysResource,PageEntity page) {
        return sysResourceDao.getSysResourcePage(sysResource,page);
    }
}