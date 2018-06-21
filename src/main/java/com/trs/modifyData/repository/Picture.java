package com.trs.modifyData.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by HongDanyang on 15/11/18.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "picture")
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * 记录发布时间
	 */
	@Column(name = "pub_time")
	private String pub_time;

	/**
	 *FastDFS链接
	 */
	@Column(name = "f_link")
	private String f_link;

	@Column(name = "f_s_link")
	private String f_s_link;

	@Column(name = "f_m_link")
	private String f_m_link;

	/**
	 * 原始链接
	 */
	@Column(name = "src_link")
	private String src_link;

	/**
	 * Zip包路径
	 */
	@Column(name = "zip_path")
	private String zip_path;

	/**
	 * 是否下载
	 */
	@Column(name = "is_download",columnDefinition="boolean default false")
	private Boolean is_download;

	/**
	 * ZB_GUID_CHAR
	 */
	@Column(name = "doc_id")
	private long doc_id;

	/**
	 * 重试次数
	 */
	@Column(name = "repeat_count")
	private int repeat_count;

	/**
	 * 进入fastDFS时间
	 */
	@Column(name = "save_time")
	private Date save_time;

	/**
	 * 随机数用于分片策略
	 */
	@Column(name="random_no")
	private int randomNo;

	/**
	 * 用于标记所属名称
	 */
	@Column(name="section")
	private String section;

	/**
	 * 用于标记详细来源
	 */
	@Column(name="source_site")
	private String source_site;

	/**
	 * 图片的宽
	 */
	@Column(name="width", columnDefinition="int default 0")
	private int width;

	/**
	 * 图片的高
	 */
	@Column(name="height", columnDefinition="int default 0")
	private int height;

	/**
	 * 标题
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 图说
	 */
	@Column(name = "ts", columnDefinition = "TEXT")
	private String ts;

	/**
	 * 图片子级目录
	 */
	@Column(name = "bz_mark")
	private String bz_mark;

}

