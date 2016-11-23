package com.chexun.sms.service.impl.permission;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chexun.sms.model.permission.SendInfo;
import com.chexun.sms.dao.permission.SendInfoDao;
import com.chexun.sms.service.permission.SendInfoService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SendInfo管理接口
 * User: 
 * Date: 2016-11-23
 */
@Service("sendInfoService")
public class SendInfoServiceImpl implements SendInfoService{

 	@Autowired
    private SendInfoDao sendInfoDao;
    /**
     * 添加SendInfo
     * @param sendInfo 要添加的SendInfo
     * @return id
     */
    public Long addSendInfo(SendInfo sendInfo){
    	Long res = sendInfoDao.addSendInfo(sendInfo);
    	return res;
    }
	public Long insertSendInfo(SendInfo sendInfo){
		Long res = sendInfoDao.insertSendInfo(sendInfo);
		
    	return res;
	}
    /**
     * 根据id删除一个SendInfo
     * @param id 要删除的id
     */
    public Long deleteSendInfoById(Long id){
    	return sendInfoDao.deleteSendInfoById(id);
    }
	public Long deleteSendInfoByObj(SendInfo sendInfo){
        return sendInfoDao.deleteSendInfoByObj(sendInfo);
    }
    public Long deleteSendInfoByIdList(List<Long > ids){
    	
    	return sendInfoDao.deleteSendInfoByIdList(ids);
    }
    /**
     * 修改SendInfo
     * @param sendInfo 要修改的SendInfo
     */
    public Long updateSendInfo(SendInfo sendInfo){
     	return sendInfoDao.updateSendInfo(sendInfo);
    }
    
    public Long updateSendInfoByObj(SendInfo sendInfo){
    	return sendInfoDao.updateSendInfoByObj(sendInfo);
    }
    
    public Long updateSendInfoByObjAndConditions(SendInfo s,SendInfo c){
    	return sendInfoDao.updateSendInfoByObjAndConditions(s,c);
    }
	public void batchUpdateSendInfo(List<SendInfo> records){
		sendInfoDao.batchUpdateSendInfo(records);
	}
    /**
     * 根据id获取单个SendInfo对象
     * @param id 要查询的id
     * @return SendInfo
     */
    
    public Integer getSendInfoCountByObj(SendInfo sendInfo){
    	return sendInfoDao.getSendInfoCountByObj(sendInfo);
    }
    


    public SendInfo getSendInfoById(Long id){
    	return sendInfoDao.getSendInfoById( id);
    }
    
     public SendInfo getSendInfoByObj(SendInfo sendInfo) {
        return sendInfoDao.getSendInfoByObj(sendInfo);
    }


    
    public List<SendInfo> getSendInfoListByObj(SendInfo sendInfo){
        return sendInfoDao.getSendInfoListByObj(sendInfo);
    }
    public List<SendInfo> getSendInfoListPage(SendInfo sendInfo,Integer offset,Integer limit){
        return sendInfoDao.getSendInfoListPage(sendInfo,offset,limit);
    }
    
    public List<SendInfo> getSendInfoPage(SendInfo sendInfo,PageEntity page) {
        return sendInfoDao.getSendInfoPage(sendInfo,page);
    }
}