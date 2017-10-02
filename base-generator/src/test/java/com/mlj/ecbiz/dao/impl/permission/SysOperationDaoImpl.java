package com.mlj.ecbiz.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.dao.permission.SysOperationDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SysOperation
 * User:
 * Date: 2017-10-02
 */
 @Repository("sysOperationDao")
public class SysOperationDaoImpl extends GenericDaoImpl implements SysOperationDao{

    public Long addSysOperation(SysOperation sysOperation) {
        return this.insert("com.mlj.ecbiz.model.permission.SysOperationMapper.createSysOperation",sysOperation);
    }
	public Long insertSysOperation(SysOperation sysOperation){
		return this.insert("com.mlj.ecbiz.model.permission.SysOperationMapper.insertSysOperation",sysOperation);
	}
    public Long deleteSysOperationById(Long id){
        return this.delete("com.mlj.ecbiz.model.permission.SysOperationMapper.deleteSysOperationById",id);
    }
    public Long deleteSysOperationByObj(SysOperation sysOperation){
        return this.delete("com.mlj.ecbiz.model.permission.SysOperationMapper.deleteSysOperationByObj",sysOperation);
    }
    
	public Long deleteSysOperationByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysOperationMapper.deleteSysOperationByIdList",ids);
	}
    public Long updateSysOperation(SysOperation sysOperation) {
        return this.update("com.mlj.ecbiz.model.permission.SysOperationMapper.updateSysOperation",sysOperation);
    }
    
    public Long updateSysOperationByObj(SysOperation sysOperation){
    	return this.update("com.mlj.ecbiz.model.permission.SysOperationMapper.updateSysOperationByObj",sysOperation);
    }
    
    public Long updateSysOperationByObjAndConditions(SysOperation s,SysOperation c){
    	Map<String,SysOperation> map = new HashMap<String,SysOperation>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysOperationMapper.updateSysOperationByObjAndConditions",map);
    }
	public void batchUpdateSysOperation(List<SysOperation> records){
		this.update("com.mlj.ecbiz.model.permission.SysOperationMapper.batchUpdateSysOperation",records);
	}
    public SysOperation getSysOperationById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationById",id);
    }
    
    public SysOperation getSysOperationByObj(SysOperation sysOperation) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationByObj",sysOperation);
    }

    
    public List<SysOperation> getSysOperationListByObj(SysOperation sysOperation){
        return this.selectList("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationListByObj",sysOperation);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysOperation> getSysOperationListPage(SysOperation sysOperation,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysOperation);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysOperationCountByObj(SysOperation sysOperation){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationCountByObj", sysOperation);
    }
    
    public List<SysOperation> getSysOperationPage(SysOperation sysOperation,PageEntity page) {
        Integer objectscount = getSysOperationCountByObj(sysOperation);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysOperationListPage(sysOperation,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysOperationIdByObj(SysOperation sysOperation) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationIdByObj",sysOperation);
    }

    public List<Long> getSysOperationIdList(SysOperation sysOperation) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationIdList",sysOperation);
    }
    
    public List<Long> getSysOperationIdListByObj(SysOperation sysOperation){
        return this.selectList("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationIdListByObj",sysOperation);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysOperationIdListPage(SysOperation sysOperation,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysOperation);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysOperationMapper.getSysOperationIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysOperationIdPage(SysOperation sysOperation,PageEntity page) {
        Integer objectscount = getSysOperationCountByObj(sysOperation);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysOperationIdListPage(sysOperation,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
