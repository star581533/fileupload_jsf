package com.iisi.api.menu;

public interface MenuService {
	
	public static final String ADD_USER = "/pages/secure/sys/addUser.xhtml?faces-redirect=true";
	
	public static final String FILE_DELETE = "/pages/secure/work/fileDelete.xhtml?faces-redirect=true";
	
	public static final String FILE_QUERY = "/pages/secure/work/fileQuery.xhtml?faces-redirect=true";
	
	public static final String FILE_UP_LOAD = "/pages/secure/work/fileUpload.xhtml?faces-redirect=true";
	
	public static final String LOGIN_LOG_QUERY = "/pages/secure/sys/loginLogQuery.xhtml?faces-redirect=true";
	
	public static final String OPERATION_LOG_QUERY = "/pages/secure/sys/operationLogQuery.xhtml?faces-redirect=true";
	
	public static final String QUERY_USER = "/pages/secure/sys/queryUser.xhtml?faces-redirect=true";
	
	public static final String UPDATE_PWD = "/pages/secure/work/updatePwd.xhtml?faces-redirect=true";
	
	public static final String UPDATE_USER = "/pages/secure/sys/updateUser.xhtml?faces-redirect=true";
	
	public static final String LOGIN = "/pages/unsecure/login.xhtml?faces-redirect=true";
	
	public static final String INDEX = "/pages/secure/work/index.xhtml?faces-redirect=true";
}
