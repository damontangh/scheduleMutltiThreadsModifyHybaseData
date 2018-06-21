package com.trs.modifyData.threadJob;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.common.utils.StringUtils;
import com.trs.hybase.client.TRSConnection;
import com.trs.hybase.client.TRSException;
import com.trs.hybase.client.TRSInputRecord;
import com.trs.hybase.client.TRSRecord;
import com.trs.modifyData.constPackage.HybaseConsts;
import com.trs.modifyData.dao.TRSHybaseDao;
import com.trs.modifyData.entity.WebsiteSourceClassifyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class WebsiteAddHybaseid {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    TRSConnection connection;

    @Value(value = "${source.classify.url}")
    private String sourceClassifyUrl;
    @Value(value = "${source.classify.port}")
    private String sourceClassifyPort;

    private static final String readColumns = "HYBASE_ID;IR_SITENAME;DOCCHANNEL";
    private static final String DB_NAME = "website_formal_20160321";
    private static final String IR_SITENAME = "IR_SITENAME";
    private static final String DOCCHANNEL = "DOCCHANNEL";
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
                String siteName = record.getString(IR_SITENAME);
                String channel = record.getString(DOCCHANNEL);
                if(!StringUtils.isNullOrEmpty(siteName) && !StringUtils.isNullOrEmpty(channel)) {
                    String catagory = restTemplate.getForObject("http://{url}:{port}/website/pagelist/?website={website}&channel={channel}",
                            String.class, sourceClassifyUrl, sourceClassifyPort, siteName, channel);
                    if (catagory.length() > 2) {
                        ObjectMapper mapper = new ObjectMapper();
                        WebsiteSourceClassifyBean model = mapper.readValue(catagory, WebsiteSourceClassifyBean[].class)[0];
                        if (model != null) {
                            inputRecord.addColumn(HYBASE_ID,model.getSource() + model.getId());
                        }
                    }
                } else {
                    log.error(String.format("website source classify scheduleJob error! msg:[%s]", "website sitename or channel is null"));
                }
            } catch (Exception e) {
                log.error(String.format("website source classify scheduleJob error!msg:[%s]", e));
            }
            try {
                inputRecord.addColumn(IR_ADDITION_CHAR1, HybaseConsts.H);
            } catch (TRSException e) {
                log.error(String.format("website addColumn error!msg:[%s]", e));
            }finally {
                inputRecord.setUid(record.getUid());
                inputRecords.add(inputRecord);
            }
        }
        return inputRecords;
    }

    public void updateWebsiteTrsData(List<TRSInputRecord> inputRecords){
        if (inputRecords == null || inputRecords.size() == 0){
            log.info("inputRecords size empty or null");
            return;
        } else {
            try {
                long result = TRSHybaseDao.updateRecordList(connection,inputRecords,DB_NAME);
                log.info(String.format("update website formal dataBase success!,size=" + result));
            } catch (TRSException e) {
                log.error(String.format("website update error!msg:[%s]", e));
            }
        }
    }

    private String searchValueStr(){
        String content = "*:* HYBASE_ID:\"\" NOT IR_ADDITION_CHAR1:h";
        return content;
    }
}
