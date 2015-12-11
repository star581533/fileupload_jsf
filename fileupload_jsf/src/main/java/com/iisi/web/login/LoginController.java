//http://www.mkyong.com/jsf2/jsf-2-0-spring-hibernate-integration-example/

package com.iisi.web.login;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.LoginDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.menu.MenuService;
import com.iisi.api.model.User;
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
	
	private FacesContext context;
	
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
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_USER_ID);
//			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_USER_ID);
//			this.testException(new FileSysException(ConstantObject.WARN_MSG_INPUT_USER_ID));
		}
		//檢核使用者密碼
		if(null == dto.getPassword() || dto.getPassword().length() == 0){
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_USER_PWD));
			throw new FileSysException(ConstantObject.WARN_MSG_INPUT_USER_PWD);
//			this.testException(new FileSysException(ConstantObject.WARN_MSG_INPUT_USER_PWD));
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
//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.ERROR_INPUT, ConstantObject.ERROR_INPUT_USER_PASSWORD));
//			this.testException(e);
			throw new FileSysException("測試使用者登入錯誤");
//			e.printStackTrace();
//			return "";		
		}catch(FileSysException e){
			System.out.println("-----------------------FileSysException-----------------");
//			throw e;		
			e.printStackTrace();
			return "";
		}catch(Exception e){
			throw new FileSysException(ConstantObject.ERROR_USER_LOGIN);
		}
		return MenuService.INDEX;
	}
	
	private void testException(Throwable exception){
		//http://www.java-tutorial.ch/java-server-faces/jsf-2-exception-handling-and-formatting
		String message = "";
		
		if(exception instanceof FileSysException){
			message = exception.getMessage();
		}else{
			message = "An unexpected error occured !";
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
