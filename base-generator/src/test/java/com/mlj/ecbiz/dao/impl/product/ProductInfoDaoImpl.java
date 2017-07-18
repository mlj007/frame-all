package com.mlj.ecbiz.dao.impl.product;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.product.ProductInfo;
import com.mlj.ecbiz.dao.product.ProductInfoDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * ProductInfo
 * User:
 * Date: 2017-07-13
 */
 @Repository("productInfoDao")
public class ProductInfoDaoImpl extends GenericDaoImpl implements ProductInfoDao{

    public Long addProductInfo(ProductInfo productInfo) {
        return this.insert("com.mlj.ecbiz.model.product.ProductInfoMapper.createProductInfo",productInfo);
    }
	public Long insertProductInfo(ProductInfo productInfo){
		return this.insert("com.mlj.ecbiz.model.product.ProductInfoMapper.insertProductInfo",productInfo);
	}
    public Long deleteProductInfoById(Long id){
        return this.delete("com.mlj.ecbiz.model.product.ProductInfoMapper.deleteProductInfoById",id);
    }
    public Long deleteProductInfoByObj(ProductInfo productInfo){
        return this.delete("com.mlj.ecbiz.model.product.ProductInfoMapper.deleteProductInfoByObj",productInfo);
    }
    
	public Long deleteProductInfoByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.product.ProductInfoMapper.deleteProductInfoByIdList",ids);
	}
    public Long updateProductInfo(ProductInfo productInfo) {
        return this.update("com.mlj.ecbiz.model.product.ProductInfoMapper.updateProductInfo",productInfo);
    }
    
    public Long updateProductInfoByObj(ProductInfo productInfo){
    	return this.update("com.mlj.ecbiz.model.product.ProductInfoMapper.updateProductInfoByObj",productInfo);
    }
    
    public Long updateProductInfoByObjAndConditions(ProductInfo s,ProductInfo c){
    	Map<String,ProductInfo> map = new HashMap<String,ProductInfo>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.product.ProductInfoMapper.updateProductInfoByObjAndConditions",map);
    }
	public void batchUpdateProductInfo(List<ProductInfo> records){
		this.update("com.mlj.ecbiz.model.product.ProductInfoMapper.batchUpdateProductInfo",records);
	}
    public ProductInfo getProductInfoById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoById",id);
    }
    
    public ProductInfo getProductInfoByObj(ProductInfo productInfo) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoByObj",productInfo);
    }

    
    public List<ProductInfo> getProductInfoListByObj(ProductInfo productInfo){
        return this.selectList("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoListByObj",productInfo);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<ProductInfo> getProductInfoListPage(ProductInfo productInfo,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productInfo);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getProductInfoCountByObj(ProductInfo productInfo){
    	return this.selectOne("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoCountByObj", productInfo);
    }
    
    public List<ProductInfo> getProductInfoPage(ProductInfo productInfo,PageEntity page) {
        Integer objectscount = getProductInfoCountByObj(productInfo);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductInfoListPage(productInfo,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getProductInfoIdByObj(ProductInfo productInfo) {
        return this.selectOne("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoIdByObj",productInfo);
    }

    public List<Long> getProductInfoIdList(ProductInfo productInfo) {
        return this.selectList("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoIdList",productInfo);
    }
    
    public List<Long> getProductInfoIdListByObj(ProductInfo productInfo){
        return this.selectList("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoIdListByObj",productInfo);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getProductInfoIdListPage(ProductInfo productInfo,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(productInfo);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.product.ProductInfoMapper.getProductInfoIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getProductInfoIdPage(ProductInfo productInfo,PageEntity page) {
        Integer objectscount = getProductInfoCountByObj(productInfo);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getProductInfoIdListPage(productInfo,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
