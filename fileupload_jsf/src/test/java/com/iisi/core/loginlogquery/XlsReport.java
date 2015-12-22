package com.iisi.core.loginlogquery;

import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class XlsReport extends AbstractReport {

	@Override
	public void print(List<?> lists, String path) {
		try{
			JasperPrint jasperPrint = null;
			
			String printFileName = null;
			
//			String path = "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.jasper";
			printFileName = JasperFillManager.fillReportToFile(path, new HashMap(), new JRBeanCollectionDataSource(lists));
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

}
