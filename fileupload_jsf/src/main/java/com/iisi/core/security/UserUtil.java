package com.iisi.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.iisi.api.security.UserInfo;

public final class UserUtil {

	public static UserInfo getUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			Object principal = authentication.getPrincipal();
			if((principal != null) && (principal instanceof UserInfo)){
				return ((UserInfo)principal);
			}
		}
		throw new IllegalStateException("UserInfo object is not available in context.");
	}
	
//	public static ExecutantType getExecutant(){
//		HttpSession session = Sessionu
//	}
}
