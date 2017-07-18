package com.mlj.ecbiz.service.product;

import java.util.List;
import com.mlj.ecbiz.model.product.ProductInfo;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * ProductInfo管理接口
 * User: 
 * Date: 2017-07-13
 */
public interface ProductInfoService {

    /**
     * 添加ProductInfo
     * @param productInfo 要添加的ProductInfo
     * @return id
     */
    public Long addProductInfo(ProductInfo productInfo);
	public Long insertProductInfo(ProductInfo productInfo);
    /**
     * 根据id删除一个ProductInfo
     * @param id 要删除的id
     */
    public Long deleteProductInfoById(Long id);
	public Long deleteProductInfoByObj(ProductInfo productInfo);
    public Long deleteProductInfoByIdList(List<Long > ids);
    /**
     * 修改ProductInfo
     * @param productInfo 要修改的ProductInfo
     */
    public Long updateProductInfo(ProductInfo productInfo);
    public Long updateProductInfoByObj(ProductInfo productInfo);
    public Long updateProductInfoByObjAndConditions(ProductInfo s,ProductInfo c);
	public void batchUpdateProductInfo(List<ProductInfo> records);
    /**
     * 根据id获取单个ProductInfo对象
     * @param id 要查询的id
     * @return ProductInfo
     */
    public ProductInfo getProductInfoById(Long id);
	public ProductInfo getProductInfoByObj(ProductInfo productInfo);
    /**
     * 根据条件获取ProductInfo列表
     * @param productInfo 查询条件
     * @return List<ProductInfo>
     */
    public List<ProductInfo> getProductInfoListByObj(ProductInfo productInfo);
    public List<ProductInfo> getProductInfoListPage(ProductInfo productInfo,Integer offset,Integer limit);
    public Integer getProductInfoCountByObj(ProductInfo productInfo);
    public List<ProductInfo> getProductInfoPage(ProductInfo productInfo,PageEntity page);
    
}