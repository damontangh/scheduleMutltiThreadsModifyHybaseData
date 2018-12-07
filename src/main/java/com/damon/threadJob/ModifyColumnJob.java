package com.damon.threadJob;

import com.trs.hybase.client.TRSConnection;
import com.trs.hybase.client.TRSInputRecord;
import com.trs.hybase.client.TRSRecord;
import com.damon.dao.TRSHybaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 修改数据库某些字段的值
 */
@Slf4j
@Component
public class ModifyColumnJob {
    @Autowired
    TRSConnection connection;

    private static final String readColumns = "TITLE_CNML;TITLE_AUTO_CNML;CONTENT_CNML;CONTENT_RULE_CNML;KEYWORD_RULE_CNML;CATALOG_AREA;TITLE_AREA;KEYWORD_RULE_AREA;IR_EMOTIONAL_VALUE;IR_ABSTRACT;MD5TAG;IR_KEYWORDS";
    private static final String DB_NAME = "mlf_http_product_original_20160321";
    private static final String COLUMN_ONE = "SECOND_MARK_SUCCESS";
    private static final String COLUMN_TWO = "SECOND_MARK_EXCEPTION";

    public List<TRSRecord> fetchData(){
        String searchValue = searchValueStr();
        List<TRSRecord> records = TRSHybaseDao.getTrsRecords(connection,DB_NAME,readColumns,searchValue,"-DOCPUBTIME");
        if (records != null && records.size() != 0){
            log.info(String.format("fetch data size= [%s]", records.size()));
        }else {
            log.info("fetch data size=0");
        }
        return records;
    }

    public void updateData(List<TRSRecord> records){
        if (records == null || records.size() == 0){
            log.debug("records empty");
            return;
        }
        List<TRSInputRecord> inputRecords = new ArrayList<>();
       for (TRSRecord record:records){
           TRSInputRecord inputRecord = new TRSInputRecord();
           try {
                   String uid = record.getUid();
                   inputRecord.setUid(uid);
                   String[] arr = readColumns.split(";");
                   for (int i = 0; i < arr.length; i++) {
                       inputRecord.addColumn(arr[i],"");
                   }
                   inputRecord.addColumn(COLUMN_ONE,"0");
                   inputRecord.addColumn(COLUMN_TWO,"0");
                   inputRecord.addColumn("ADDITION_CHAR3","h");
                   inputRecords.add(inputRecord);
           } catch (Exception e) {
               log.error(String.format("get column or add column error! msg:[%s]",e));
           }
       }
       long result = 0;
        try {
            log.info("ready to update data...");
            result = TRSHybaseDao.updateRecordList(connection,inputRecords,DB_NAME);
            log.info(String.format("update size = [%s]",result));
        } catch (Exception e) {
            log.error(String.format("update error! msg:[%s]",e));
        }
    }

    private String searchValueStr(){
        String content = "DOCPUBTIME:[* TO 2018.02.28} NOT ADDITION_CHAR3:h";
        return content;
    }
}
