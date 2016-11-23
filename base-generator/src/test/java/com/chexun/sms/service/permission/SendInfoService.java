package com.chexun.sms.service.permission;

import java.util.List;
import com.chexun.sms.model.permission.SendInfo;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * SendInfo管理接口
 * User: 
 * Date: 2016-11-23
 */
public interface SendInfoService {

    /**
     * 添加SendInfo
     * @param sendInfo 要添加的SendInfo
     * @return id
     */
    public Long addSendInfo(SendInfo sendInfo);
	public Long insertSendInfo(SendInfo sendInfo);
    /**
     * 根据id删除一个SendInfo
     * @param id 要删除的id
     */
    public Long deleteSendInfoById(Long id);
	public Long deleteSendInfoByObj(SendInfo sendInfo);
    public Long deleteSendInfoByIdList(List<Long > ids);
    /**
     * 修改SendInfo
     * @param sendInfo 要修改的SendInfo
     */
    public Long updateSendInfo(SendInfo sendInfo);
    public Long updateSendInfoByObj(SendInfo sendInfo);
    public Long updateSendInfoByObjAndConditions(SendInfo s,SendInfo c);
	public void batchUpdateSendInfo(List<SendInfo> records);
    /**
     * 根据id获取单个SendInfo对象
     * @param id 要查询的id
     * @return SendInfo
     */
    public SendInfo getSendInfoById(Long id);
	public SendInfo getSendInfoByObj(SendInfo sendInfo);
    /**
     * 根据条件获取SendInfo列表
     * @param sendInfo 查询条件
     * @return List<SendInfo>
     */
    public List<SendInfo> getSendInfoListByObj(SendInfo sendInfo);
    public List<SendInfo> getSendInfoListPage(SendInfo sendInfo,Integer offset,Integer limit);
    public Integer getSendInfoCountByObj(SendInfo sendInfo);
    public List<SendInfo> getSendInfoPage(SendInfo sendInfo,PageEntity page);
    
}