//http://blog.db.idv.tw/2010/12/javachecked-exception-runtime.html
package com.iisi.api.execption;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.reflect.FieldUtils;

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
//		System.out.println("-------------------------------------start-----------------------------------");
//		FacesMessage faceMessage = new FacesMessage(message);
//		FacesContext.getCurrentInstance().addMessage(null, faceMessage);
//		
//		System.out.println("-------------------------------------end-----------------------------------");
	}
	
	public FileSysException(String message, Throwable cause){
		super(genExceptionMessage(message, null), cause);
	}
	
	private static String genExceptionMessage(String error, String extMsg){
		ms(error);
		return error;
	}
	
	private static void ms(String exception){	
		System.out.println("-------------------------------------ms start-----------------------------------");
		try{
			System.out.println("--------growl start");
//			FieldUtils.writeDeclaredField("aa", "message",  "bb", true);
			System.out.println("--------growl end");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		FacesMessage faceMessage = new FacesMessage(exception);
		FacesContext.getCurrentInstance().addMessage(null, faceMessage);
		System.out.println("-------------------------------------ms end-----------------------------------");
	}
	
	private void testException(Throwable exception){
		String message = "";
		if(exception instanceof FileSysException){
			message = exception.getMessage();
		}else{
			message = "An unexpected error occured !";
		}
		
		FacesMessage faceMessage = new FacesMessage(message);
		FacesContext.getCurrentInstance().addMessage(null, faceMessage);
	}
}
