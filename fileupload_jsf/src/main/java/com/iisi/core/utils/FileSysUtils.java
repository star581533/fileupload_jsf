package com.iisi.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.model.FileData;

public class FileSysUtils {
	
	private static final String RESOURCES = "resources";
	
	/**
	 * 建立資料夾
	 * @param dirName
	 */
	public static void genDirectory(String dirName){
		File dir = new File(dirName);
		if(!dir.exists()){
			dir.mkdir();
		}
	}
	
	/**
	 * 建立有使用到路徑資料夾
	 * @param dirs
	 * @return String
	 */
	public static String genDirPath(List<String> dirs){
		StringBuilder dirNames = new StringBuilder();
		for(String dirName : dirs){
			dirNames.append(dirName).append(File.separator);
			genDirectory(dirNames.toString());
		}
		return dirNames.toString();
	}
	
	/**
	 * 取得上傳初始化路徑
	 * @return
	 */
	public static String getUploadInitDir(){
		//取得環境執行物件
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//利用externalContext取得指定物件
		ExternalContext externalContext = facesContext.getExternalContext();		
		//取得web.xml中所設定目錄
		String directory = externalContext.getInitParameter("uploadDirectory");
		
		return directory;
	}
	
	/**
	 * 取得畫面中REQUEST
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
		return request;
	}
	
	/**
	 * 報表存放路徑
	 * @return
	 */
	public static String getReportPathDir(){
		StringBuilder reportPath = new StringBuilder(File.separator + RESOURCES);
		reportPath.append(File.separator + "reports");
		return reportPath.toString();
	}
	
	/**
	 * 取得Properties資料
	 * @param properName
	 * @param code
	 * @return
	 */
	public static String getProperties(String properName, String code){
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		StringBuilder propertPath = new StringBuilder(File.separator + RESOURCES);
		propertPath.append(File.separator + "properties");
		propertPath.append(File.separator + properName);
		InputStream is = context.getResourceAsStream(propertPath.toString());
		
		PropertiesContent properties = new PropertiesContent();
		String str = properties.getPropertiesByCode(is, code);
		
		if(StringUtils.isBlank(str)){
			str = "";
		}
		return str;
	}
	
	/**
	 * 取得絕對路徑
	 * @return
	 */
	public static String getFileDirPath(){		
		final String directory = getUploadInitDir();
		final File fileDir = new File(directory);
		final String path = fileDir.getAbsolutePath();
		return path;
	}
	
	/**
	 * 下載檔案
	 * @param data FileData
	 * @return StreamedContent
	 */
	public static StreamedContent downloadFile(FileData data){		

		StreamedContent file = null;
		final String path = getFileDirPath();
		final String fileName = data.getImageId();
		final String extensionName = getExtensionFile(data.getFileName());
		final String filePath = path + File.separator + data.getList() + File.separator+ fileName + extensionName;				
	    final File result = new File(filePath);
	    
	    //檔案存在
	    if(result.exists()){
		    InputStream stream;
			try {
				stream = new FileInputStream(result.getAbsolutePath());
				file = new DefaultStreamedContent(stream, "image/jpg", data.getFileName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
	    }else{
	    	throw new FileSysException(ConstantObject.ERROR_MSG_FILE_NOT_EXIST);
	    }
		return file;
	}
	
	/**
	 * 取得副檔名
	 * @param fileName
	 * @return String
	 */
	public static String getExtensionFile(final String fileName){
		String extensionName = "";
		final int extensionSymbol = fileName.indexOf(".");
		if(extensionSymbol > 0){
			extensionName = fileName.substring(extensionSymbol);
		}
		return extensionName;
	}	
	
	/**
	 * 時間格式，hh:mm:ss
	 * @param hhmmss
	 * @return String
	 */
	public static String formatHhmmss(String hhmmss){
		return hhmmss.substring(0,2) + ":" + hhmmss.substring(2, 4) + ":" + hhmmss.substring(4, 6);
	}
	
	/**
	 * 日期格式，yyy/mm/dd
	 * @param yyymmdd
	 * @return String
	 */
	public static String formatYyymmdd(String yyymmdd){
		return yyymmdd.substring(0,3) + "/" + yyymmdd.substring(3,5) + "/" + yyymmdd.substring(5,7);
	}
}
