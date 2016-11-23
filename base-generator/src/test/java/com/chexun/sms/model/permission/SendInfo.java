package com.chexun.sms.model.permission;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SendInfo implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long id;
    private String mobile;
    private String content;
    private String params;
    private Long source;
    private String ip;
    private java.util.Date addtime;
    private java.util.Date sendtime;
    private Long state;
}
