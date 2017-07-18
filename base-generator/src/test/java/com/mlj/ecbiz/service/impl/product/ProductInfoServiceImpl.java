package com.mlj.ecbiz.service.impl.product;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.product.ProductInfo;
import com.mlj.ecbiz.dao.product.ProductInfoDao;
import com.mlj.ecbiz.service.product.ProductInfoService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * ProductInfo管理接口
 * User: 
 * Date: 2017-07-13
 */
@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService{

 	@Autowired
    private ProductInfoDao productInfoDao;
    /**
     * 添加ProductInfo
     * @param productInfo 要添加的ProductInfo
     * @return id
     */
    public Long addProductInfo(ProductInfo productInfo){
    	Long res = productInfoDao.addProductInfo(productInfo);
    	return res;
    }
	public Long insertProductInfo(ProductInfo productInfo){
		Long res = productInfoDao.insertProductInfo(productInfo);
		
    	return res;
	}
    /**
     * 根据id删除一个ProductInfo
     * @param id 要删除的id
     */
    public Long deleteProductInfoById(Long id){
    	return productInfoDao.deleteProductInfoById(id);
    }
	public Long deleteProductInfoByObj(ProductInfo productInfo){
        return productInfoDao.deleteProductInfoByObj(productInfo);
    }
    public Long deleteProductInfoByIdList(List<Long > ids){
    	
    	return productInfoDao.deleteProductInfoByIdList(ids);
    }
    /**
     * 修改ProductInfo
     * @param productInfo 要修改的ProductInfo
     */
    public Long updateProductInfo(ProductInfo productInfo){
     	return productInfoDao.updateProductInfo(productInfo);
    }
    
    public Long updateProductInfoByObj(ProductInfo productInfo){
    	return productInfoDao.updateProductInfoByObj(productInfo);
    }
    
    public Long updateProductInfoByObjAndConditions(ProductInfo s,ProductInfo c){
    	return productInfoDao.updateProductInfoByObjAndConditions(s,c);
    }
	public void batchUpdateProductInfo(List<ProductInfo> records){
		productInfoDao.batchUpdateProductInfo(records);
	}
    /**
     * 根据id获取单个ProductInfo对象
     * @param id 要查询的id
     * @return ProductInfo
     */
    
    public Integer getProductInfoCountByObj(ProductInfo productInfo){
    	return productInfoDao.getProductInfoCountByObj(productInfo);
    }
    


    public ProductInfo getProductInfoById(Long id){
    	return productInfoDao.getProductInfoById( id);
    }
    
     public ProductInfo getProductInfoByObj(ProductInfo productInfo) {
        return productInfoDao.getProductInfoByObj(productInfo);
    }


    
    public List<ProductInfo> getProductInfoListByObj(ProductInfo productInfo){
        return productInfoDao.getProductInfoListByObj(productInfo);
    }
    public List<ProductInfo> getProductInfoListPage(ProductInfo productInfo,Integer offset,Integer limit){
        return productInfoDao.getProductInfoListPage(productInfo,offset,limit);
    }
    
    public List<ProductInfo> getProductInfoPage(ProductInfo productInfo,PageEntity page) {
        return productInfoDao.getProductInfoPage(productInfo,page);
    }
}