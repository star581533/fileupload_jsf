package com.iisi.core.loginLogQuery.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.db.DBFactory;
import com.iisi.api.domain.LoginLogQueryDTO;
import com.iisi.api.loginLogQuery.LoginLogQueryService;
import com.iisi.api.model.LoginLog;
import com.iisi.api.model.LoginLogPrint;
import com.iisi.api.report.AbstractReport;
import com.iisi.core.report.PdfReport;
import com.iisi.core.report.XlsReport;
import com.iisi.core.utils.DateUtils;

@Service("loginLogQueryService")
public class LoginLogQueryServiceImpl implements LoginLogQueryService {

	@Autowired
	private DBFactory dbFactory;
	
	private AbstractReport report;
	
	@Autowired
	private OperationLogComponent operationComponent;
	
	
	@Override
	public void getLoginLogList(LoginLogQueryDTO dto) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		StringBuilder operationContent = new StringBuilder();
		
		sql.append("select * from loginlog where logindate between ? and ? ");
		
		String startDate = DateUtils.adToRocDate(dto.getStartDate()); 
		String endDate = DateUtils.adToRocDate(dto.getEndDate()); 
		params.add(startDate);
		params.add(endDate);
		
		operationContent.append("sLoginDate=").append(startDate).append(",");
		operationContent.append("eLoginDate=").append(endDate).append(",");
		
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
			operationContent.append("userName=").append(dto.getUserName());
		}
		
		List<LoginLog> loginLogs = (List<LoginLog>)dbFactory.query(params, sql.toString(), LoginLog.class);
		
		dto.setLoginLogs(loginLogs);
		
		this.setLoginLogPrint(dto);
		
		operationComponent.insertOperationLog("LQ", operationContent.toString());
	}
	
	private void setLoginLogPrint(LoginLogQueryDTO dto){
		List<LoginLogPrint> loginLogPrints = new ArrayList<LoginLogPrint>();
		if(dto.getLoginLogs().size() > 0){
			for(LoginLog loginLog : dto.getLoginLogs()){
				LoginLogPrint loginLogPrint = new LoginLogPrint();
				loginLogPrint.setInOutMark(loginLog.getInOutMark());
				loginLogPrint.setLoginDate(loginLog.getLoginDate());
				loginLogPrint.setLoginTime(loginLog.getLoginTime());
				loginLogPrint.setOfficeId(loginLog.getOfficeId());
				loginLogPrint.setRoleId(loginLog.getRoleId());
				loginLogPrint.setUserId(loginLog.getUserId());
				loginLogPrint.setUserName(loginLog.getUserName());
				loginLogPrints.add(loginLogPrint);
			}
		}
		dto.setLoginLogPrints(loginLogPrints);
	}

	@Override
	public void doPrintPdf(LoginLogQueryDTO dto) {
		report = new PdfReport();
		report.print(dto.getLoginLogPrints(), dto.getReportPath(), "LoginLog.pdf", new HashMap());		
	}

	@Override
	public void doPrintXls(LoginLogQueryDTO dto) {
		report = new XlsReport();
		report.print(dto.getLoginLogPrints(), dto.getReportPath(), "LoginLog.xls", new HashMap());
	}

}
