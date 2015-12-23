package com.iisi.core.report;

import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import com.iisi.api.report.AbstractReport;

public class XlsReport_bak extends AbstractReport {

	@Override
	public void print(List<?> lists, String reportPath, String reportName,  Map<String, Object> map) {
		
		try{			
			String tempReportPath = getExternalContext().getRealPath(reportPath);
			jasperPrint = JasperFillManager.fillReport(tempReportPath, map, new JRBeanCollectionDataSource(lists));
			
			HttpServletResponse response = (HttpServletResponse)getExternalContext().getResponse();
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Content-disposition", "attachment; filename=" + reportName);
			response.setContentType("application/vnd.ms-excel");
			
		 FacesContext.getCurrentInstance().responseComplete();
			
			ServletOutputStream outputStream = response.getOutputStream();				
			
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream); 
			
			exporter.exportReport();
			outputStream.flush();
			outputStream.close();
			FacesContext.getCurrentInstance().getResponseComplete();
		}catch(JRException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
