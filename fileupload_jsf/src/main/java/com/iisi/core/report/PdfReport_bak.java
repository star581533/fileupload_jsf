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
 * @author 1104611
 *
 */
public class PdfReport_bak extends AbstractReport{

	@Override
	public void print(List<?> lists, String reportPath, String reportName,  Map<String, Object> map) {		
		try{
			String tempReportPath = getExternalContext().getRealPath(reportPath);
			jasperPrint = JasperFillManager.fillReport(tempReportPath, map, new JRBeanCollectionDataSource(lists));
						
			HttpServletResponse response = (HttpServletResponse) getExternalContext().getResponse();
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Content-disposition", "attachment; filename=" + reportName);
			response.setContentType("application/pdf");
						
			ServletOutputStream outputStream = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);			
			outputStream.write(baos.toByteArray());
			streamClose();
			FacesContext.getCurrentInstance().responseComplete();
			
			
			//OK
//			String  reportPath=  FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
//			System.out.println("reportPath = " + reportPath);
//			jasperPrint = JasperFillManager.fillReport(reportPath, map, new JRBeanCollectionDataSource(lists));
//			 HttpServletResponse httpServletResponse=(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
//			 httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
//			 
////			 FacesContext.getCurrentInstance().responseComplete();
//			 
//			 ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream();
//			 JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
//			 System.out.println("All done the report is done");
//			   servletOutputStream.flush();
//			   servletOutputStream.close();
//			 FacesContext.getCurrentInstance().responseComplete();
		}catch(JRException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
