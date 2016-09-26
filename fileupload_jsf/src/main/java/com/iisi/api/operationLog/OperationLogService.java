package com.iisi.api.operationLog;

import com.iisi.api.domain.OperationLogQueryDTO;


public interface OperationLogService {
	
	/**
	 * 取得操作紀錄資料
	 * @param dto
	 */
	public void getOperationLogList(OperationLogQueryDTO dto);
	
	/**
	 * 列印PDF報表
	 * @param dto
	 */
	public void doPrintPdf(OperationLogQueryDTO dto);
	
	/**
	 * 列印XLS報表
	 * @param dto
	 */
	public void doPrintXls(OperationLogQueryDTO dto);
}
