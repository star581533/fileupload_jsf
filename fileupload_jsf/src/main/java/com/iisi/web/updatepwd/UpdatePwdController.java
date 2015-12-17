package com.iisi.web.updatepwd;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.UpdatePwdDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.menu.MenuService;
import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;
import com.iisi.api.updatePwd.UpdatePwdService;
import com.iisi.core.security.UserUtil;



@ManagedBean
@RequestScoped
public class UpdatePwdController implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{updatePwdService}")
	private UpdatePwdService service;
	
	private UpdatePwdDTO dto;
	
	@ManagedProperty(value="#{fileSysUtil}")
	private transient FileSysUtil fileSysUtil;

	
	@PostConstruct
	public void init(){				
		this.setDto();
	}
	
	public String error(){
		System.out.println("error");
		return MenuService.LOGIN;
	}
	
	public void saveData(){
		try{
			this.verifyData();					
			service.updatePassword(dto);			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantObject.INFO_MSG_UPDATE_SUCCESS, ConstantObject.INFO_MSG_UPDATE_USER_PASSWORD));
			this.setDto();
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void verifyData(){	
		FacesContext context = FacesContext.getCurrentInstance();

		if(ConstantMethod.verifyColumn(dto.getOldPassWord())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_OLD_PASSWORD));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_OLD_PASSWORD);
		}else{
			//比對畫面舊密碼與資料庫密碼是否相同
			if(!service.checkUserPassword(dto)){
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.ERROR_INPUT, "舊密碼輸入不正確"));
				throw new FileSysException("舊密碼輸入不正確");
			}
		}
					
		if(ConstantMethod.verifyColumn(dto.getNewPassWord())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_NEW_PASSWORD));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_NEW_PASSWORD);
		}
		
		if(ConstantMethod.verifyColumn(dto.getConfirmPassWord())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_CONFIRM_PASSWORD));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_CONFIRM_PASSWORD);
		}
			
		
		//比對新密碼與確認密碼要相同
		if(!ConstantMethod.compareTwoColumn(dto.getNewPassWord(), dto.getConfirmPassWord())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_NEW_CONFIRM_PASSWORD));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_NEW_CONFIRM_PASSWORD);
		}
		
		//比對新舊密碼是否相同
		if(ConstantMethod.compareTwoColumn(dto.getOldPassWord(), dto.getNewPassWord())){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_USER_PWD));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_USER_PWD);
		}
	}
	
	private void setDto(){
		dto = new UpdatePwdDTO();
		
		UserInfo userInfo = this.fileSysUtil.getUser();
		String userId = userInfo.getUsername();
		String officeId = userInfo.getOfficeId();
		
		System.out.println("userId = " + userId);
		System.out.println("officeId = " + officeId);
		
		if(userId != null){			
			dto.setUserId(userId);
		}else{
			this.error();
		}
	}
	
	public UpdatePwdDTO getDto() {
		return dto;
	}

	public void setDto(UpdatePwdDTO dto) {
		this.dto = dto;
	}

	public UpdatePwdService getService() {
		return service;
	}

	public void setService(UpdatePwdService service) {
		this.service = service;
	}

	public FileSysUtil getFileSysUtil() {
		return fileSysUtil;
	}

	public void setFileSysUtil(FileSysUtil fileSysUtil) {
		this.fileSysUtil = fileSysUtil;
	}
	
}
