package com.mlj.ecbiz.model.product;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductInfo implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long id;
    private String productName;
    private String productSn;
    private String unit;
    private String weight;
    private String typeId;
    private String catId;
    private String brandId;
    private String productNum;
    private String shopPrice;
    private String marketPrice;
    private Long isOnsale;
    private Long isShow;
    private Long sortOrder;
    private String imgitme;
    private String operator;
    private String creatTime;
    private String updateTime;
    private Long stock;
}
