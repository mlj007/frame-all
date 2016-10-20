package com.chexun.generation.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chexun.generation.common.dao.GenericDao;
import com.chexun.generation.entity.PageEntity;
import com.chexun.generation.entity.PageOL;
import com.chexun.generation.util.ObjectUtils;

/**
 * 
 * @ClassName GenericDaoImpl
 * @package 
 * @description 数据库基类 供其他dao继承
 * @author spencer
 * @Create Date: 2014-3-12 上午11:05:17
 * 
 */
@Repository("genericDao")
public abstract class GenericDaoImpl implements GenericDao {

    public SqlSession sqlSession;

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    @Resource(name = "sqlSessionMain")
    public void setSqlSession1(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    public Long insert(String sqlKey, Object object) {
        return Long.valueOf(this.getSqlSession().insert(sqlKey, object));
    }

    
    public Long delete(String sqlKey, Object object) {
        return Long.valueOf(this.getSqlSession().delete(sqlKey, object));
    }
    
    public Long update(String key, Object object) {
        return Long.valueOf(getSqlSession().update(key, object));
    }

    
    public <T> T selectOne(String sqlKey, Object params) {
        T selectOne =null;
        List<T> list= selectList(sqlKey, params);
        if(!ObjectUtils.isNull(list)){
            selectOne =  list.get(0);
        }
        return selectOne;
    }
    
    public <T> List<T> selectList(String sqlKey, Object params) {
        return this.getSqlSession().selectList(sqlKey, params);
    }

    /**
     * 分页查询时使用
     * 
     * @return
     */
    
    public <T> List<T> queryForListPage(String sqlKey, Object params,
            PageEntity page) {

        /**
         * 分页时需要2个sql。在正常sql后面加pageCount为计算count的sql
         * 如：customre.getcustomreByTime必须命名为customre.getcustomreByTimeCount
         */

        // 查询总行数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("e", params);
        PageOL pageOL = new PageOL();
        pageOL.setOffsetPara((page.getCurrentPage() - 1) * page.getPageSize());
        pageOL.setLimitPara(page.getPageSize());
        map.put("page", pageOL);

        Integer objectscount = this.selectOne(sqlKey + "Count", map);

        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1)
                    / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1)
                    / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return this.selectList(sqlKey, map);
        }

    }

}
