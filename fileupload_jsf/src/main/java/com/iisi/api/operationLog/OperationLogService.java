package com.iisi.api.operationLog;

import java.util.List;

import com.iisi.api.domain.OperationLogQueryDTO;
import com.iisi.api.model.OperationLog;

public interface OperationLogService {
	
	/**
	 * 取得操作紀錄資料
	 * @param dto
	 * @return List<OperationLog>
	 */
	public List<OperationLog> getOperationLogList(OperationLogQueryDTO dto);
	
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
