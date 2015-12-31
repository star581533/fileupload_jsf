package com.iisi.web.operationlogquery;

import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.OperationLogQueryDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.operationLog.OperationLogService;
import com.iisi.core.utils.FileSysUtils;


@ManagedBean
@ViewScoped
public class OperationLogQueryController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(OperationLogQueryController.class);

	private OperationLogQueryDTO dto;
	
	@ManagedProperty(value="#{operationLogService}")
	private OperationLogService operationLogService;
		
	private final String REPORT_NAME = "OperationLog.jasper";
	
	@PostConstruct
	public void init(){
		dto = new OperationLogQueryDTO();
		String reportPath = FileSysUtils.getReportPathDir() + File.separator + this.REPORT_NAME;
		dto.setReportPath(reportPath);
	}
	
	public void doQuery(){
		LOG.debug("************************* OperationLogQueryController doQuery start *************************");
		try{
			this.verifyData();
			this.operationLogService.getOperationLogList(dto);	
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
				
		LOG.debug("************************* OperationLogQueryController doQuery end *************************");
	}
	
	private void verifyData(){
		//起始日
		if(null == dto.getStartDate() || dto.getStartDate().toString().length() == 0){
			throw new FileSysException(ConstantObject.UPPER_CASE_X, ConstantObject.WARN_MSG_INPUT_START_DATE);
		}
		//迄止日
		if(null == dto.getEndDate() || dto.getEndDate().toString().length() == 0){
			throw new FileSysException(ConstantObject.UPPER_CASE_X, ConstantObject.WARN_MSG_INPUT_END_DATE);
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