package com.iisi.web.updateuser;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.UpdateUserDTO;
import com.iisi.api.updateUser.UpdateUserService;
import com.iisi.core.utils.FileSysUtils;
import com.iisi.core.utils.PropertiesContent;


@ManagedBean
@ViewScoped
public class UpdateUserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UpdateUserDTO dto;
	
	@ManagedProperty(value = "#{updateUserService}")
	private UpdateUserService service;
	
	@PostConstruct
	public void init(){
		dto = new UpdateUserDTO();
		this.getWebParameUserId();		
		dto.setOfficeName(FileSysUtils.getProperties("office.properties", dto.getUser().getOfficeId()));
	}
	
	public void doPwdReset(){
		dto.setResetFlag(ConstantObject.UPPER_CASE_Y);
	}
	
	public void doReset(){
		dto = new UpdateUserDTO();
		this.getWebParameUserId();
	}
	
	public void doUpdate(){
		service.doUpdate(dto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "更新成功", "使用者資料更新成功"));
		RequestContext.getCurrentInstance().update("growl");
	}
	
	/**
	 * 從Web上取得User資料
	 */
	private void getWebParameUserId(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String userId = request.getParameter("id");
		String officeId = request.getParameter("officeid");
		this.queryUser(userId, officeId);
	}
	
	/**
	 * 查詢使用者資料
	 * @param userId
	 * @param officeId
	 */
	private void queryUser(String userId, String officeId){
		dto.setUserId(userId);
		dto.setOfficeId(officeId);		
		this.service.doQuery(dto);
	}

	public UpdateUserDTO getDto() {
		return dto;
	}

	public void setDto(UpdateUserDTO dto) {
		this.dto = dto;
	}

	public UpdateUserService getService() {
		return service;
	}

	public void setService(UpdateUserService service) {
		this.service = service;
	}
}
