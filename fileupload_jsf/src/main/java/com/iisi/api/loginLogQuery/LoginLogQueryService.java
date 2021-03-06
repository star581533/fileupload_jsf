package com.iisi.api.loginLogQuery;

import com.iisi.api.domain.LoginLogQueryDTO;

public interface LoginLogQueryService {

	/**
	 * 取得簽到／簽退紀錄資料
	 * @param dto
	 */
	public void getLoginLogList(LoginLogQueryDTO dto);
	
	/**
	 * 列印PDF
	 * @param dto
	 */
	public void doPrintPdf(LoginLogQueryDTO dto);
	
	/**
	 * 列印XLS
	 * @param dto
	 */
	public void doPrintXls(LoginLogQueryDTO dto);
	
}
