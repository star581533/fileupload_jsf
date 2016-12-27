//http://www.journaldev.com/3229/primefaces-fileupload-component-example-tutorial
package com.iisi.web.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.FileUploadDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.fileUpload.FileUploadService;
import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.FileSysUtils;



@ManagedBean
@ViewScoped
public class FileUploadController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FileUploadDTO dto;
	
//	private UploadedFile uploadedFile;
	
	@ManagedProperty(value="#{fileUploadService}")
	private FileUploadService service;
	
	@ManagedProperty(value="#{fileSysUtil}")
	private FileSysUtil fileSysUtil;
	
	@PostConstruct
	public void init(){		
		dto = new FileUploadDTO();			
	}
		
	public void uploadData(){
		try{
			//驗證
			this.verifyData();
			//傳檔
			this.sendFile();
			//寫值到DB
			service.doSave(dto);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "上傳成功", "檔案上傳成功"));
			
			dto = new FileUploadDTO();
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 驗證畫面資料
	 */
	private void verifyData(){
		//類型
		if(ConstantMethod.verifyColumn(dto.getType())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_TYPE);
		}
		//密件
		if(ConstantMethod.verifyColumn(dto.getSecret())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_SECRET);
		}
		//日期
		if(ConstantMethod.verifyColumn(dto.getDisPatchDate().toString())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_DATE);
		}
		//分類號
		if(ConstantMethod.verifyColumn(dto.getClassNum())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_CLASSNUM);
		}
		//公文文號
		if(ConstantMethod.verifyColumn(dto.getDisPatchNum())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_DISPATCHNUM);
		}
		//主旨
		if(ConstantMethod.verifyColumn(dto.getSubject())){
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_SUBJECT);
		}				
		//檔名
		if(ConstantMethod.verifyColumn(dto.getUploadFile().getFileName())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_FILE);
		}	
	}
	
	/**
	 * 將檔案上傳
	 */
	public void sendFile(){			
		String directory = FileSysUtils.getUploadInitDir();
		
		File file = new File(directory);
		//完整路徑
		String path = file.getAbsolutePath();
		
		System.out.println("path = " + path);
	
		UserInfo userInfo = this.fileSysUtil.getUser();
		String userId = userInfo.getUserId();
		String officeId = userInfo.getOfficeId();
		
		//建立會使用到目錄
		List<String> dirPaths = new ArrayList<String>();
		dirPaths.add(path);
		dirPaths.add(DateUtils.getNowYear());
		dirPaths.add(officeId);
		dirPaths.add(userId);
		
//		dto.setUploadFile(this.uploadedFile);		
		dto.setFullPath(FileSysUtils.genDirPath(dirPaths));		
		dto.setFilePath(DateUtils.getNowYear() + File.separator + officeId + File.separator + userId);
		
		//取得檔案名稱
		String fileName = dto.getUploadFile().getFileName();
		//以亂數改檔名
		dto.setImageId(DateUtils.getNowDate()+DateUtils.getNowTimeAndMicroSec());
		String serverName = dto.getImageId() + fileName.substring(fileName.lastIndexOf("."));
		
		System.out.println("serverName = " + serverName);
		System.out.println("dto.getFilePath() = " + dto.getFilePath());
		
		//上傳檔案 
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(new File(dto.getFullPath(), serverName));
			byte[] buffer = new byte[1024];
			
			int bulk;
			InputStream inputStream = dto.getUploadFile().getInputstream();
			while(true){
				bulk = inputStream.read(buffer);
				if(bulk < 0){
					break;
				}				
				fileOutputStream.write(buffer, 0, bulk);
				fileOutputStream.flush();
			}
			fileOutputStream.close();
			inputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
			
	public FileUploadDTO getDto() {
		return dto;
	}

	public void setDto(FileUploadDTO dto) {
		this.dto = dto;
	}

//	public UploadedFile getUploadedFile() {
//		return uploadedFile;
//	}
//
//	public void setUploadedFile(UploadedFile uploadedFile) {
//		this.uploadedFile = uploadedFile;
//	}

	public FileUploadService getService() {
		return service;
	}

	public void setService(FileUploadService service) {
		this.service = service;
	}

	public FileSysUtil getFileSysUtil() {
		return fileSysUtil;
	}

	public void setFileSysUtil(FileSysUtil fileSysUtil) {
		this.fileSysUtil = fileSysUtil;
	}
	
	
}
