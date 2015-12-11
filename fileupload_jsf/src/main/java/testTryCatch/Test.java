package testTryCatch;

import org.apache.commons.lang3.reflect.FieldUtils;

public class Test {
	public static void main(String args[]){
		try{
			throw new Exception();
		}catch(testException e){
			System.out.println("testException");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("ArrayIndexOutOfBoundsException");
		}catch(Exception e){
			System.out.println("Exception");
			throw new testException("dd");
		}
			
	}
}

class testException extends RuntimeException{
	public testException(String msg){
		super(msg);
		
		dd(msg);
	}
	
	public String dd(String msg){
		String message = msg;
		try{
			System.out.println("msg ");
//			FieldUtils.writeDeclaredField("aa", "message",  "bb", true);
			System.out.println("message = " + message);
		}catch(Throwable e1){
			try{
				System.out.println("e1");
			}catch(Throwable e){
				
			}
		}
		return message;
	}
}
