//http://www.mkyong.com/jsf2/jsf-2-0-spring-hibernate-integration-example/

package com.iisi.web.login;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iisi.api.component.UserDataComponent;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.LoginDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.menu.MenuService;
import com.iisi.api.model.User;
import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;
import com.iisi.core.security.SecurityUtils;

@ManagedBean(name="loginController")
@RequestScoped
public class LoginController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginDTO dto;
	
	private String message;	
		
	private String userName = null;
	
	private String userId = null;
	
	private String password = null;
	
	@ManagedProperty(value="#{authenticationManager}")
	private AuthenticationManager authenticationManager = null;
	
	private FacesContext context = null;
		
	@Autowired
	private transient UserDataComponent userDataComponent;
	
	@PostConstruct
	public void init(){
		dto = new LoginDTO();
	}
			
	/**
	 * 執行資料驗證
	 * @throws FileUploadException 
	 */
	public void verify() {
		context = FacesContext.getCurrentInstance();
		//檢核使用者帳號
		if(null == dto.getUserId() || dto.getUserId().length() == 0){
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_USER_ID));
			throw new FileSysException("W", ConstantObject.WARN_MSG_INPUT_USER_ID);
		}
		//檢核使用者密碼
		if(null == dto.getPassword() || dto.getPassword().length() == 0){
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_USER_PWD));
			throw new FileSysException("W",ConstantObject.WARN_MSG_INPUT_USER_PWD);
		}
	}
	
	/**
	 * 登入
	 * @return
	 */
	public String login(){						
		try{
			this.verify();
			Authentication auth = new UsernamePasswordAuthenticationToken(this.dto.getUserId(), SecurityUtils.getMD5(this.dto.getPassword()));
			Authentication result = authenticationManager.authenticate(auth);
			SecurityContextHolder.getContext().setAuthentication(result);								
		}catch(AuthenticationException e){			
			this.handleException(e);
			return "";		
		}catch(FileSysException e){
			e.printStackTrace();
			return "";
		}catch(Exception e){
//			throw new FileSysException(ConstantObject.ERROR_USER_LOGIN);
			this.handleException(e);
		}
		return MenuService.INDEX;
	}
	
	/**
	 * 登入例外處理(未來想辦法要整合進FileSysException中)
	 * @param exception
	 */
	private void handleException(Throwable exception){
		//http://www.java-tutorial.ch/java-server-faces/jsf-2-exception-handling-and-formatting
		String message = "";
		
		if(exception instanceof FileSysException){
			message = exception.getMessage();
		}else if(exception instanceof AuthenticationException){
			message = ConstantObject.ERROR_INPUT_USER_PASSWORD;
		}else{
			message = ConstantObject.ERROR_USER_LOGIN;
		}
		
		FacesMessage faceMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, faceMessage);
	}
	
	public void cancelButton(){
		dto = new LoginDTO();
	}

	public LoginDTO getDto() {
		return dto;
	}

	public void setDto(LoginDTO dto) {
		this.dto = dto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}	
}
