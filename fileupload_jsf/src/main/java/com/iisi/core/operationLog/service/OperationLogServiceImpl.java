package com.iisi.core.operationLog.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.OperationLogQueryDTO;
import com.iisi.api.model.OperationLog;
import com.iisi.api.model.OperationLogPrint;
import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.operationLog.OperationLogService;
import com.iisi.api.report.AbstractReport;
import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;
import com.iisi.core.report.PdfReport;
import com.iisi.core.report.XlsReport;
import com.iisi.core.utils.DateUtils;

@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory.getLogger(OperationLogServiceImpl.class);	
	
	@Autowired
	private transient DbFactory dbFactory;
	
	@Autowired
	private OperationLogComponent operationLogComponent;
	
	@Autowired
	private FileSysUtil fileSysUtil;
	
	private AbstractReport report;
	
	@Override
	public void getOperationLogList(OperationLogQueryDTO dto) {
		LOG.debug("************************* OperationLogServiceImpl getOperationLogList start *************************");
		StringBuilder operationContent = new StringBuilder();		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append("select * from operationLog where logdate between ? and ? ");
		
		String startDate = DateUtils.adToRocDate(dto.getStartDate());
		String endDate = DateUtils.adToRocDate(dto.getEndDate());
		params.add(startDate);
		params.add(endDate);
		
		operationContent.append("sLogDate=").append(startDate).append(",");
		operationContent.append("eLogDate=").append(endDate).append(",");
		
		if(!ConstantMethod.verifyColumn(dto.getOfficeId())){
			sql.append("and officeid = ? ");
			params.add(dto.getOfficeId());
			operationContent.append("officeId=").append(dto.getOfficeId()).append(",");
		}
		
		if(!ConstantMethod.verifyColumn(dto.getUserId())){
			sql.append("and userid = ? ");
			params.add(dto.getUserId());
			operationContent.append("userId=").append(dto.getUserId()).append(",");
		}
		
		if(!ConstantMethod.verifyColumn(dto.getUserName())){
			sql.append("and username = ? ");
			params.add(dto.getUserName());
			operationContent.append("userName=").append(dto.getUserName()).append(",");
		}
		
		if(!ConstantMethod.verifyColumn(dto.getType())){
			if(!dto.getType().equals("AL")){
				sql.append("and type = ? ");
				params.add(dto.getType());
			}
			operationContent.append("type=").append(dto.getType()).append(",");
		}
		
		DBSMain dbsMain = this.dbFactory.getDbsMain();
		
		List<OperationLog> operationLogs = (List<OperationLog>) dbsMain.query(params, sql.toString(), OperationLog.class);
		
		dto.setOperationLogs(operationLogs);
		
		this.setOperationLogPrint(dto);
		
		UserInfo userInfo = this.fileSysUtil.getUser();		
		
		OperationLog operationLog = new OperationLog();
		operationLog.setOfficeId(userInfo.getOfficeId());
		operationLog.setOperationContent(operationContent.toString());
		operationLog.setType("OQ");
		operationLog.setUserId(userInfo.getUserId());
		operationLog.setUserName(userInfo.getUserName());
		
		operationLogComponent.insertOperationLog(operationLog);
		
		LOG.debug("************************* OperationLogServiceImpl getOperationLogList end *************************");
	}
			
	/**
	 * 填入畫面中顯示值
	 * @param dto
	 */
	private void setOperationLogPrint(OperationLogQueryDTO dto){
		List<OperationLogPrint> operationLogPrints = new ArrayList<OperationLogPrint>();
		if(dto.getOperationLogs().size() > 0){
			for(OperationLog operationLog : dto.getOperationLogs()){
				OperationLogPrint operationLogPrint = new OperationLogPrint();
				operationLogPrint.setLogDate(operationLog.getLogDate());
				operationLogPrint.setLogTime(operationLog.getLogTime());
				operationLogPrint.setOfficeId(operationLog.getOfficeId());
				operationLogPrint.setOperationContent(operationLog.getOperationContent());
				operationLogPrint.setType(operationLog.getType());
				operationLogPrint.setUserId(operationLog.getUserId());
				operationLogPrint.setUserName(operationLog.getUserName());
				operationLogPrints.add(operationLogPrint);
			}
		}
		dto.setOperationLogPrints(operationLogPrints);
	}
	
	@Override
	public void doPrintPdf(OperationLogQueryDTO dto) {
		report = new PdfReport();
		report.print(dto.getOperationLogPrints(), dto.getReportPath(), "OperationLog.pdf", new HashMap());
	}

	@Override
	public void doPrintXls(OperationLogQueryDTO dto) {
		report = new XlsReport();
		report.print(dto.getOperationLogPrints(), dto.getReportPath(), "OperationLog.xls", new HashMap());
	}
	
	
	
}
