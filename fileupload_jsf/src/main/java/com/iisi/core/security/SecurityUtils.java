package com.iisi.core.security;

import java.security.MessageDigest;

public class SecurityUtils {
	
	/**
	 * 將文字轉成MD5
	 * @param str
	 * @return
	 */
	public static String getMD5(String str){
		String md5 = null;
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			//將byte陣列加密
			byte[] barr = md.digest(str.getBytes());
			//將byte列轉成16進制
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<barr.length;i++){
				sb.append(byte2Hex(barr[i]));
				String hex = sb.toString();
				md5 = hex;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return md5;
	}
	
	private static String byte2Hex(byte b){
		String[] h = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
		int i = b;
		if(i < 0){
			i += 256;
		}
		return h[i/16] + h[i%16];
	}
}
