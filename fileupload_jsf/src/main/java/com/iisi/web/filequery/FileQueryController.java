package com.iisi.web.filequery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.FileOutputDTO;
import com.iisi.api.domain.FileQueryDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.fileQuery.FileQueryService;
import com.iisi.api.model.FileData;
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
	
	private StreamedContent file;
	
	private boolean checkAllBox;
		
	@PostConstruct
	public void init(){
		dto = new FileQueryDTO();
//		dto.setUser(Checker.getUser());	
	}
	
	public void doQuery(){
		try{			
			this.verifyData();
			dto.setFiles(fileQueryService.getFileList(dto));
			System.out.println("size = " + dto.getFiles());
			this.setTempFiles();
			
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void setTempFiles(){
		if(dto.getFiles().size() > 0){
			List<FileOutputDTO> outputs = new ArrayList<FileOutputDTO>();
			for(FileData file : dto.getFiles()){
				FileOutputDTO output = new FileOutputDTO();
				output.setClassNum(file.getClassNum());
				output.setDisPatchNum(file.getDisPatchNum());
				output.setFileName(file.getFileName());
				output.setGovernment(file.getGovernment());
				output.setOfficeId(file.getOfficeId());
				output.setSecret(file.getSecret());
				output.setUploadDate(file.getUploadDate());
				output.setUploadTime(file.getUploadTime());
				output.setUserId(file.getUserId());
				output.setUserName(file.getUserName());
				output.setSubject(file.getSubject());
				output.setImageId(file.getImageId());
				output.setList(file.getList());
				outputs.add(output);
			}
			dto.setOutputs(outputs);
		}
	}
	
	public void downloadFile(ActionEvent event){		
		String directory = FileSysUtils.getUploadInitDir();
		
		File fileDir = new File(directory);
		String path = fileDir.getAbsolutePath();
		
		UIComponent component = event.getComponent();
				
		String list = (String)component.getAttributes().get("list");		
		String imageId = (String)component.getAttributes().get("imageId");
		String fileName = (String)component.getAttributes().get("fileName");

		String imageName = imageId + ".jpg";
		String filePath = path + File.separator + list + File.separator+ imageName;
		System.out.println("filePath = " + filePath);
				
	    File result = new File(filePath);
	    
	    System.out.println("result.exists() = " + result.exists());
	    
	    if(result.exists()){
		    InputStream stream;
			try {
				stream = new FileInputStream(result.getAbsolutePath());
				file = new DefaultStreamedContent(stream, "image/jpg", fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
	    }else{
	    	throw new FileSysException(ConstantObject.WARN_MSG_INPUT_TYPE);
	    }
	}
	
	
	

//	public void downloadFile(FileData data){
////		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
////		String directory = externalContext.getInitParameter("uploadDirectory");
//		
//		String directory = FileSysUtils.getUploadInitDir();
//		
//		File fileDir = new File(directory);
//		String path = fileDir.getAbsolutePath();
//		
//		String fileName = data.getImageId() + ".jpg";
//		String filePath = path + File.separator + data.getList() + File.separator+ fileName;
//		System.out.println("filePath = " + filePath);
//				
//	    File result = new File(filePath);
//	    
//	    System.out.println("result.exists() = " + result.exists());
//	    
//	    if(result.exists()){
//		    InputStream stream;
//			try {
//				stream = new FileInputStream(result.getAbsolutePath());
//				file = new DefaultStreamedContent(stream, "image/jpg", data.getFileName());
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}	
//	    }else{
//	    	throw new FileSysException(ConstantObject.WARN_MSG_INPUT_TYPE);
//	    }
//	}
	
	public void verifyData(){
		FacesContext context = FacesContext.getCurrentInstance();
		//類型
		if(null == dto.getType() || dto.getType().length() == 0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_TYPE));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_TYPE);
		}
		//起始日
		if(null == dto.getStartDate() || dto.getStartDate().toString().length() == 0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_START_DATE));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_START_DATE);
		}
		//迄止日
		if(null == dto.getEndDate() || dto.getEndDate().toString().length() == 0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_END_DATE));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_END_DATE);
		}
	}
	
	/**
	 * 處理畫面中Header的CheckBox動作
	 */
//	public void selectAll(){
//		System.out.println("================this.checkAllBox : " + this.checkAllBox);
//		this.setCheckAll(this.checkAllBox);
//	}
		
	/**
	 * 將Header的CheckBox動作，放入各個Row的CheckBox
	 * @param bool
	 */
//	private void setCheckAll(boolean bool){
//		for(FileOutputDTO output : dto.getOutputs()){
//			output.setCheckBoxAll(bool);
//		}
//	}

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

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public boolean isCheckAllBox() {
		return checkAllBox;
	}

	public void setCheckAllBox(boolean checkAllBox) {
		this.checkAllBox = checkAllBox;
	}
		
}
