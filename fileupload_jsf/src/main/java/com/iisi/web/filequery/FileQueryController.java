package com.iisi.web.filequery;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.FileQueryDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.fileQuery.FileQueryService;
import com.iisi.api.model.FileData;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.FileSysUtils;


@ManagedBean
@ViewScoped
public class FileQueryController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FileQueryDTO dto;
	
	@ManagedProperty(value="#{fileQueryService}")
	private FileQueryService fileQueryService;
		
	@PostConstruct
	public void init(){
		dto = new FileQueryDTO();
	}
	
	/**
	 * 查詢資料
	 */
	public void doQuery(){
		try{			
			this.verifyData();
			dto.setFiles(fileQueryService.getFileList(dto));
			System.out.println("size = " + dto.getFiles());
			
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
			final String log = "download imageId=" + data.getImageId() + ", fileName=" + file.getName();
			fileQueryService.insertLog(log);
			dto.setContentFile(file);
		}		
	}
	
	/**
	 * 資料驗證
	 */
	private void verifyData(){
		//類型
		if(StringUtils.isBlank(dto.getType())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_TYPE);
		}
		//起始日
		if(DateUtils.checkDateValue(dto.getStartDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_START_DATE);
		}
		//迄止日
		if(DateUtils.checkDateValue(dto.getEndDate())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_END_DATE);
		}
	}
	
	public FileQueryDTO getDto() {
		return dto;
	}

	public void setDto(FileQueryDTO dto) {
		this.dto = dto;
	}

	public FileQueryService getFileQueryService() {
		return fileQueryService;
	}

	public void setFileQueryService(FileQueryService fileQueryService) {
		this.fileQueryService = fileQueryService;
	}
		
}
