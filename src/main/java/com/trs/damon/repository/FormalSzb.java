package com.trs.damon.repository;

import com.trs.ckm.soap.RevDetail;
import com.trs.search.annotations.HybaseDocument;
import com.trs.search.annotations.HybaseField;
import com.trs.search.annotations.HybaseId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@HybaseDocument(indexName = "mlf_product_formal")
public class FormalSzb{

	@HybaseId
	private String id;
	/**
	 * 新增字段，事故码值
	 */

	@HybaseField(field = "IR_VIEDO_URL", searchFields = true, highLightFields = false)
	private String video_url;

	@HybaseField(field = "IR_VIDEO_ID", searchFields = true, highLightFields = false)
	private String video_id;

	@HybaseField(field = "IR_SRCURL", searchFields = true, highLightFields = false)
	private String srcUrl;

	@HybaseField(field = "ACCIDENT_NUM", searchFields = true, highLightFields = false)
	private String accidentNums;

	@HybaseField(field = "ZB_SIMILAR", searchFields = true, highLightFields = false)
	private String similar;
	
	@HybaseField(field = "ZB_DEPARTMENT", searchFields = true, highLightFields = false)
	private String department;
	
	@HybaseField(field = "ZB_FINAL_CATALOG", searchFields = true, highLightFields = false)
	private String final_catalog;
	
	@HybaseField(field = "ZB_DROP", searchFields = true, highLightFields = false)
	private String drop;
	
	@HybaseField(field = "ZB_SIMDETAILS", searchFields = true, highLightFields = false)
	private String simDetails;
	
	@HybaseField(field = "TITLE_CNML", searchFields = true, highLightFields = false)
	private String titleCnml;
	
	@HybaseField(field = "CONTENT_CNML", searchFields = true, highLightFields = false)
	private String contentCnml;
	
	@HybaseField(field = "ZB_AUTHOR_ID", searchFields = true, highLightFields = false)
	private String author_id;
	
	@HybaseField(field = "ZB_EDITOR_ID", searchFields = true, highLightFields = false)
	private String editor_id;
	
	@HybaseField(field = "KEYWORD_RULE_CNML", searchFields = true, highLightFields = false)
	private String keywordCnml;
	
	@HybaseField(field = "TITLE_AUTO_CNML", searchFields = true, highLightFields = false)
	private String titleAutoCnml;
	
	@HybaseField(field = "CONTENT_RULE_CNML", searchFields = true, highLightFields = false)
	private String contentRuleCnml;
	
	@HybaseField(field = "ZB_TYPE", searchFields = true, highLightFields = false)
	private String type;
	
	@HybaseField(field = "TITLE_AREA", searchFields = true, highLightFields = false)
	private String title_area;
	
	@HybaseField(field = "KEYWORD_RULE_AREA", searchFields = true, highLightFields = false)
	private String keyword_area;
	
	@HybaseField(field = "ZB_FINAL_AREA", searchFields = true, highLightFields = false)
	private String zb_final_area;
	
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
	private String tx;
	
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
	
	@HybaseField(field = "CTS", searchFields = true, highLightFields = false)
	private String cts;
	
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
	
	@HybaseField(field = "IR_CONTENT", searchFields = true, highLightFields = false)
	private String ir_content;
	
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
	private String pubtime;
	
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
	
	@HybaseField(field = "KEYWORDS", searchFields = true, highLightFields = false)
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
	
	@HybaseField(field = "CNML", searchFields = true, highLightFields = false)
	private String cnml;
	
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
	
	@HybaseField(field = "ZB_GUID_CHAR", searchFields = true, highLightFields = false)
	private String guid_char;
	
	@HybaseField(field = "ZB_ZYZXFILD", searchFields = true, highLightFields = false)
	private String zyzxfild;
	
	@HybaseField(field = "ZB_RANK", searchFields = true, highLightFields = false)
	private String rank;
	
	@HybaseField(field = "ZB_BELONGING", searchFields = true, highLightFields = false)
	private String belonging;
	
	@HybaseField(field = "ZB_ERTICAL", searchFields = true, highLightFields = false)
	private String ertical;
	
	@HybaseField(field = "ZB_REGIONAL", searchFields = true, highLightFields = false)
	private String regional;
	
	@HybaseField(field = "ZB_ORIGINAL", searchFields = true, highLightFields = false)
	private String original;
	
	@HybaseField(field = "ZB_ORIGINAL_AUTHOR", searchFields = true, highLightFields = false)
	private String original_author;
	
	@HybaseField(field = "ZB_KEYWORDS5_CHAR", searchFields = true, highLightFields = false)
	private String keywords5_char;
	
	@HybaseField(field = "ZB_KEYWORDS5_PHRASE", searchFields = true, highLightFields = false)
	private String keywords5_phrase;
	
	@HybaseField(field = "PUBID", searchFields = true, highLightFields = false)
	private String pubId;
	
	@HybaseField(field = "ZB_CNML_LIST", searchFields = true, highLightFields = false)
    private String cnmlList;
	
	@HybaseField(field = "ZB_AREA_LIST", searchFields = true, highLightFields = false)
	private String areaList;
	
	@HybaseField(field = "ZB_ZYZXFIELD_LIST", searchFields = true, highLightFields = false)
	private String zyzxfieldList;
	
	@HybaseField(field = "ZB_SOURCE_SITE", searchFields = true, highLightFields = false)
	private String source_site;
	
	@HybaseField(field = "ZB_DATA_LEVEL", searchFields = true, highLightFields = false)
	private String data_level;
	
	@HybaseField(field = "PRODUCTTYPE", searchFields = true, highLightFields = false)
	private String product_type;
	
	@HybaseField(field = "ENTITY_PERSION", searchFields = true, highLightFields = false)
	private String entity_name;
	
	@HybaseField(field = "ENTITY_PLACE", searchFields = true, highLightFields = false)
	private String entity_place;
	
	@HybaseField(field = "ENTITY_ORG", searchFields = true, highLightFields = false)
	private String entity_org;
	
	@HybaseField(field = "ENTITY_TIME", searchFields = true, highLightFields = false)
	private String entity_time;
	
	@HybaseField(field = "MLF_ROOTID", searchFields = true, highLightFields = false)
	private String mlfRootID;
	
	@HybaseField(field = "MLF_DOCID", searchFields = true, highLightFields = false)
	private String mlfDocID;
	
	@HybaseField(field = "ORGINAL_FLAG", searchFields = true, highLightFields = false)
	private String originalFlag;
	
	/**
	 * 情感数字：－100～100
	 * IR_EMOTIONAL_VALUE字段计算得出
	 * @since 16/07/08
	 */
	@HybaseField(field = "IR_EMOTIONAL_DIGIT", searchFields = true, highLightFields = false)
	private String emotional_digit;

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

	/**
	 *  各方观点中新增字段
	 *  @since 17.04.15
	 */
	@HybaseField(field = "EVENT_ABS", searchFields = true, highLightFields = false)
	private String event_abs;

	@HybaseField(field = "EVENT_CATEGORY", searchFields = true, highLightFields = false)
	private String event_category;

	@HybaseField(field = "EVENT_ENTITY", searchFields = true, highLightFields = false)
	private String event_entity;

	@HybaseField(field = "SITE_AREA", searchFields = true, highLightFields = false)
	private String site_area;

	/**相关政策EVENT_POLICY*/
	@HybaseField(field = "EVENT_POLICY", searchFields = true, highLightFields = false)
	private String event_policy;

	/**标签抽取 实体标签*/
	@HybaseField(field = "ZB_ENTITYLABEL_CHAR", searchFields = true, highLightFields = false)
	private String zb_EntityLabel_Char;

	/**标签抽取 关键词标签*/
	@HybaseField(field = "ZB_KEYWORDSLABEL_CHAR", searchFields = true, highLightFields = false)
	private String zb_KeywordsLabel_Char;

	/**标签抽取 分类标签*/
	@HybaseField(field = "ZB_CLASSIFYLABEL_CHAR", searchFields = true, highLightFields = false)
	private String zb_ClassifyLabel_Char;
	
	@HybaseField(field = "IS_VA", searchFields = true, highLightFields = false)
    private String is_va;
	
	@HybaseField(field = "LIST_TITLE", searchFields = true, highLightFields = false)
    private String list_title;
	
	@HybaseField(field = "HYBASE_ID", searchFields = true, highLightFields = false)
    private String hybase_id;

	public FormalSzb(Szb meizi) {
		video_url = meizi.getVideo_url();
		video_id = meizi.getVideo_id();
		srcUrl = meizi.getSrcUrl();
		accidentNums = meizi.getAccidentNums();
		zb_final_area = meizi.getZb_final_area();
		title_area = meizi.getTitle_area();
		keyword_area = meizi.getKeyword_area();
		type = "jtcpg";
		mlfDocID = meizi.getMlfDocID();
		mlfRootID = meizi.getMlfRootID();
		originalFlag = meizi.getOriginalFlag();
		keywordCnml = meizi.getKeywordCnml();
		titleAutoCnml = meizi.getTitleAutoCnml();
		contentRuleCnml = meizi.getContentRuleCnml();
		author_id = meizi.getAuthor_id();
		editor_id = meizi.getEditor_id();
		contentCnml = meizi.getContentCnml();
		titleCnml = meizi.getTitleCnml();
		simDetails = this.getSimDetails(meizi.getRevDetails());
		drop = meizi.getDrop();
		final_catalog = meizi.getFinal_catalog();
		department = meizi.getDepartment();
		guid_char=String.valueOf(meizi.getGuid());
		similar = getSimilarIds(meizi.getRevDetails());
		kid=meizi.getKid();
		mc= meizi.getSource_site();
		rq=meizi.getRq();
		ky=meizi.getKy();
		yt = meizi.getYt();
		title = meizi.getTitle();
		fb = meizi.getFb();
		zz = meizi.getZz();
		tx = meizi.getFormalContent();
		txs = meizi.getTxs();
		bc = meizi.getBc();
		bm = meizi.getBm();
		pd = meizi.getPd();
		pdfPath = meizi.getPdfPath();
		cc = meizi.getCc();
		jp = meizi.getJp();
		jpPath = meizi.getJpPath();
		pu = meizi.getPu();
		ct = meizi.getCt();
		cts = meizi.getCts();
		ctPath = meizi.getCtPath();
		cs = meizi.getCs();
		tz = meizi.getTz();
		ts = meizi.getTs();
		qs = meizi.getQs();
		nq = meizi.getNq();
		zk = meizi.getZk();
		qh = meizi.getQh();
		fl = meizi.getFl();
		lm = meizi.getLm();
		bj = meizi.getBj();
		tp = meizi.getTp();
		gg = meizi.getGg();
		wh = meizi.getWh();
		bz = meizi.getBz();
		zy = meizi.getZy();
		wb = meizi.getWb();
		bb = meizi.getBb();
		ym =meizi.getYm();
		zb =meizi.getZb();
		qy = meizi.getQy();
		hj = meizi.getHj();
		ir_content = meizi.getFormalNr();
		sx = meizi.getSx();
		sxPath = meizi.getSxPath();
		xb = meizi.getXb();
		url =meizi.getUrl();
		createTime = meizi.getCreateTime();
		pubtime = meizi.getPubTime();
		nrGuid= meizi.getNrGuid();
		fblx= meizi.getFblx();
		gjid= meizi.getGjid();
		tc = meizi.getTc();
		wzbj = meizi.getWzbj();
		bjbm = meizi.getBjbm();
		cbbm = meizi.getCbbm();
		wjlx = meizi.getWjlx();
		qyr = meizi.getQyr();
		cbzz = meizi.getCbzz();
		gglx = meizi.getGglx();
		keyWords = meizi.getKeyWords();
		addition_char1 = meizi.getAddition_char1();
		addition_char2 = meizi.getAddition_char2();
		addition_char3 = meizi.getAddition_char3();
		addition_num1 = meizi.getAddition_num1();
		addition_num2 = meizi.getAddition_num2();
		addition_num3 = meizi.getAddition_num3();
		addition_phrase1 = meizi.getAddition_phrase1();
		addition_phrase2 = meizi.getAddition_phrase2();
		addition_phrase3 = meizi.getAddition_phrase3();
		addition_doc1 = meizi.getAddition_doc1();
		addition_doc2 = meizi.getAddition_doc2();
		addition_doc3 = meizi.getAddition_doc3();
		addition_date1 = meizi.getAddition_date1();
		addition_date2 = meizi.getAddition_date2();
		addition_date3 = meizi.getAddition_date3();
		emotinal_value = meizi.getEmotinal_value();
		md5tag = meizi.getMd5tag();
		ir_abstract = meizi.getIr_abstract();
		catalog_area = meizi.getCatalog_area();
		success=meizi.getSuccess();
		exception=meizi.getException();
		zyzxfild=meizi.getZyzxfild();
		rank=meizi.getRank();
		belonging=meizi.getBelonging();
		ertical=meizi.getErtical();
		regional=meizi.getRegional();
		original="0"; // 正式库没有默认值，所以，此处设置一个
		original_author=meizi.getOriginal_author();
		keywords5_char = meizi.getKeywords5();
		keywords5_phrase= meizi.getKeywords5();
		cnmlList=meizi.getCnmlList();
		areaList=meizi.getAreaList();
		zyzxfieldList=meizi.getZyzxfieldList();
		source_site=meizi.getSource_site();
		data_level=meizi.getData_level();
		product_type=meizi.getProduct_type();
		pubId=meizi.getPubId();
		entity_name=meizi.getEntity_name();
		entity_org=meizi.getEntity_org();
		entity_place=meizi.getEntity_place();
		entity_time=meizi.getEntity_time();
		emotional_digit = meizi.getEmotional_digit();
		emotinal_value = meizi.getEmotinal_value();
		// 时间差计算
		this.simNum = meizi.getSimNum();
		this.title_simNum = meizi.getTitle_simNum();
		docType = meizi.getDocType();
		appendixs_doc = meizi.getAppendixs_doc();
		//观点抽取 跟 政策抽取
		event_abs = meizi.getEvent_abs();
		event_category = meizi.getEvent_category();
		event_entity = meizi.getEvent_entity();
		site_area = meizi.getSite_area();
		event_policy = meizi.getEvent_policy();
		//ckm抽取标签
		zb_EntityLabel_Char = meizi.getZbEntityLabelChar();
		zb_KeywordsLabel_Char = meizi.getZbKeywordsLabelChar();
		zb_ClassifyLabel_Char = meizi.getZbClassifyLabelChar();
		is_va = meizi.getVa();
		list_title = meizi.getList_title();
		hybase_id = meizi.getHybaseId();
	}

	private String getSimilarIds(RevDetail[] details){
		if(details!=null){
			String[] ids = new String[details.length];
			for(int i = 0; i < details.length; i++){
				ids[i] = details[i].getid();
			}
			List<String> list = Arrays.asList(ids);
			return list.toString().replace(",", ";").toString().replace("[", "").replace("]", "");
		}
		return null;
	}

	private String getSimDetails(RevDetail[] details){
		if(details!=null){
			StringBuilder strBuild = new StringBuilder();
			for(int i = 0; i < details.length; i++){
				strBuild.append(details[i].getid()).append(":")
						.append(details[i].getsimv()).append(";");
			}
			return strBuild.toString().replace(",", ";").toString().replace("[", "").replace("]", "");
		}
		return null;
	}

}

