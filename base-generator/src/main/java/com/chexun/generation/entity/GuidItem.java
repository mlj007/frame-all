package com.chexun.generation.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName
 * @description 用到唯一字段时生成
 * @author : 
 * @Create Date : 2014-3-13 下午8:16:44
 */
public class GuidItem implements Serializable {
   
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 187309816730062536L;
    @Getter
    @Setter
    private static Long defaultAutoId=1L;//主键
    @Getter
    @Setter
    private Long id;//主键
    @Getter
    @Setter
    private Long autoId=defaultAutoId;//自增值,在此定义默认值
    @Getter
    @Setter
    private String project;//关键字
    @Getter
    @Setter
    private String description="";//描述
 
}
