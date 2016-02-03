package com.iisi.web.menu;



import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.loginout.LoginOutService;
import com.iisi.api.menu.MenuService;
import com.iisi.api.security.UserInfo;

@ManagedBean
@RequestScoped
public class MenuController {
	
	private MenuModel model;
	
	private String userName;
	
	private boolean roleView;
	
	@ManagedProperty(value="#{loginOutService}")
	private LoginOutService loginOutService;
		
	@PostConstruct
	public void init(){
		FacesContext context = FacesContext.getCurrentInstance();
		//取得使用者姓名
		this.setUserName(context.getExternalContext().getRemoteUser());
		
		UserInfo userInfo = this.getUserInfo();
		if(userInfo.getRoleId().equals(ConstantObject.ONE)){
			this.setRoleView(true);
		}else{
			this.setRoleView(false);
		}
	}
	
	/**
	 * 登出
	 */
	public void logOut(){
		try {
			ExternalContext context  = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest request = (HttpServletRequest)context.getRequest();
			String url = request.getContextPath() + "/j_spring_security_logout";
			
			UserInfo userInfo = this.getUserInfo();
			
			loginOutService.insertLoginOut(userInfo, ConstantObject.UPPER_CASE_O);
			
			//登出時導頁
			context.redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得使用者資料
	 * @return
	 */
	private UserInfo getUserInfo(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (UserInfo)auth.getPrincipal();
	}
	
	public String addUser(){
		return MenuService.ADD_USER;
	}
	
	public String fileDelete(){
		return MenuService.FILE_DELETE;
	}
	
	public String fileQuery(){
		return MenuService.FILE_QUERY;
	}
	
	public String fileUpload(){
		return MenuService.FILE_UP_LOAD;
	}
	
	public String loginLogQuery(){
		return MenuService.LOGIN_LOG_QUERY;
	}
	
	public String operationLogQuery(){
		return MenuService.OPERATION_LOG_QUERY;
	}
	
	public String queryUser(){
		return MenuService.QUERY_USER;
	}
	
	public String updatePwd(){
		return MenuService.UPDATE_PWD;
	}
	
	public String updateUser(){
		return MenuService.UPDATE_USER;
	}
	
	public String login(){
		return MenuService.LOGIN;
	}
	
	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isRoleView() {
		return roleView;
	}

	public void setRoleView(boolean roleView) {
		this.roleView = roleView;
	}

	public LoginOutService getLoginOutService() {
		return loginOutService;
	}

	public void setLoginOutService(LoginOutService loginOutService) {
		this.loginOutService = loginOutService;
	}

}
