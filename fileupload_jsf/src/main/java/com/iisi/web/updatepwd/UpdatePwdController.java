package com.iisi.web.updatepwd;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.UpdatePwdDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.menu.MenuService;
import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;
import com.iisi.api.updatePwd.UpdatePwdService;



@ManagedBean
@RequestScoped
public class UpdatePwdController implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{updatePwdService}")
	private UpdatePwdService service;
	
	@ManagedProperty(value="#{fileSysUtil}")
	private transient FileSysUtil fileSysUtil;

	private UpdatePwdDTO dto;
	
	@PostConstruct
	public void init(){				
		this.setDto();
	}
	
	/**
	 * 畫面載入使用者資料有誤導至登入畫面
	 * @return String
	 */
	private String error(){
		System.out.println("error");
		return MenuService.lookupMenuPage(MenuService.LOGIN);
	}
	
	/**
	 * 確定
	 */
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
	
	/**
	 * 驗證資料
	 */
	private void verifyData(){	
		//新密碼
		if(StringUtils.isBlank(dto.getNewPassWord())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_NEW_PASSWORD);
		}		
		//確認密碼
		if(StringUtils.isBlank(dto.getConfirmPassWord())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_CONFIRM_PASSWORD);
		}
		//比對新密碼與確認密碼要相同
		if(!ConstantMethod.compareTwoColumn(dto.getNewPassWord(), dto.getConfirmPassWord())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_NEW_CONFIRM_PASSWORD);
		}		
		//比對新舊密碼是否相同
		if(ConstantMethod.compareTwoColumn(dto.getOldPassWord(), dto.getNewPassWord())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_USER_PWD);
		}
		//舊密碼
		if(StringUtils.isBlank(dto.getOldPassWord())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_OLD_PASSWORD);
		}else{
			//比對畫面舊密碼與資料庫密碼是否相同
			if(!service.checkUserPassword(dto)){
				throw new FileSysException(ConstantObject.UPPER_CASE_W, "舊密碼輸入不正確");
			}
		}
	}
	
	/**
	 * 設定畫面預設值
	 */
	private void setDto(){
		dto = new UpdatePwdDTO();
		
		//取得使用者資料
		UserInfo userInfo = this.fileSysUtil.getUser();
		String userId = userInfo.getUsername();
		String officeId = userInfo.getOfficeId();

		if(userId != null){			
			dto.setUserId(userId);
			dto.setOfficeId(officeId);
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
