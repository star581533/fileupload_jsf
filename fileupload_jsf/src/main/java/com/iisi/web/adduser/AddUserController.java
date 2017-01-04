package com.iisi.web.adduser;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.iisi.api.addUser.AddUserService;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.AddUserDTO;
import com.iisi.api.execption.FileSysException;

@ManagedBean
//@RequestScoped
@ViewScoped
public class AddUserController implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	private AddUserDTO dto;
	
	private List<SelectItem> officeList = new ArrayList<SelectItem>();	
		
	@ManagedProperty(value="#{addUserService}")
	private AddUserService addUserService;
	
	private String tmpOfficeId;
		
	@PostConstruct
	public void init(){	
		dto = new AddUserDTO();
		dto.setOfficeId("000");
	}
	
	/**
	 * 新增使用者資料
	 */
	public void doSave(){		
		try{
			this.verifyData();
			
			if(dto.getUserCount() == 0){
				this.addUserService.doSave(dto);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "新增成功", "使用者新增成功"));
				RequestContext.getCurrentInstance().update("growl");
				dto = new AddUserDTO();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "新增失敗", "使用者資料已存在"));
				throw new FileSysException("資料已存在");
			}
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 資料驗證
	 */
	private void verifyData(){		
		//驗證使用者代碼
		if(StringUtils.isBlank(dto.getUserId())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_USER_ID);
		}else{
			this.addUserService.checkUser(dto);
			if(dto.getUserCount() > 0){
				throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.ERROR_MSG_USER_EXIST);
			}
		}
		//驗證使用者姓名
		if(StringUtils.isBlank(dto.getUserName())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_USERNAME);
		}			
	}

	/**
	 * 檢查使用者帳號是否存在
	 */
	public void userDataListener(){
		this.addUserService.checkUser(dto);		
		if(dto.getUserCount() > 0){
			dto.setUserConfirm("使用者帳號已存在");
		}else{
			dto.setUserConfirm("使用者帳號未使用");
		}
		dto.setOfficeId(getTmpOfficeId());
	}

	public void officeListener(){
		this.setTmpOfficeId(dto.getOfficeId());
	}
	
	public AddUserDTO getDto() {
		return dto;
	}

	public void setDto(AddUserDTO dto) {
		this.dto = dto;
	}

	public List<SelectItem> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<SelectItem> officeList) {
		this.officeList = officeList;
	}

	public AddUserService getAddUserService() {
		return addUserService;
	}

	public void setAddUserService(AddUserService addUserService) {
		this.addUserService = addUserService;
	}

	public String getTmpOfficeId() {
		return tmpOfficeId;
	}

	public void setTmpOfficeId(String tmpOfficeId) {
		this.tmpOfficeId = tmpOfficeId;
	}	
	
	
}
