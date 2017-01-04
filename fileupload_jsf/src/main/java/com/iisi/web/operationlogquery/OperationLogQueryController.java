package com.iisi.web.operationlogquery;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.OperationLogQueryDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.operationLog.OperationLogService;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.FileSysUtils;


@ManagedBean
@ViewScoped
public class OperationLogQueryController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManagedProperty(value="#{operationLogService}")
	private OperationLogService operationLogService;
		
	private OperationLogQueryDTO dto;

	private final String REPORT_NAME = "OperationLog.jasper";
	
	@PostConstruct
	public void init(){
		dto = new OperationLogQueryDTO();
		String reportPath = FileSysUtils.getReportPathDir() + File.separator + this.REPORT_NAME;
		dto.setReportPath(reportPath);
	}
	
	/**
	 * 查詢資料
	 */
	public void doQuery(){
		try{
			this.verifyData();
			this.operationLogService.getOperationLogList(dto);	
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 資料驗證
	 */
	private void verifyData(){		
		//起始日
		if(DateUtils.checkDateValue(dto.getStartDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_START_DATE);
		}
		//迄止日
		if(DateUtils.checkDateValue(dto.getEndDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_END_DATE);
		}	
	}
	
	public void doPrintPdf(){
		this.operationLogService.doPrintPdf(dto);
	}
	
	public void doPrintXls(){
		this.operationLogService.doPrintXls(dto);
	}

	public OperationLogQueryDTO getDto() {
		return dto;
	}

	public void setDto(OperationLogQueryDTO dto) {
		this.dto = dto;
	}

	public OperationLogService getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}	
}