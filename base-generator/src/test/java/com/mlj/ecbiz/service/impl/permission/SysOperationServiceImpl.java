package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.dao.permission.SysOperationDao;
import com.mlj.ecbiz.service.permission.SysOperationService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysOperation管理接口
 * User: 
 * Date: 2017-10-02
 */
@Service("sysOperationService")
public class SysOperationServiceImpl implements SysOperationService{

 	@Autowired
    private SysOperationDao sysOperationDao;
    /**
     * 添加SysOperation
     * @param sysOperation 要添加的SysOperation
     * @return id
     */
    public Long addSysOperation(SysOperation sysOperation){
    	Long res = sysOperationDao.addSysOperation(sysOperation);
    	return res;
    }
	public Long insertSysOperation(SysOperation sysOperation){
		Long res = sysOperationDao.insertSysOperation(sysOperation);
		
    	return res;
	}
    /**
     * 根据id删除一个SysOperation
     * @param id 要删除的id
     */
    public Long deleteSysOperationById(Long id){
    	return sysOperationDao.deleteSysOperationById(id);
    }
	public Long deleteSysOperationByObj(SysOperation sysOperation){
        return sysOperationDao.deleteSysOperationByObj(sysOperation);
    }
    public Long deleteSysOperationByIdList(List<Long > ids){
    	
    	return sysOperationDao.deleteSysOperationByIdList(ids);
    }
    /**
     * 修改SysOperation
     * @param sysOperation 要修改的SysOperation
     */
    public Long updateSysOperation(SysOperation sysOperation){
     	return sysOperationDao.updateSysOperation(sysOperation);
    }
    
    public Long updateSysOperationByObj(SysOperation sysOperation){
    	return sysOperationDao.updateSysOperationByObj(sysOperation);
    }
    
    public Long updateSysOperationByObjAndConditions(SysOperation s,SysOperation c){
    	return sysOperationDao.updateSysOperationByObjAndConditions(s,c);
    }
	public void batchUpdateSysOperation(List<SysOperation> records){
		sysOperationDao.batchUpdateSysOperation(records);
	}
    /**
     * 根据id获取单个SysOperation对象
     * @param id 要查询的id
     * @return SysOperation
     */
    
    public Integer getSysOperationCountByObj(SysOperation sysOperation){
    	return sysOperationDao.getSysOperationCountByObj(sysOperation);
    }
    


    public SysOperation getSysOperationById(Long id){
    	return sysOperationDao.getSysOperationById( id);
    }
    
     public SysOperation getSysOperationByObj(SysOperation sysOperation) {
        return sysOperationDao.getSysOperationByObj(sysOperation);
    }


    
    public List<SysOperation> getSysOperationListByObj(SysOperation sysOperation){
        return sysOperationDao.getSysOperationListByObj(sysOperation);
    }
    public List<SysOperation> getSysOperationListPage(SysOperation sysOperation,Integer offset,Integer limit){
        return sysOperationDao.getSysOperationListPage(sysOperation,offset,limit);
    }
    
    public List<SysOperation> getSysOperationPage(SysOperation sysOperation,PageEntity page) {
        return sysOperationDao.getSysOperationPage(sysOperation,page);
    }
}