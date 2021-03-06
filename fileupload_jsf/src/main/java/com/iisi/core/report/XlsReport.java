package com.iisi.core.report;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import com.iisi.api.report.AbstractReport;
import com.iisi.api.report.ReportType;

/**
 * 列印XLS
 * @author 1104611
 *
 */
public class XlsReport extends AbstractReport {

	@Override
	public void print(List<?> lists, String reportPath, String reportName,  Map<String, Object> map) {
		try{		
			this.setJasperReportData(reportPath, map, lists);
			this.getResponseAndSetContent(reportName, ReportType.XLS);
			
			FacesContext.getCurrentInstance().responseComplete();
			//製出Excel			
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream); 
			exporter.exportReport();
			
			streamClose();
			
			FacesContext.getCurrentInstance().getResponseComplete();
		}catch(JRException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
