package com.iisi.core.loginlogquery;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.iisi.api.model.LoginLog;

public class TestMainReport {
	
	private AbstractReport xlsReport;
	
	private AbstractReport pdfReport;
		
	private String path = "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.jasper";
	
	public TestMainReport(){
		List<LoginLog> loginlogs = this.getLoginlogs();
		xlsReport = new XlsReport();
		pdfReport = new PdfReport();
		
		xlsReport.print(loginlogs, path);
		pdfReport.print(loginlogs, path);
	}
		
	private List<LoginLog> getLoginlogs(){
		List<LoginLog> loginlogs = new ArrayList<LoginLog>();
		
		LoginLog loginlog = new LoginLog();
		loginlog.setInOutMark("I");
		loginlog.setLoginDate("1041222");
		loginlog.setLoginTime("102800");
		loginlog.setOfficeId("000");
		loginlog.setUserId("test");
		loginlog.setUserName("test");
		
		loginlogs.add(loginlog);
		
		loginlog = new LoginLog();
		loginlog.setInOutMark("O");
		loginlog.setLoginDate("1041222");
		loginlog.setLoginTime("102900");
		loginlog.setOfficeId("000");
		loginlog.setUserId("test");
		loginlog.setUserName("test");
		
		loginlogs.add(loginlog);
		
		
		return loginlogs;
	}
	
	public static void main(String args[]){
		new TestMainReport();
	}
}
