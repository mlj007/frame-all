package com.chexun.generation.common.dao;

import java.util.List;

import com.chexun.generation.entity.GuidItem;

/**
 * 
 * @ClassName 
 * @description
 * @author : 
 * @Create Date : 2014-3-12 下午7:37:29
 */
public interface GuidDao {
    /**
     * 根据字段获得唯一的id
     * @param project
     * @return
     */
    public List<GuidItem> getGuidItemByProject(String project);
    
    /**
     * 添加guidItem
     * @param guidItem
     * @return
     */
    public void addGuidItem(GuidItem guidItem);
    
    /**
     * 更新guidItem
     * @param guidItem
     * @return
     */
    public void updateGuidItem(GuidItem guidItem);
    
    
}
