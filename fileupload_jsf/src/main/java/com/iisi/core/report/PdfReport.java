package com.iisi.core.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.iisi.api.report.AbstractReport;
import com.iisi.api.report.ReportType;

//http://sameh-nassar.blogspot.tw/2012/12/using-jasper-report-in-adf-application.html
//http://www.ramkitech.com/2011/11/jsf-jpa-jasperreports-ireport.html
//http://stackoverflow.com/questions/24677487/jasper-report-generation-and-downloading-pdf-in-jsf-primefaces

/**
 * 列印PDF
 * 若是有空看能否將程式改更好
 * @author 1104611
 *
 */
public class PdfReport extends AbstractReport{

	@Override
	public void print(List<?> lists, String reportPath, String reportName,  Map<String, Object> map) {		
		try{
			this.setJasperReportData(reportPath, map, lists);									
			this.getResponseAndSetContent(reportName, ReportType.PDF);
			
			FacesContext.getCurrentInstance().responseComplete();
			//產出PDF
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);			
			outputStream.write(baos.toByteArray());
			streamClose();
			FacesContext.getCurrentInstance().responseComplete();
		}catch(JRException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
