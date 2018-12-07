package com.trs.damon.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class WeiboSourceClassifyBean {
	private Integer id;
	private String data_user;
	/**
	 * 新增3个变量对应Mysql的字段
	 */
	private Integer certificate_flag;
	private String mark_list;
	//private String gather_priority;
	private String media_unit;
	private String certificate_unit;
	//=====
	private String weiboid;
	private String weiboname;
	private String weibourl;
	private String rank;
	//private String rank_m;
	private String belonging;
	private String belonging_m;
	private String erticalstructure;
	private String erticalstructure_m;
	private String regionalstructure;
	private String certification_type;
	private String reliability;
	private Integer level;
	private Integer product;
	private String source;
}
