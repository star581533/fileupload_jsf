package com.iisi.core.report;

import java.util.List;
import java.util.Map;

import com.iisi.api.report.AbstractReport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class XlsReport extends AbstractReport {

	@Override
	public void print(List<?> lists, String path, String reportName,  Map<String, Object> map) {
		try{			
			String printFileName = JasperFillManager.fillReportToFile(path, map, new JRBeanCollectionDataSource(lists));
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
	                  reportName);
//			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
//	                  "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.xls");
			exporter.exportReport();
		}catch(JRException e){
			e.printStackTrace();
		}
	}

}
