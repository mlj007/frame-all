package com.chexun.sms.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.chexun.sms.model.permission.SendInfo;
import com.chexun.sms.dao.permission.SendInfoDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SendInfo
 * User:
 * Date: 2016-11-23
 */
 @Repository("sendInfoDao")
public class SendInfoDaoImpl extends GenericDaoImpl implements SendInfoDao{

    public Long addSendInfo(SendInfo sendInfo) {
        return this.insert("com.chexun.sms.model.permission.SendInfoMapper.createSendInfo",sendInfo);
    }
	public Long insertSendInfo(SendInfo sendInfo){
		return this.insert("com.chexun.sms.model.permission.SendInfoMapper.insertSendInfo",sendInfo);
	}
    public Long deleteSendInfoById(Long id){
        return this.delete("com.chexun.sms.model.permission.SendInfoMapper.deleteSendInfoById",id);
    }
    public Long deleteSendInfoByObj(SendInfo sendInfo){
        return this.delete("com.chexun.sms.model.permission.SendInfoMapper.deleteSendInfoByObj",sendInfo);
    }
    
	public Long deleteSendInfoByIdList(List<Long > ids){
		return this.delete("com.chexun.sms.model.permission.SendInfoMapper.deleteSendInfoByIdList",ids);
	}
    public Long updateSendInfo(SendInfo sendInfo) {
        return this.update("com.chexun.sms.model.permission.SendInfoMapper.updateSendInfo",sendInfo);
    }
    
    public Long updateSendInfoByObj(SendInfo sendInfo){
    	return this.update("com.chexun.sms.model.permission.SendInfoMapper.updateSendInfoByObj",sendInfo);
    }
    
    public Long updateSendInfoByObjAndConditions(SendInfo s,SendInfo c){
    	Map<String,SendInfo> map = new HashMap<String,SendInfo>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.chexun.sms.model.permission.SendInfoMapper.updateSendInfoByObjAndConditions",map);
    }
	public void batchUpdateSendInfo(List<SendInfo> records){
		this.update("com.chexun.sms.model.permission.SendInfoMapper.batchUpdateSendInfo",records);
	}
    public SendInfo getSendInfoById(Long id) {
        return this.selectOne("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoById",id);
    }
    
    public SendInfo getSendInfoByObj(SendInfo sendInfo) {
        return this.selectOne("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoByObj",sendInfo);
    }

    
    public List<SendInfo> getSendInfoListByObj(SendInfo sendInfo){
        return this.selectList("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoListByObj",sendInfo);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SendInfo> getSendInfoListPage(SendInfo sendInfo,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sendInfo);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSendInfoCountByObj(SendInfo sendInfo){
    	return this.selectOne("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoCountByObj", sendInfo);
    }
    
    public List<SendInfo> getSendInfoPage(SendInfo sendInfo,PageEntity page) {
        Integer objectscount = getSendInfoCountByObj(sendInfo);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSendInfoListPage(sendInfo,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSendInfoIdByObj(SendInfo sendInfo) {
        return this.selectOne("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoIdByObj",sendInfo);
    }

    public List<Long> getSendInfoIdList(SendInfo sendInfo) {
        return this.selectList("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoIdList",sendInfo);
    }
    
    public List<Long> getSendInfoIdListByObj(SendInfo sendInfo){
        return this.selectList("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoIdListByObj",sendInfo);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSendInfoIdListPage(SendInfo sendInfo,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sendInfo);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.chexun.sms.model.permission.SendInfoMapper.getSendInfoIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSendInfoIdPage(SendInfo sendInfo,PageEntity page) {
        Integer objectscount = getSendInfoCountByObj(sendInfo);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSendInfoIdListPage(sendInfo,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
