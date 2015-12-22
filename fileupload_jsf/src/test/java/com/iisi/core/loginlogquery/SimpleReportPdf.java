package com.iisi.core.loginlogquery;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class SimpleReportPdf {

	DefaultTableModel tableModel;
	
	public static void main(String args[]){
		
		new SimpleReportPdf();
	}
	
	public SimpleReportPdf(){
		String str = this.getClass().getResource("../../../").getFile();
		
		String name = "reports/LoginLog.jrxml";
		
		Class clazz = this.getClass();
		URLClassLoader loader = (URLClassLoader)clazz.getClassLoader();
		URL url = loader.getResource(name);
//		URL url = loader.findResource(name);
		System.out.println("Path = " + url.getPath());
		
		JasperPrint jasperPrint = null;
		TableModelData();
		try{
			String path = "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.jasper";
			//Report路徑
//			JasperCompileManager.compileReportToFile(path);
			
			
			
			jasperPrint = JasperFillManager.fillReport(path, new HashMap(), new JRTableModelDataSource(tableModel));
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
		}catch(JRException e){
			e.printStackTrace();
		}
	}
	
	private void TableModelData(){
		String[] columnNames = {"loginDate", "loginTime", "userId", "userName", "officeName", "inoutMark"};
		String[][] data = {
				{"1041222", "102800", "test", "test", "000", "I"},
				{"1041222", "102900", "test", "test", "000", "O"}
		};
		tableModel = new DefaultTableModel(data, columnNames);
		
		
	}
}
