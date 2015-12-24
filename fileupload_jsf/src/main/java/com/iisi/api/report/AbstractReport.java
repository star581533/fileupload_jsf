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
	
	/**
	 * 列印報表
	 * @param lists 資料內容
	 * @param reportPath 報表路徑
	 * @param reportName 報表名稱
	 * @param map 報表內容對應變數
	 */
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
	 * @param map 報表內容對應變數
	 * @param lists 資料
	 */
	public void setJasperReportData(String reportPath, Map<String, Object> map, List<?> lists){
		try{
			//取得一個絕對路徑
			String tempReportPath = getExternalContext().getRealPath(reportPath);
			//把指定的報表填入資料後，產生報表物件並回傳
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
		//瀏覽器快取設定，max-age：快取存活時間
		response.setHeader("Cache-Control", "max-age=0");
		//瀏覽器強制下載此頁內容，attachment：附件檔
		response.setHeader("Content-disposition", "attachment; filename=" + reportName);
				
		switch(reportType){
			case PDF:
				//設定網頁編碼
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
			//瀏覽器進行位元組輸出，將資料寫至客戶端
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
