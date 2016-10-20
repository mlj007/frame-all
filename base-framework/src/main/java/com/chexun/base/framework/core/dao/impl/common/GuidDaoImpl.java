package com.chexun.base.framework.core.dao.impl.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chexun.base.framework.core.dao.common.GuidDao;
import com.chexun.base.framework.core.entity.GuidItem;

/**
 * 
 * @ClassName  net.sns.framework.core.dao.impl.common.GuidDaoImpl
 * @description
 * @author : spencer
 * @Create Date : 2013-12-18 下午7:37:25
 */
@Repository("guidDao")
public class GuidDaoImpl extends GenericDaoImpl  implements GuidDao {

    /**
     * 获得GuidItem列表
     */
    @Override
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
