package com.damon.threadJob;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.common.utils.StringUtils;
import com.trs.hybase.client.TRSConnection;
import com.trs.hybase.client.TRSException;
import com.trs.hybase.client.TRSInputRecord;
import com.trs.hybase.client.TRSRecord;
import com.damon.constPackage.HybaseConsts;
import com.damon.dao.TRSHybaseDao;
import com.damon.entity.WeiboSourceClassifyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class WeiboAddHybaseid {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    TRSConnection connection;

    @Value(value = "${source.classify.url}")
    private String sourceClassifyUrl;
    @Value(value = "${source.classify.port}")
    private String sourceClassifyPort;

    private static final String readColumns = "HYBASE_ID;IR_UID";
    private static final String DB_NAME = "weibo_formal_20160801";
    private static final String IR_UID = "IR_UID";
    private static final String HYBASE_ID = "HYBASE_ID";
    private static final String IR_ADDITION_CHAR1 = "IR_ADDITION_CHAR1";

    public List<TRSRecord> fetchData(){
        String searchValue = HybaseConsts.SEARCH_VALUE;
        List<TRSRecord> records = TRSHybaseDao.getTrsRecords(connection,DB_NAME,readColumns,searchValue,"-DOCPUBTIME");
        if (records != null && records.size() != 0){
            log.info(String.format("fetch data size= [%s]", records.size()));
        } else {
            log.info("fetch data size = [0]");
        }
        return records;
    }

    /**
     * 该方法用于给hybaseid进行赋值
     * @param records
     * @return
     */
    public List<TRSInputRecord> processData(List<TRSRecord> records) {
        if (records == null || records.size() == 0)
            return null;
        List<TRSInputRecord> inputRecords = new ArrayList<>();
        for (TRSRecord record:records){
            TRSInputRecord inputRecord = new TRSInputRecord();
            try {
                String weiboId = record.getString(IR_UID);
                if(!StringUtils.isNullOrEmpty(weiboId)) {
                    String catagory = restTemplate.getForObject("http://{url}:{port}/weibo/pagelist/{weiboid}",
                            String.class, sourceClassifyUrl, sourceClassifyPort,weiboId);
                    if (catagory.length() > 2) {
                        ObjectMapper mapper = new ObjectMapper();
                        WeiboSourceClassifyBean model = mapper.readValue(catagory, WeiboSourceClassifyBean[].class)[0];
                        if (model != null) {
                            inputRecord.addColumn(HYBASE_ID,model.getSource() + model.getId());
                        }
                    }
                } else {
                    log.error(String.format("weibo source classify scheduleJob error! msg:[%s]", "weiboid is null"));
                }
            } catch (Exception e) {
                log.error(String.format("weibo source classify scheduleJob error!msg:[%s]", e));
            }
            try {
                inputRecord.addColumn(IR_ADDITION_CHAR1, HybaseConsts.H);
            } catch (TRSException e) {
                log.error(String.format("weibo addColumn error!msg:[%s]", e));
            }finally {
                inputRecord.setUid(record.getUid());
                inputRecords.add(inputRecord);
            }
        }
        return inputRecords;
    }

    public void updateWeiboTrsData(List<TRSInputRecord> inputRecords){
        if (inputRecords == null || inputRecords.size() == 0){
            log.info("inputRecords size empty or null");
            return;
        } else {
            try {
                long result = TRSHybaseDao.updateRecordList(connection,inputRecords,DB_NAME);
                log.info(String.format("update weibo formal dataBase success!,size=" + result));
            } catch (TRSException e) {
                log.error(String.format("weibo update error!msg:[%s]", e));
            }
        }
    }

    private String searchValueStr(){
        String content = "*:* HYBASE_ID:\"\" NOT IR_ADDITION_CHAR1:h";
        return content;
    }
}
