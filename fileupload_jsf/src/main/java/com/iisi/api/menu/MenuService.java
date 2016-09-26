package com.iisi.api.menu;

import org.apache.commons.lang3.StringUtils;

public enum MenuService {

	ADD_USER("secure/work", "addUser.xhtml"), //
	FILE_DELETE("secure/work", "fileDelete.xhtml"), //
	FILE_QUERY("secure/work", "fileQuery.xhtml"), //
	FILE_UP_LOAD("secure/work", "fileUpload.xhtml"), //
	LOGIN_LOG_QUERY("secure/sys", "loginLogQuery.xhtml"), //
	OPERATION_LOG_QUERY("secure/sys", "operationLogQuery.xhtml"), //
	QUERY_USER("secure/sys", "queryUser.xhtml"), //
	UPDATE_PWD("secure/work", "updatePwd.xhtml"), //
	UPDATE_USER("secure/sys", "updateUser.xhtml"), //
	LOGIN("unsecure", "login.xhtml"), //
	INDEX("secure/work", "index.xhtml"), //
	;
	
	private String packageName;
	
	private String pageName;
		
	private MenuService(final String packageName, final String pageName) {
		this.packageName = packageName;
		this.pageName = pageName;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}	
	
	public static String lookupMenuPage(final MenuService chooseMenu){
		String menuPage = "";
		for(MenuService menu : MenuService.values()){
			if(StringUtils.equals(chooseMenu.name(), menu.name())){
				final String webPath = String.format("/pages/%s/%s?faces-redirect=true", menu.getPackageName(), menu.getPageName());
				menuPage = webPath;
			}
		}
		return menuPage;
	}
	

//	
//	public static final String FILE_DELETE	        = "/pages/secure/work/fileDelete.xhtml?faces-redirect=true";
//	public static final String FILE_QUERY           = "/pages/secure/work/fileQuery.xhtml?faces-redirect=true";
//	public static final String FILE_UP_LOAD         = "/pages/secure/work/fileUpload.xhtml?faces-redirect=true";
//	public static final String LOGIN_LOG_QUERY 		= "/pages/secure/sys/loginLogQuery.xhtml?faces-redirect=true";
//	public static final String OPERATION_LOG_QUERY 	= "/pages/secure/sys/operationLogQuery.xhtml?faces-redirect=true";
//	public static final String QUERY_USER 			= "/pages/secure/sys/queryUser.xhtml?faces-redirect=true";
//	public static final String UPDATE_PWD       	= "/pages/secure/work/updatePwd.xhtml?faces-redirect=true";
//	public static final String UPDATE_USER          = "/pages/secure/sys/updateUser.xhtml?faces-redirect=true";
//	public static final String LOGIN                = "/pages/unsecure/login.xhtml?faces-redirect=true";
//	public static final String INDEX                = "/pages/secure/work/index.xhtml?faces-redirect=true";
//	
}
