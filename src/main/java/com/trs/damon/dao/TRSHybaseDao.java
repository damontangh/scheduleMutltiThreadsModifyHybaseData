package com.trs.damon.dao;

import com.trs.hybase.client.*;
import com.trs.hybase.client.params.OperationParams;
import com.trs.hybase.client.params.SearchParams;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TRSHybaseDao {

	public TRSRecord getSzbOriginalByDocId(String documentId){
		//TODO �˴���Ӧ����ԭʼ�� ��Ϊԭʼ���������Ψһ�Ĳ��Ҳ��ظ��ģ�ͨ��ԭʼ�����picture
//		String url = "http://10.200.74.13:5555/";
//		String username = "admin";
//		String password = "Trsadmin_2015_!@#";
//		String dbName = "mlf_szb_product_original_20160321";
//		TRSConnection conn = new TRSConnection(url, username, password, null);
//		TRSRecord record = TRSHybaseUtil.getTrsRecord(conn, dbName, documentId);
//		return record;
		return null;
	}
	
	public static List<String> categoryQuery(TRSConnection conn, String dbName,String searchValue,String defaultColumn,String categoryColumn){
		List<String> list = null;
		try {
			TRSResultSet category = conn.categoryQuery(dbName, searchValue, defaultColumn, categoryColumn, 9000);
	    	list = new ArrayList<>(); //���ڷ�������
	    	Map<String, Long> categoryMap = category.getCategoryMap();
	    	if (categoryMap == null) 
				return list;
			 Iterator<Entry<String, Long>> iter = categoryMap.entrySet().iterator();
		     while (iter.hasNext()) {
		         Entry<String, Long> entry = iter.next();
		         if(entry.getValue() > 1) {
		        	 list.add(entry.getKey());
		         }
		     }
		} catch (TRSException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<TRSRecord> getTrsRecords(TRSConnection conn, String dbName,String readColumns,String searchValue){
		List<TRSRecord> records = new ArrayList<TRSRecord>();
		SearchParams params = new SearchParams();
		params.setReadColumns(readColumns);
		params.setSortMethod("+DOCPUBTIME");
		TRSResultSet resultSet = null;
		if (conn != null) {
			try {
				resultSet = conn.executeSelect(dbName, searchValue, 0, 100, params);
				for(int i = 0; i < resultSet.size(); i++) {
					resultSet.moveNext();
					TRSRecord re = resultSet.get();
					records.add(re);
				}
		        return records;
			} catch (TRSException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static List<TRSRecord> getTrsRecords(TRSConnection conn, String dbName,String readColumns,String searchValue,String orderBy){
		List<TRSRecord> records = new ArrayList<TRSRecord>();
		SearchParams params = new SearchParams();
		params.setReadColumns(readColumns);
		if(!StringUtils.isEmpty(orderBy)){
			params.setSortMethod(orderBy);
		}
		TRSResultSet resultSet = null;
		if (conn != null) {
			try {
				resultSet = conn.executeSelect(dbName, searchValue, 0, 1000, params);
				for(int i = 0; i < resultSet.size(); i++) {
					resultSet.moveNext();
					TRSRecord re = resultSet.get();
					records.add(re);
				}
		        return records;
			} catch (TRSException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static long updateRecordList(TRSConnection conn, List<TRSInputRecord> updateList, String dbName) throws TRSException {
		if (!updateList.isEmpty()) {
			//List<TRSInputRecord> tempList = null;
			//for(TRSInputRecord record : updateList){
			//	tempList = new ArrayList<TRSInputRecord>();
			//  System.out.println("-----"+record.getUid());
			//	record.addColumn("SECOND_MARK_SUCCESS",1); //���뽫���δ��ı�ʶ��Ϊ0�����򲻽��ж��δ��
			//	tempList.add(record);
			//}
			OperationParams uParams = new OperationParams();
			uParams.setProperty("update.mode.replace", "false");
			TRSReport report = new TRSReport();
			conn.executeUpdate(dbName, updateList,uParams,report);
			return report.getUpdatedNum();
		}
		return 0;
	}
	/**
	 * Description: �������ݼ��� <BR>  
	 * @param conn
	 * @param recordList
	 * @param DBName
	 * @throws TRSException
	 */
	public static void insertRecordList(TRSConnection conn, 
			List<TRSInputRecord> recordList, String DBName) throws TRSException {
		OperationParams params = new OperationParams();
		params.setBoolProperty("insert.duplicate.override", true);// ��������
		conn.executeInsert(DBName, recordList, params, null);
		//��¼��д������
		conn.commitDatabase(DBName, null);
	}
	
	public static void deleteByUid(TRSConnection conn, List<String> uids, String dbName) throws TRSException{
		conn.executeDelete(dbName, uids);
		conn.commitDatabase(dbName, null);
		conn.close();
	}
	
	public static void deleteByUid(TRSConnection conn, String[] uids, String dbName) throws TRSException{
		conn.executeDelete(dbName, uids);
		conn.close();
	}
	
	public static void deleteBySearchValue(TRSConnection conn, String searchValue, String dbName) throws TRSException {
		List<String> uids = new ArrayList<>();
		List<TRSRecord> list = getTrsRecords(conn, dbName, "", searchValue, "");
		for(TRSRecord record : list){
			uids.add(record.getUid());
		}
		conn.executeDelete(dbName, uids);
		conn.close();
	}
}
