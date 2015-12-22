package com.iisi.core.loginlogquery;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class SimpleReportXls {
	
	DefaultTableModel tableModel;
	
	public SimpleReportXls(){
		try{
			JasperPrint jasperPrint = null;
			TableModelData();
			String printFileName = null;
			
			String path = "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.jasper";
			printFileName = JasperFillManager.fillReportToFile(path, new HashMap(), new JRTableModelDataSource(tableModel));
//			jasperPrint = JasperFillManager.fillReport(path, new HashMap(), new JRTableModelDataSource(tableModel));
			
			System.out.println("printFileName = " + printFileName);
			
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
	                  "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.xls");
			exporter.exportReport();
		}catch(JRException e){
			e.printStackTrace();
		}

	}
	
	public static void main(String args[]){
		SimpleReportXls xls = new SimpleReportXls();
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
