package com.iisi.web.filedelete;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.FileDeleteDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.fileDelete.FileDeleteService;
import com.iisi.api.model.FileData;
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
	
	private StreamedContent file;
	
	@PostConstruct
	public void init(){
		dto = new FileDeleteDTO();
	}
	
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
	
	public void downloadFile(FileData data){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String directory = externalContext.getInitParameter("uploadDirectory");
		File fileDir = new File(directory);
		String path = fileDir.getAbsolutePath();
		
//		String path = externalContext.getRealPath("/upload");
		String fileName = data.getImageId() + ".jpg";
		String filePath = path + File.separator + data.getList() + File.separator+ fileName;
		System.out.println("filePath = " + filePath);
				
	    File result = new File(filePath);
	    
	    System.out.println("result.exists() = " + result.exists());
	    
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
	}
	
	public void deleteFile(FileData data){
		dto.setFile(data);	
		
//		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
////		String path = externalContext.getRealPath("/upload");
//		String directory = externalContext.getInitParameter("uploadDirectory");
		String directory = FileSysUtils.getUploadInitDir();
		
		File fileDir = new File(directory);
		String path = fileDir.getAbsolutePath();
		
		String fileName = data.getImageId() + ".jpg";
		String filePath = path + File.separator + data.getList() + File.separator+ fileName;
		System.out.println("filePath = " + filePath);
		
		File result = new File(filePath);
		    
		System.out.println("result.exists() = " + result.exists());
		
		service.doDelete(dto);
		
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
//		RequestContext.getCurrentInstance().update("resultList");
	}
	
	private void verifyData(){	
		FacesContext context = FacesContext.getCurrentInstance();
		//類型
		if(ConstantMethod.verifyColumn(dto.getType())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_TYPE));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_TYPE);
		}
		//日期區間-起
		if(ConstantMethod.verifyColumn(dto.getStartDate().toString())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_START_DATE));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_START_DATE);
		}
		//日期區間-迄
		if(ConstantMethod.verifyColumn(dto.getEndDate().toString())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_END_DATE));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_END_DATE);
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

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}	
}
