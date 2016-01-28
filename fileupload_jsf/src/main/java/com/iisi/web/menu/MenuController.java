package com.iisi.web.menu;



import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;






import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.menu.MenuService;
import com.iisi.api.security.UserInfo;

@ManagedBean
@RequestScoped
public class MenuController {
	
	private MenuModel model;
	
	private String userName;
	
	private boolean roleView;
		
	@PostConstruct
	public void init(){
		FacesContext context = FacesContext.getCurrentInstance();		
		this.setUserName(context.getExternalContext().getRemoteUser());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserInfo userInfo = (UserInfo)auth.getPrincipal();
		
		if(userInfo.getRoleId().equals(ConstantObject.ONE)){
			this.setRoleView(true);
		}else{
			this.setRoleView(false);
		}
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

}
