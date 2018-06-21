package com.trs.modifyData.entity;

import lombok.Data;

@Data
public class SzbSourceClassifyBean {

	private Integer id;
	/**
	 * 报名
	 */
	private String name;
	/**
	 * 来源等级
	 */
	private String rank;
	/**
	 * 归属性质
	 */
	private String belonging;
	/**
	 * 可靠性
	 */
	private String reliability;
	/**
	 * 数据等级(资源圈:0,分析圈1,其他:100)
	 */
	private Integer level;
	/**
	 * 是否集团信息(1:是,0否)
	 */
	private Integer product;
	/**
	 * 数据源
	 */
	private String source;

	/**
	 * 新增4个变量
	 * 媒体单位
	 * 头版版次
	 * 数字报性质
	 * 地域
	 */
	private String media_unit;

	private String frontpage_num;

	private String szb_character;

	private String regionalstructure;
}
