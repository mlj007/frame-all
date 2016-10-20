package com.chexun.generation.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chexun.generation.common.dao.GuidDao;
import com.chexun.generation.entity.GuidItem;

/**
 * 
 * @ClassName
 * @description
 * @author : 
 * @Create Date : 
 */
@Repository("guidDao")
public class GuidDaoImpl extends GenericDaoImpl  implements GuidDao {

    /**
     * 获得GuidItem列表
     */
    
    public List<GuidItem> getGuidItemByProject(String project) {
        return this.selectList("publicMapper.getsequence", project);
    }
    
    /**
     * 增加GuidItem
     */
    public void addGuidItem(GuidItem guidItem){
        this.insert("publicMapper.addsequence", guidItem);
    }
    
    /**
     * 更新GuidItem
     */
    public void updateGuidItem(GuidItem guidItem){
        this.update("publicMapper.updatesequence", guidItem);
    }
    
    
}
