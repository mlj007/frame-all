package com.mlj.ecbiz.dao.permission;
import java.util.List;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * SysOperation管理接口
 * User: 
 * Date: 2017-10-02
 */
public interface SysOperationDao {

    /**
     * 添加SysOperation
     * @param sysOperation 要添加的SysOperation
     * @return id
     */
    public Long addSysOperation(SysOperation sysOperation);
	public Long insertSysOperation(SysOperation sysOperation);
    /**
     * 根据id删除一个SysOperation
     * @param id 要删除的id
     */
    public Long deleteSysOperationById(Long id);
    
    public Long deleteSysOperationByObj(SysOperation sysOperation);

	public Long deleteSysOperationByIdList(List<Long > ids);

    /**
     * 修改SysOperation
     * @param sysOperation 要修改的SysOperation
     */
    public Long updateSysOperation(SysOperation sysOperation);
    
    public Long updateSysOperationByObj(SysOperation sysOperation);
    
    public Long updateSysOperationByObjAndConditions(SysOperation s,SysOperation c);
    public void batchUpdateSysOperation(List<SysOperation> records);

    /**
     * 根据id获取单个SysOperation对象
     * @param id 要查询的id
     * @return SysOperation
     */
    public SysOperation getSysOperationById(Long id);
	public SysOperation getSysOperationByObj(SysOperation sysOperation);
    /**
     * 根据条件获取SysOperation列表
     * @param sysOperation 查询条件
     * @return List<SysOperation>
     */
    public List<SysOperation> getSysOperationListByObj(SysOperation sysOperation);
    public List<SysOperation> getSysOperationListPage(SysOperation sysOperation,Integer offset,Integer limit);
    public Integer getSysOperationCountByObj(SysOperation sysOperation);
    public List<SysOperation> getSysOperationPage(SysOperation sysOperation,PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long getSysOperationIdByObj(SysOperation sysOperation);
    /**
     * 根据条件获取SysOperation列表
     * @param sysOperation 查询条件
     * @return List<SysOperation>
     */
    public List<Long> getSysOperationIdList(SysOperation sysOperation);
    public List<Long> getSysOperationIdListByObj(SysOperation sysOperation);
    public List<Long> getSysOperationIdListPage(SysOperation sysOperation,Integer offset,Integer limit);
    public List<Long> getSysOperationIdPage(SysOperation sysOperation,PageEntity page);
}