package com.iisi.core.loginlogquery;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import com.iisi.api.model.LoginLog;

public class TestPdfReport {
	
	public TestPdfReport(){
//		test();
		try{
			JasperPrint jasperPrint = null;
			String path = "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.jasper";
			
			List<LoginLog> loginLogs = this.getLoginlogs();
			
			HashMap<String, Object> params = new HashMap<String, Object>();
					
			
			jasperPrint = JasperFillManager.fillReport(path, new HashMap(), new JRBeanCollectionDataSource(loginLogs));
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
		}catch(JRException e){
			e.printStackTrace();
		}
	}
	
	
	private List<LoginLog> getLoginlogs(){
//		String[] columnNames = {"loginDate", "loginTime", "userId", "userName", "officeName", "inoutMark"};
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
	
//	private void test(){
////		String[] columnNames = {"loginDate", "loginTime", "userId", "userName", "officeName", "inoutMark"};
//		List<LoginLog> loginlogs = new ArrayList<LoginLog>();
//		
//		LoginLog loginlog = new LoginLog();
//		loginlog.setInOutMark("I");
//		loginlog.setLoginDate("1041222");
//		loginlog.setLoginTime("102800");
//		loginlog.setOfficeId("000");
//		loginlog.setUserId("test");
//		loginlog.setUserName("test");
//		
//		loginlogs.add(loginlog);
//		
//		loginlog = new LoginLog();
//		loginlog.setInOutMark("O");
//		loginlog.setLoginDate("1041222");
//		loginlog.setLoginTime("102900");
//		loginlog.setOfficeId("000");
//		loginlog.setUserId("test");
//		loginlog.setUserName("test");
//		
//		loginlogs.add(loginlog);
//		
//		
//		Field[] fields = loginlog.getClass().getDeclaredFields();
//		
////		try {
//			for(int i=0;i<fields.length;i++){
//				System.out.println(fields[i].getName());
//			}
////		} catch (SecurityException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (NoSuchFieldException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		
////		String[][] test = new String[10][10];
////		loginlogs.toArray(test);
//	}
	
	public static void main(String args[]){
		new TestPdfReport();
	}
}
