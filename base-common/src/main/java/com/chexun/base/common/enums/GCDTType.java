package com.chexun.base.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum GCDTType {
	
	关注("关注", 1001L), 发布日志("发布日志", 1002L), 发布新问题("发布新问题", 1003L), 
	发布小组话题("发布小组话题", 1004L), 发布文章("发布文章", 1005L), 赞日志("赞日志", 1006L),
	赞回答("赞回答",1007L),赞小组话题("赞小组话题",1008L),赞文章("赞文章",1009L),踩回答("踩回答",1010L),
	感谢回答("感谢回答",1011L),收藏日志("收藏日志",1012L),收藏回答("收藏回答",1013L),收藏小组话题("收藏小组话题",1014L),
	收藏文章("收藏文章",1015L),评论日志("评论日志",1016L),评论回答("评论回答",1017L),评论小组话题("评论小组话题",1018L),
	评论文章("评论文章",1019L),新建小组("新建小组",1020L),加入小组("加入小组",1021L),日志转载("日志转载",1022L),
	文章投稿("文章投稿",1023L), 评论微话题("评论微话题",1024L),推荐日志("推荐日志",1025L),推荐回答("推荐回答",1026L),
	推荐小组话题("推荐小组话题",1027L),推荐文章("推荐文章",1028L),推荐微话题("推荐微话题",1029L),
	发布微话题("发布微话题",1030L),赞微话题("赞微话题",1031L),收藏微话题("收藏微话题",1032L);

	private String positionName;
	private Long positionValue;

	private GCDTType(String positionName, Long positionValue) {
		this.positionName = positionName;
		this.positionValue = positionValue;
	}

	public String getTypeName() {
		return positionName;
	}

	public void setTypeName(String positionName) {
		this.positionName = positionName;
	}

	public Long getTypeValue() {
		return positionValue;
	}

	public void setTypeValue(Long positionValue) {
		this.positionValue = positionValue;
	}

	/**
	 * 根据位置名字获取值
	 *
	 * @param style
	 * @return
	 */
	public static Long findEnum(String positionName) {
		GCDTType[] c = values();
		for (GCDTType ate : c) {
			if (ate.getTypeName().equals(positionName)) {
				return ate.getTypeValue();
			}
		}
		return null;
	}

	/**
	 * 根据位置值获得未知名称
	 *
	 * @param style
	 * @return
	 */
	public static String findEnum(Long positionValue) {
		GCDTType[] c = values();
		for (GCDTType ate : c) {
			if (ate.getTypeValue().equals(positionValue)) {
				return ate.getTypeName();
			}
		}
		return null;
	}

	public static Map<String, Long> getAllPositionType() {
		Map<String, Long> positionType = new HashMap<String, Long>();
		GCDTType[] c = values();
		for (GCDTType ate : c) {
			positionType.put(ate.getTypeName(), ate.getTypeValue());
		}
		return positionType;
	}
}
