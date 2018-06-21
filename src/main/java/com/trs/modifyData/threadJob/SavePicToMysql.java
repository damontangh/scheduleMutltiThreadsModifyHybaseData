package com.trs.modifyData.threadJob;

import com.trs.hybase.client.TRSReport;
import com.trs.modifyData.repository.*;
import com.trs.search.builder.ITRSSearchBuilder;
import com.trs.search.builder.SearchBuilderFactory;
import com.trs.search.common.PagedList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.trs.common.utils.StringUtils.isNullOrEmpty;

/**
 * 把http原始库的图片抽取出来存入mysql,成品库和原始库需要更新的字段进行更新
 */
@Component
@Slf4j
public class SavePicToMysql {
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private FormalSzbRepository formalSzbRepository;
    @Autowired
    private SzbRepository szbRepository;

    private static final String IMGURL_REG = "IMAGE.*SRC=(.*?)[^>]*?(>|gt;)|<img[\\S\\s]*?src=\"[\\S\\s]*?\" />";
    private static final String IMGSRC_REG = "(?<!(old|OLD))src=(\"(http|https):.*?\") | (?<!(old|OLD))src=(\"W0.*?\")";

    public List<Szb> fetchOrigianlData(){
        ITRSSearchBuilder builder= SearchBuilderFactory.create(0, 9999);//TODO
        builder.filterByTRSL("DOCPUBTIME:[\"2018.01.17 17:00:00\" TO *] NOT DOC_TYPE:2 NOT ADDITION_NUM1:1");
        builder.orderBy("DOCPUBTIME",true);
        try {
            PagedList<Szb> szbPagedList = szbRepository.pageList(builder);
            if (szbPagedList != null){
                List<Szb> szbList = szbPagedList.getPageItems();
                log.info(String.format("fetch data from original base successfully,size=[%s]",szbList.size()));
                return szbList;
            }
        } catch (Exception e) {
            log.error(String.format("fetch data from original base error,msg:[%s]",e));
        }
        return null;
    }


    public void savePictureToMysqlAndupdateMlfFormalAndOriginal(List<Szb> datas){
        if (datas == null || datas.size() == 0)
            return;
        /**
         * 成品库：
         * 变量CTS要赋值到成品库
         * 变量formalNr赋值给IR_CONTENT
         * 因此先根据原始库的GUID去获取成品库数据，然后进行赋值，再更新成品库
         * 再更新原始库
         * 更新原始库：
         * ADDITION_NUM1:1
         */
        for (int i = 0; i < datas.size(); i++) {
            Szb data = datas.get(i);
            final String guid = data.getOriginalGuid();
            List<Picture> mysqlPictures = pictureRepository.findAll(new Specification<Picture>() {
                @Override
                public Predicate toPredicate(Root<Picture> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Predicate docIdEqual = null;
                    if(!isNullOrEmpty(guid)){
                        // 这里也可以root.get("name").as(String.class)这种方式来强转泛型类型
                        docIdEqual = criteriaBuilder.equal(root.<Integer> get("doc_id"), guid);
                    }
                    if(null != docIdEqual) criteriaQuery.where(docIdEqual);
                    return docIdEqual;
                }
            });
            //如果根据guid去查mysql，能查到数据的话说明之前打标已经把图片存进去了
            if (mysqlPictures != null && mysqlPictures.size() > 0) continue;
            if(isNullOrEmpty(data.getDocType()))
                data.setDocType(SzbConst.ONE); // 若为空，则默认为新闻稿处理
            List<Picture> pictures = new ArrayList<>();
            data.setFormalNr(CleanSign(isNullOrEmpty(data.getContent())? "" : data.getContent()));
            dealNotAtlas(pictures, data);
            if(pictures.size() > 0){
                data.setCts(pictures.size() + "");
                pictureRepository.save(pictures);
            }
            //下面更新成品库
            ITRSSearchBuilder builder= SearchBuilderFactory.create(0, 1000);
            builder.filterByTRSL(String.format("ZB_GUID_CHAR:%s",data.getOriginalGuid()));
            try {
                PagedList<FormalSzb> mlfProData = formalSzbRepository.pageList(builder);
                List<FormalSzb> formalSzbList = mlfProData.getPageItems();
                if (formalSzbList != null && formalSzbList.size() != 0){
                    FormalSzb eachData =formalSzbList.get(0);
                    eachData.setCts(data.getCts());
                    eachData.setIr_content(data.getFormalNr());
                    //更新成品库
                    TRSReport report = formalSzbRepository.updateRecord(eachData);
                    long updateNum = report.getUpdatedNum();
                    //下面更新原始库
                    data.setAddition_num1("1");
                    TRSReport originalReport = szbRepository.updateRecord(data);
//                    log.info(String.format("update mlf_product_formal success,size=%s",updateNum));
                }

            } catch (Exception e) {
                log.error(String.format("fetch data or update error,msg:[%s],guid=[%s]",e,data.getOriginalGuid()));
            }
        }
    }

    public String CleanSign(String str) {
        str= str.replaceAll("&ldquo;", "\"").replaceAll("&rdquo;", "\"")
                .replaceAll("&lsquo;", "'").replaceAll("&rsquo;", "'")
                .replaceAll("&middot;", ".").replaceAll("&mdash;", "—");
        return str;
    }

    /**
     * 非图集稿-通过正则抽取正文图片
     * @param pictures 稿件正文中图片集合
     * @param data 稿件数据
     */
    public void dealNotAtlas(List<Picture> pictures, Szb data){
        if(data.getFormalNr().length() > 1 && !data.getDocType().equals(SzbConst.TWO)){
            List<String> imgUrl = this.getImageUrl(data.getFormalNr());
            List<String> imgSrc = this.getImageSrc(imgUrl);
            if(imgSrc.size() > 0){
                for(String url:imgSrc){
                    url = url.trim();
                    String sign = url.substring(0, 1);
                    String sourceSite = isNullOrEmpty(data.getProduct_type()) ? "" : data.getProduct_type();
                    Picture p = new Picture();
                    // 只有资源圈数据才进行图片下载(false:进行下载,true:不进行下载)
                    p.setIs_download(!SzbConst.ZERO.equals(data.getData_level()));
                    p.setPub_time(data.getPubTime());
                    p.setSave_time(new Date());
                    p.setDoc_id(Long.parseLong(data.getOriginalGuid()));
                    p.setRandomNo((int)(Math.random()*10));
                    p.setSection(SzbConst.PRODUCT_TYPE);
                    p.setSource_site(sourceSite);
                    if (sign.equals("W")) {
                        //获取解析后的url
                        String wcmUrl = getWcmPicPath(url);
                        //得到用于存储含html标签的content
                        String nr = !isNullOrEmpty(data.getFormalNr())? data.getFormalNr(): new String();
                        //将nr中的所有未解析url替换成解析过后的wcmUrl
                        nr = nr.replace(url, wcmUrl);
                        data.setFormalNr(nr);
                        //只有解析过后的wcmUrl才能被正常下载
                        p.setSrc_link(wcmUrl);
                    }else {
                        //进入else图片url，那就不是W开头的了，此时需要进行判断是否http://开头
                        url = getAccurateUrl(url);
                        if (url != null && url.length() < 15)
                            continue;
                        p.setSrc_link(url);
                    }
                    p.setTitle(data.getTitle());
                    p.setTs(data.getTs());
                    pictures.add(p);
                }
            }
        }
    }

    public static String getAccurateUrl(String originalUrl){
        String regx = "(http[s]?:)+(/*)(.*)";
        Matcher matcher = Pattern.compile(regx).matcher(originalUrl);
        if (matcher.find()){
            String slashPart = matcher.group(2);
            //如果//部分长度是2，那就不用修改
            if (slashPart.length() == 2) return originalUrl;
            //如果是http: 就加上//
            //如果是http:////，就换成//
            if (slashPart.length() == 0) {
                slashPart = "//";
                originalUrl = matcher.group(1) + slashPart + matcher.group(3);
            } else originalUrl = originalUrl.replace(slashPart,"//");
            return originalUrl;
        }
        //如果匹配不上正则，那说明这个url不对，就不要这个url了
        return "";
    }

    public String getWcmPicPath(String pic) {
        String wcmPic = new String();
        String month = pic.substring(0, 8);
        String day = pic.substring(0, 10);
        //按照wcm给予的解析标准进行解析
        wcmPic = String.format("%s/%s/%s/%s", "http://mlf.8531.cn/webpic", month, day, pic);
        return wcmPic;
    }

    private static List<String> getImageUrl(String HTML) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
        List<String> listImgUrl = new ArrayList<>();
        while (matcher.find()) {
            listImgUrl.add(matcher.group());
        }
        return listImgUrl;
    }

    /***
     * 获取ImageSrc地址
     *
     * @param listImageUrl
     * @return
     */
    private List<String> getImageSrc(List<String> listImageUrl) {
        List<String> listImgSrc = new ArrayList<String>();
        if(listImageUrl.size()>0){
            for (String image : listImageUrl) {
                Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
                while (matcher.find()) {
                    String url = matcher.group().substring(0, matcher.group().length() - 1);
                    url = DecodeString(url);
                    url = url.replace("src=\"", "").replace("\" &gt", "").replace("\"", "").replace("&gt", "");
                    listImgSrc.add(url);
                }
            }
        }
        return listImgSrc;
    }

    public String DecodeString(String content) {
        content = content.replace("&amp;lt;", "<").replace("&amp;gt;", ">").replace("&amp;amp;lt;", "<").replace("&amp;amp;gt;", ">")
                .replace("&amp;amp;amp;lt;", "<").replace("&amp;amp;amp;gt;", ">")
                .replace("&gt;", ">").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;amp;nbsp;", " ").replace("&gt;", ">");
        content = content.replace("&amp;", "&").replace("&quot;", "\"").replace("&apos;", "'").replace("&amp;amp;quot;", "\"").replace("&amp;quot;", "\"")
                .replace("&amp;amp;amp;quot;", "\"").replace("&nbsp;", " ").replace("&amp;nbsp;", " ").replace("&amp;amp;amp;nbsp;", " ");
        content = content.replaceAll("&[amp;]+?lt;", "<").replaceAll("&[amp;]+?gt;", ">")
                .replaceAll("&[amp;]+?quot;", "\"").replaceAll("&[amp;]+?nbsp;", " ").replaceAll("&(amp;)+", "&");
        //将换行符转换为html标签
        //content = content.replace("\r\n", "<br/>");
        return content;
    }


}
