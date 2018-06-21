package com.trs.modifyData.entity;

import lombok.Data;

@Data
public class WeixinSourceClassifyBean {

	private Integer id;
	/**
	 * 微信名
	 */
	private String weixinname;
	/**
	 * 微信ID
	 */
	private String weixinid;
	/**
	 * 来源等级
	 */
	private String rank;
	/**
	 * 归属性质
	 */
	private String belonging;
	/**
	 * 垂直性质
	 */
	private String erticalstructure;
	/**
	 * 地域性质
	 */
	private String regionalstructure;
	/**
	 * 可靠性
	 */
	private String reliability;
	/**
	 * 数据等级(资源圈:0,分析圈1,其他:100)
	 */
	private String level;
	/**
	 * 是否集团信息(1:是,0否)
	 */
	private String product;
	/**
	 * 数据源
	 */
	private String source;

	private String dataUser;/*采集点使用者 mlf/ddgz*/

	private Integer certificate_flag;

	private String mark_list;
}
