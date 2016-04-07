package com.iisi.api.component;

import com.iisi.api.model.OperationLog;

public interface OperationLogComponent {
	
//	/** 檔案上傳 */
//	public final static String FILE_UPLOAD = "FU";	
//	/** 檔案查詢 */
//	public final static String FILE_QUERY = "FQ";
//	/** 檔案刪除 */
//	public final static String FILE_DELETE = "FD";
//	/** 新增使用者 */
//	public final static String NEW_USER = "NU";
//	/** 修改使用者資料 */
//	public final static String MODIFY_USER = "MU";
//	/** 帳號查詢 */
//	public final static String ACCOUNT_QUERY = "AQ";
//	/** 密碼修改 */
//	public final static String PASSWORD_MODIFY = "PM";
//	/** 簽到/簽退紀錄查詢 */
//	public final static String LOGINLOG_QUERY = "LQ";
//	/** 操作紀錄查詢 */
//	public final static String OPERATION_QUERY = "OQ";
	
	public void insertOperationLog(OperationLog operationLog);
	
	public void insertOperationLog(String type, String operationContent);
}
