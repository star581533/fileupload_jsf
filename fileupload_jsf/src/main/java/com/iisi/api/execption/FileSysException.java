//http://blog.db.idv.tw/2010/12/javachecked-exception-runtime.html
package com.iisi.api.execption;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.security.core.AuthenticationException;

/**
 * FileSysException
 * 繼承RuntimeException自定例外類別
 * 因RuntimeException在執行時才會發生，不需強制放try-catch或往上層拋例外(throws Exception)
 * @author User
 *
 */
public class FileSysException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public FileSysException(FileSysException e){
//		
//	}
	
	public FileSysException(String message){
		super(genExceptionMessage(message, null));
	}
	
	public FileSysException(String error, String message){
		super(genExceptionMessage(error, message));
	}
	
	public FileSysException(String message, Throwable cause){
		super(genExceptionMessage(message, null), cause);
	}
	
	public FileSysException(Throwable cause){
		super(genExceptionMessage(cause));
	}
	
	private static String genExceptionMessage(String error, String extMsg){
		setMessage(error, extMsg);
		return error;
	}
	
	private static String genExceptionMessage(Throwable cause){
		return handleException(cause);
	}
	
	private static String handleException(Throwable cause){
		String message = "";
		if(cause instanceof AuthenticationException){
			message = "使用者登入錯誤";
		}
		FacesMessage faceMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		return message;
	}
	
	private static void setMessage(String title, String msg){	
//		try{
//			System.out.println("--------growl start");
////			FieldUtils.writeDeclaredField("aa", "message",  "bb", true);
//			System.out.println("--------growl end");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		Severity severity = null;
		
		System.out.println("title = " + title);
		
		if(title.equals("W")){
			severity = FacesMessage.SEVERITY_WARN;
		}else if(title.equals("E")){
			severity = FacesMessage.SEVERITY_ERROR;
		}else if(title.equals("I")){
			severity = FacesMessage.SEVERITY_INFO;
		}else{
			severity = FacesMessage.SEVERITY_FATAL;
		}
		
		FacesMessage faceMessage = new FacesMessage(severity, "",msg);
		FacesContext.getCurrentInstance().addMessage(null, faceMessage);
//		FacesContext.getCurrentInstance().responseComplete();
//		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantObject.INPUT_DATA, ConstantObject.WARN_MSG_INPUT_USER_ID));
	}

}
