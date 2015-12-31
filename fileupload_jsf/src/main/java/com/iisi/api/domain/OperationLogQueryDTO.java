package com.iisi.api.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iisi.api.model.OperationLog;
import com.iisi.api.model.OperationLogPrint;

public class OperationLogQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String officeData;
	
	private String officeId;
	
	private String officeName;
	
	private Date startDate;
	
	private Date endDate;
	
	private String userId;
	
	private String userName;
	
	private List<OperationLog> operationLogs;
	
	private List<OperationLogPrint> operationLogPrints;
	
	private ArrayList<OperationLog> testLogs;
	
	private ArrayList<OperationLogPrint> printLogs;
	
	private String dateStart;
	
	private String dateEnd;
	
	private String type;
	
	private String reportPath;
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");

	public String getOfficeData() {
		return officeData;
	}

	public void setOfficeData(String officeData) {
		this.officeData = officeData;
	}
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<OperationLog> getOperationLogs() {
		return operationLogs;
	}

	public void setOperationLogs(List<OperationLog> operationLogs) {
		this.operationLogs = operationLogs;
	}
	
	public String getDateStart() {
		return sf.format(startDate);
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return sf.format(endDate);
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
	public List<OperationLogPrint> getOperationLogPrints() {
		return operationLogPrints;
	}

	public void setOperationLogPrints(List<OperationLogPrint> operationLogPrints) {
		this.operationLogPrints = operationLogPrints;
	}
	
	public ArrayList<OperationLog> getTestLogs() {
		return testLogs;
	}

	public void setTestLogs(ArrayList<OperationLog> testLogs) {
		this.testLogs = testLogs;
	}

	public ArrayList<OperationLogPrint> getPrintLogs() {
		return printLogs;
	}

	public void setPrintLogs(ArrayList<OperationLogPrint> printLogs) {
		this.printLogs = printLogs;
	}

	public void splitOfficeData(String data){
		if(null != data && data.length() > 0){
			this.setOfficeId(data.substring(0, data.indexOf(":")));
			this.setOfficeName(data.substring(data.indexOf(":")+1));
		}else{
			this.setOfficeId("");
			this.setOfficeName("");
		}
	}
}
