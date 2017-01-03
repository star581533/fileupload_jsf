package com.iisi.web.filedelete;


import java.io.File;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.FileDeleteDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.fileDelete.FileDeleteService;
import com.iisi.api.model.FileData;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.FileSysUtils;


@ManagedBean
@ViewScoped
public class FileDeleteController implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FileDeleteDTO dto;
	
	@ManagedProperty(value="#{fileDeleteService}")
	private FileDeleteService service;
	
	@PostConstruct
	public void init(){
		dto = new FileDeleteDTO();
	}
	
	/**
	 * 查詢資料
	 */
	public void doQuery(){
		try{
			this.verifyData();			
			dto.setFiles(service.doQuery(dto));
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 下載檔案
	 * @param data
	 */
	public void downloadFile(FileData data){
		final StreamedContent file = FileSysUtils.downloadFile(data);
		
		if(file != null){
			String log = "download imageId=" + data.getImageId() + ", fileName=" + file.getName();
			service.insertLog(log);
			dto.setContentFile(file);
		}
	}
	
	/**
	 * 刪除檔案
	 * @param data
	 */
	public void deleteFile(FileData data){
		dto.setFile(data);	
		
		final String path = FileSysUtils.getFileDirPath();		
		final String fileName = data.getImageId();
		final String extensionName = FileSysUtils.getExtensionFile(data.getFileName());
		final String filePath = path + File.separator + data.getList() + File.separator+ fileName + extensionName;
				    		
		service.doDelete(dto);
		File result = new File(filePath);
		
		if(result.exists()){
			if(result.delete()){
				System.out.println("刪除成功");
			}else{
				System.out.println("刪除失敗");
			}				
		}else{
			System.out.println("檔案不存在");
		}	
		
		dto.setFiles(service.doQuery(dto));
	}
	
	/**
	 * 資料驗證
	 */
	private void verifyData(){	
		//類型
		if(StringUtils.isBlank(dto.getType())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_TYPE);
		}
		//日期區間-起
		if(DateUtils.checkDateValue(dto.getStartDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_START_DATE);
		}
		//日期區間-迄
		if(DateUtils.checkDateValue(dto.getEndDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_END_DATE);
		}	
	}

	public FileDeleteDTO getDto() {
		return dto;
	}

	public void setDto(FileDeleteDTO dto) {
		this.dto = dto;
	}

	public FileDeleteService getService() {
		return service;
	}

	public void setService(FileDeleteService service) {
		this.service = service;
	}
	
}
