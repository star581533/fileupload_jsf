package com.iisi.web.loginlogquery;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;



import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.LoginLogQueryDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.loginLogQuery.LoginLogQueryService;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.FileSysUtils;

@ManagedBean
@ViewScoped
public class LoginLogQueryController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginLogQueryDTO dto;
	
	@ManagedProperty(value = "#{loginLogQueryService}")
	private LoginLogQueryService loginLogQueryService;
	
//	private String officeAll;	
	
	private final String REPORT_NAME = "LoginLog.jasper";
		
	@PostConstruct
	public void init(){
		dto = new LoginLogQueryDTO();
		final String reportPath = FileSysUtils.getReportPathDir() + File.separator + this.REPORT_NAME;
		dto.setReportPath(reportPath);		
	}

	/**
	 * 查詢資料
	 */
	public void doQuery(){
		try{
			this.verifyData();
			this.loginLogQueryService.getLoginLogList(dto);	
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 列印PDF報表
	 */
	public void doPrint(){
		this.loginLogQueryService.doPrintPdf(dto);
	}
	
	/**
	 * 列印XLS報表
	 */
	public void doPrintXls(){
		this.loginLogQueryService.doPrintXls(dto);
	}
	
	/**
	 * 驗證欄位
	 */
	public void verifyData(){
		//起始日
		if(DateUtils.checkDateValue(dto.getStartDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_START_DATE);
		}
		//迄止日
		if(DateUtils.checkDateValue(dto.getEndDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_END_DATE);
		}
	}

	public LoginLogQueryDTO getDto() {
		return dto;
	}

	public void setDto(LoginLogQueryDTO dto) {
		this.dto = dto;
	}

//	public String getOfficeAll() {
//		return officeAll;
//	}
//
//	public void setOfficeAll(String officeAll) {
//		this.officeAll = officeAll;
//	}

	public LoginLogQueryService getLoginLogQueryService() {
		return loginLogQueryService;
	}

	public void setLoginLogQueryService(LoginLogQueryService loginLogQueryService) {
		this.loginLogQueryService = loginLogQueryService;
	}	
}
