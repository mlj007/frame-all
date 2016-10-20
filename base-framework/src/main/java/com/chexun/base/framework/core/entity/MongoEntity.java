package com.chexun.base.framework.core.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import com.chexun.base.framework.core.util.json.JsonUtil;

@Data
@EqualsAndHashCode(callSuper = false)
public class MongoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 数据生成时间
	 */
	@Indexed(unique = false, direction = IndexDirection.DESCENDING, name = "createTime", dropDups = true)
	private Date createTime = new Date(System.currentTimeMillis());

	@Override
	public String toString() {
		return JsonUtil.bean2json(this);
	}

}
