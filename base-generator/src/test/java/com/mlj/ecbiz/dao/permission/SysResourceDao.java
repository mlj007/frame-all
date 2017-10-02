package com.mlj.ecbiz.dao.permission;
import java.util.List;
import com.mlj.ecbiz.model.permission.SysResource;
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * SysResource管理接口
 * User: 
 * Date: 2017-10-02
 */
public interface SysResourceDao {

    /**
     * 添加SysResource
     * @param sysResource 要添加的SysResource
     * @return id
     */
    public Long addSysResource(SysResource sysResource);
	public Long insertSysResource(SysResource sysResource);
    /**
     * 根据id删除一个SysResource
     * @param id 要删除的id
     */
    public Long deleteSysResourceById(Long id);
    
    public Long deleteSysResourceByObj(SysResource sysResource);

	public Long deleteSysResourceByIdList(List<Long > ids);

    /**
     * 修改SysResource
     * @param sysResource 要修改的SysResource
     */
    public Long updateSysResource(SysResource sysResource);
    
    public Long updateSysResourceByObj(SysResource sysResource);
    
    public Long updateSysResourceByObjAndConditions(SysResource s,SysResource c);
    public void batchUpdateSysResource(List<SysResource> records);

    /**
     * 根据id获取单个SysResource对象
     * @param id 要查询的id
     * @return SysResource
     */
    public SysResource getSysResourceById(Long id);
	public SysResource getSysResourceByObj(SysResource sysResource);
    /**
     * 根据条件获取SysResource列表
     * @param sysResource 查询条件
     * @return List<SysResource>
     */
    public List<SysResource> getSysResourceListByObj(SysResource sysResource);
    public List<SysResource> getSysResourceListPage(SysResource sysResource,Integer offset,Integer limit);
    public Integer getSysResourceCountByObj(SysResource sysResource);
    public List<SysResource> getSysResourcePage(SysResource sysResource,PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long getSysResourceIdByObj(SysResource sysResource);
    /**
     * 根据条件获取SysResource列表
     * @param sysResource 查询条件
     * @return List<SysResource>
     */
    public List<Long> getSysResourceIdList(SysResource sysResource);
    public List<Long> getSysResourceIdListByObj(SysResource sysResource);
    public List<Long> getSysResourceIdListPage(SysResource sysResource,Integer offset,Integer limit);
    public List<Long> getSysResourceIdPage(SysResource sysResource,PageEntity page);
}