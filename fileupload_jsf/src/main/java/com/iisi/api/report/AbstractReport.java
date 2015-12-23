package com.iisi.api.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.iisi.api.execption.FileSysException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class AbstractReport {
	
	public JasperPrint jasperPrint = null;	
	
	public ServletOutputStream outputStream;
	
	public abstract void print(List<?> lists, String reportPath, String reportName,  Map<String, Object> map);
	
	/**
	 * 取得畫面設定
	 * @return
	 */
	public ExternalContext getExternalContext(){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		return context;
	}

	/**
	 * 
	 * @param reportPath 報表路徑
	 * @param map 報表對應變數
	 * @param lists 資料
	 */
	public void setJasperReportData(String reportPath, Map<String, Object> map, List<?> lists){
		try{
			String tempReportPath = getExternalContext().getRealPath(reportPath);
			jasperPrint = JasperFillManager.fillReport(tempReportPath, map, new JRBeanCollectionDataSource(lists));
		}catch(JRException e){
			e.printStackTrace();
		}catch(Exception e){
			throw new FileSysException("產製報表失敗");
		}					
	}
	
	/**
	 * 取得Response並填入Response產報表內容
	 * @param reportName
	 * @param reportType
	 * @return
	 */
	public HttpServletResponse getResponseAndSetContent(String reportName, ReportType reportType){
		HttpServletResponse response = (HttpServletResponse) getExternalContext().getResponse();
		this.setReponseContentData(response, reportName, reportType);
		return response;
	}
	
	/**
	 * 依報表類型取出Web使用元素
	 * @param response
	 * @param reportName
	 * @param reportType
	 */
	private void setReponseContentData(HttpServletResponse response, String reportName, ReportType reportType){
		response.setHeader("Cache-Control", "max-age=0");
		response.setHeader("Content-disposition", "attachment; filename=" + reportName);
				
		switch(reportType){
			case PDF:
				response.setContentType("application/pdf");
				break;
			case XLS:
				response.setContentType("application/vnd.ms-excel");
				break;
			case DOC:
				response.setContentType("application/doc");
				break;
			case XML:
				response.setContentType("text/xml");
				break;
			default:
				break;
		}
		
		try{
			outputStream = response.getOutputStream();
		}catch(Exception e){
			throw new FileSysException("取得串流資料失敗");
		}
		
	}
	
	/**
	 * 關閉串流
	 */
	public void streamClose(){
		try {
			if(outputStream != null){
				outputStream.flush();	
				outputStream.close();
			}						
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
