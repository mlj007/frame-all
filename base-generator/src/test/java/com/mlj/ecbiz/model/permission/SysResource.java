package com.mlj.ecbiz.model.permission;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysResource implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long id;
    private String name;
    private String url;
    private Long parentId;
    private String description;
    private java.util.Date createTime;
    private Character state;
    private Long otherParentId;
}
