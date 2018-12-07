package com.damon.repository;

import com.trs.ckm.soap.RevDetail;
import com.trs.search.annotations.HybaseDocument;
import com.trs.search.annotations.HybaseField;
import com.trs.search.annotations.HybaseId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@HybaseDocument(indexName = "mlf_http_product_original_20160321")
public class Szb{

	@HybaseId
	private String id;
	
	private String hybaseId;

	//加一个事故变量对应正式库字段
	private String accidentNums;

	private boolean noErr = true;
	
	private long guid;
	
	private String similar;
	
	private RevDetail[] revDetails;
	
	private String rank;
	
	private String final_catalog;
	
	private String belonging;
	
	private String ertical;
	
	private String regional;
	
	private String zyzxfild;
	
	private String original;
	
	private String original_author;
	
	private String cnmlList;
	
	private String areaList;
	
	private String zyzxfieldList;
	
	private String source_site;
	
	private String data_level = SzbConst.ZERO;

	private String isProduct;

	private String sourceType;
	
	private String formalContent;
	
	private String formalNr;
	
	private String keywords5;
	
	private String department;
	
	private String drop = SzbConst.ZERO;
	
	private String simDetails;
	
	private String entity_name;
	
	private String entity_place;
	
	private String entity_org;
	
	private String entity_time;
	
	private String isResignDoc = SzbConst.ZERO; //是否是重新签发稿，之前该字段名为 isNeedPic
	
	private String zb_final_area;

	/**
	 * 情感数字：－100～100
	 * IR_EMOTIONAL_VALUE字段计算得出
	 * @since 16/07/08
	 */
	private String emotional_digit;

	/**
	 * 各方观点中新增字段
	 * @since  17.04.15
	 */
	private String event_abs;
	private String event_category;
	private String event_entity;

	/**
	 * 媒体地域
	 * @since  17.04.15
	 **/
	private String site_area;

	/**
	 * 相关政策
	 * @since  17.04.15
	 */
	private String event_policy;

	/*ckm标签抽取字段*/
	private String zbEntityLabelChar;
	private String zbKeywordsLabelChar;
	private String zbClassifyLabelChar;
	
	/** 是否音/视频 */
	private String va = SzbConst.ZERO;

	@HybaseField(field = "IR_VIEDO_URL", searchFields = true, highLightFields = false)
	private String video_url;

	@HybaseField(field = "IR_VIDEO_ID", searchFields = true, highLightFields = false)
	private String video_id;

	@HybaseField(field = "IR_SRCURL", searchFields = true, highLightFields = false)
	private String srcUrl;

	@HybaseField(field = "TITLE_AREA", searchFields = true, highLightFields = false)
	private String title_area;
	
	@HybaseField(field = "KEYWORD_RULE_AREA", searchFields = true, highLightFields = false)
	private String keyword_area;
	
	@HybaseField(field = "KEYWORD_RULE_CNML", searchFields = true, highLightFields = false)
	private String keywordCnml;
	
	@HybaseField(field = "TITLE_AUTO_CNML", searchFields = true, highLightFields = false)
	private String titleAutoCnml;
	
	@HybaseField(field = "CONTENT_RULE_CNML", searchFields = true, highLightFields = false)
	private String contentRuleCnml;
	
	@HybaseField(field = "ZB_GUID_CHAR", searchFields = true, highLightFields = false)
	private String originalGuid;
	
	@HybaseField(field = "TITLE_CNML", searchFields = true, highLightFields = false)
	private String titleCnml;
	
	@HybaseField(field = "CONTENT_CNML", searchFields = true, highLightFields = false)
	private String contentCnml;
	
	@HybaseField(field = "SHARDING_FLAG", searchFields = true, highLightFields = false)
	private String sharding_flag;
	
	@HybaseField(field = "KID", searchFields = true, highLightFields = false)
	private String kid;

	@HybaseField(field = "MC", searchFields = true, highLightFields = false)
	private String mc;

	@HybaseField(field = "RQ", searchFields = true, highLightFields = true)
	private String rq;
	
	@HybaseField(field = "KY", searchFields = true, highLightFields = false)
	private String ky;
	
	@HybaseField(field = "YT", searchFields = true, highLightFields = false)
	private String yt;
	
	@HybaseField(field = "TITLE", searchFields = true, highLightFields = false)
	private String title;
	
	@HybaseField(field = "FB", searchFields = true, highLightFields = false)
	private String fb;
	
	@HybaseField(field = "ZZ", searchFields = true, highLightFields = false)
	private String zz;
	
	@HybaseField(field = "TX", searchFields = true, highLightFields = false)
	private String content;
	
	@HybaseField(field = "TXS", searchFields = true, highLightFields = false)
	private String txs;
	
	@HybaseField(field = "BC", searchFields = true, highLightFields = false)
	private String bc;
	
	@HybaseField(field = "BM", searchFields = true, highLightFields = false)
	private String bm;
	
	@HybaseField(field = "PD", searchFields = true, highLightFields = false)
	private String pd;
	
	@HybaseField(field = "PDFPATH", searchFields = true, highLightFields = false)
	private String pdfPath;
	
	@HybaseField(field = "CC", searchFields = true, highLightFields = false)
	private String cc;
	
	@HybaseField(field = "JP", searchFields = true, highLightFields = false)
	private String jp;
	
	@HybaseField(field = "JPPATH", searchFields = true, highLightFields = false)
	private String jpPath;
	
	@HybaseField(field = "PU", searchFields = true, highLightFields = false)
	private String pu;
	
	@HybaseField(field = "CT", searchFields = true, highLightFields = false)
	private String ct;
	
//	@HybaseField(field = "CTS", searchFields = true, highLightFields = false)
	private String cts = SzbConst.ZERO;
	
	@HybaseField(field = "CTPATH", searchFields = true, highLightFields = false)
	private String ctPath;
	
	@HybaseField(field = "CS", searchFields = true, highLightFields = false)
	private String cs;
	
	@HybaseField(field = "TZ", searchFields = true, highLightFields = false)
	private String tz;
	
	@HybaseField(field = "TS", searchFields = true, highLightFields = false)
	private String ts;
	
	@HybaseField(field = "QS", searchFields = true, highLightFields = false)
	private String qs;
	
	@HybaseField(field = "NQ", searchFields = true, highLightFields = false)
	private String nq;
	
	@HybaseField(field = "ZK", searchFields = true, highLightFields = false)
	private String zk;
	
	@HybaseField(field = "QH", searchFields = true, highLightFields = false)
	private String qh;
	
	@HybaseField(field = "FL", searchFields = true, highLightFields = false)
	private String fl;
	
	@HybaseField(field = "LM", searchFields = true, highLightFields = false)
	private String lm;
	
	@HybaseField(field = "BJ", searchFields = true, highLightFields = false)
	private String bj;
	
	@HybaseField(field = "TP", searchFields = true, highLightFields = false)
	private String tp;
	
	@HybaseField(field = "GG", searchFields = true, highLightFields = false)
	private String gg;
	
	@HybaseField(field = "WH", searchFields = true, highLightFields = false)
	private String wh;
	
	@HybaseField(field = "BZ", searchFields = true, highLightFields = false)
	private String bz;
	
	@HybaseField(field = "ZY", searchFields = true, highLightFields = false)
	private String zy;
	
	@HybaseField(field = "WB", searchFields = true, highLightFields = false)
	private String wb;
	
	@HybaseField(field = "BB", searchFields = true, highLightFields = false)
	private String bb;
	
	@HybaseField(field = "YM", searchFields = true, highLightFields = false)
	private String ym;
	
	@HybaseField(field = "ZB", searchFields = true, highLightFields = false)
	private String zb;
	
	@HybaseField(field = "QY", searchFields = true, highLightFields = false)
	private String qy;
	
	@HybaseField(field = "HJ", searchFields = true, highLightFields = false)
	private String hj;
	
	@HybaseField(field = "NR", searchFields = true, highLightFields = false)
	private String nr;
	
	@HybaseField(field = "SX", searchFields = true, highLightFields = false)
	private String sx;
	
	@HybaseField(field = "SXPATH", searchFields = true, highLightFields = false)
	private String sxPath;
	
	@HybaseField(field = "XB", searchFields = true, highLightFields = false)
	private String xb;
	
	@HybaseField(field = "URL", searchFields = true, highLightFields = false)
	private String url;
	
	@HybaseField(field = "CREATETIME", searchFields = true, highLightFields = false)
	private String createTime;
	
	@HybaseField(field = "DOCPUBTIME", searchFields = true, highLightFields = false)
	private String pubTime;
	
	@HybaseField(field = "NRGUID", searchFields = true, highLightFields = false)
	private String nrGuid;
	
	@HybaseField(field = "FBLX", searchFields = true, highLightFields = false)
	private String fblx;
	
	@HybaseField(field = "GJID", searchFields = true, highLightFields = false)
	private String gjid;
	
	@HybaseField(field = "TC", searchFields = true, highLightFields = false)
	private String tc;
	
	@HybaseField(field = "WZBJ", searchFields = true, highLightFields = false)
	private String wzbj;
	
	@HybaseField(field = "BJBM", searchFields = true, highLightFields = false)
	private String bjbm;
	
	@HybaseField(field = "CBBM", searchFields = true, highLightFields = false)
	private String cbbm;
	
	@HybaseField(field = "WJLX", searchFields = true, highLightFields = false)
	private String wjlx;
	
	@HybaseField(field = "QYR", searchFields = true, highLightFields = false)
	private String qyr;
	
	@HybaseField(field = "CBZZ", searchFields = true, highLightFields = false)
	private String cbzz;
	
	@HybaseField(field = "GGLX", searchFields = true, highLightFields = false)
	private String gglx;
	
	@HybaseField(field = "IR_KEYWORDS", searchFields = true, highLightFields = false)
	private String keyWords;
	
	@HybaseField(field = "ADDITION_CHAR1", searchFields = true, highLightFields = false)
	private String addition_char1;
	
	@HybaseField(field = "ADDITION_CHAR2", searchFields = true, highLightFields = false)
	private String addition_char2;
	
	@HybaseField(field = "ADDITION_CHAR3", searchFields = true, highLightFields = false)
	private String addition_char3;
	
	@HybaseField(field = "ADDITION_NUM1", searchFields = true, highLightFields = false)
	private String addition_num1;
	
	@HybaseField(field = "ADDITION_PHRASE1", searchFields = true, highLightFields = false)
	private String addition_phrase1;
	
	@HybaseField(field = "ADDITION_DOC1", searchFields = true, highLightFields = false)
	private String addition_doc1;
	
	@HybaseField(field = "ADDITION_DATE1", searchFields = true, highLightFields = false)
	private String addition_date1;
	
	@HybaseField(field = "PUBID", searchFields = true, highLightFields = false)
	private String pubId;
	
	@HybaseField(field = "ADDITION_NUM2", searchFields = true, highLightFields = false)
	private String addition_num2;
	
	@HybaseField(field = "ADDITION_NUM3", searchFields = true, highLightFields = false)
	private String addition_num3;
	
	@HybaseField(field = "ADDITION_PHRASE2", searchFields = true, highLightFields = false)
	private String addition_phrase2;
	
	@HybaseField(field = "ADDITION_PHRASE3", searchFields = true, highLightFields = false)
	private String addition_phrase3;
	
	@HybaseField(field = "ADDITION_DOC2", searchFields = true, highLightFields = false)
	private String addition_doc2;
	
	@HybaseField(field = "ADDITION_DOC3", searchFields = true, highLightFields = false)
	private String addition_doc3;
	
	@HybaseField(field = "ADDITION_DATE2", searchFields = true, highLightFields = false)
	private String addition_date2;
	
	@HybaseField(field = "ADDITION_DATE3", searchFields = true, highLightFields = false)
	private String addition_date3;
	
	@HybaseField(field = "CATALOG_AREA", searchFields = true, highLightFields = false)
	private String catalog_area;
	
	@HybaseField(field = "IR_ABSTRACT", searchFields = true, highLightFields = false)
	private String ir_abstract;
	
	@HybaseField(field = "MD5TAG", searchFields = true, highLightFields = false)
	private String md5tag;
	
	@HybaseField(field = "IR_EMOTIONAL_VALUE", searchFields = true, highLightFields = false)
	private String emotinal_value;
	
	@HybaseField(field = "SECOND_MARK_SUCCESS", searchFields = true, highLightFields = false)
	private String success;
	
	@HybaseField(field = "SECOND_MARK_EXCEPTION", searchFields = true, highLightFields = false)
	private String exception;
	
	@HybaseField(field = "PRODUCT_TYPE", searchFields = true, highLightFields = false)
	private String product_type;
	
	@HybaseField(field = "ZB_AUTHOR_ID", searchFields = true, highLightFields = false)
	private String author_id;
	
	@HybaseField(field = "ZB_EDITOR_ID", searchFields = true, highLightFields = false)
	private String editor_id;
	
	@HybaseField(field = "MLF_ROOTID", searchFields = true, highLightFields = false)
	private String mlfRootID;
	
	@HybaseField(field = "MLF_DOCID", searchFields = true, highLightFields = false)
	private String mlfDocID;
	
	@HybaseField(field = "ORGINAL_FLAG", searchFields = true, highLightFields = false)
	private String originalFlag;

	/**
	 * 推送时间
	 * @since 16/09/18 LiYao
	 */
	@HybaseField(field = "IR_LASTTIME", searchFields = true, highLightFields = false)
	private String lastTime;

	/**
	 * 输入时间和当前时间时间差
	 * SIMNUM=当前时间-IR_LASTTIME
	 * @since 16/08/19 LiYao
	 */
	@HybaseField(field = "SIMNUM", searchFields = true, highLightFields = false)
	private String simNum;

	/**
	 * 输入时间和参数时间的时间差
	 * TITLE_SIMNUM=IR_LASTTIME-IR_URLTIME
	 * @since 16/08/19 LiYao
	 */
	@HybaseField(field = "TITLE_SIMNUM", searchFields = true, highLightFields = false)
	private String title_simNum;

	@HybaseField(field = "DOC_TYPE", searchFields = true, highLightFields = false)
	private String docType;

	@HybaseField(field = "APPENDIXS_DOC", searchFields = true, highLightFields = false)
	private String appendixs_doc;
	
	@HybaseField(field = "LIST_TITLE", searchFields = true, highLightFields = false)
    private String list_title;

}

