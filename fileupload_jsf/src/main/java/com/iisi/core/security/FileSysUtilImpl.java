package com.iisi.core.security;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;


@Component("fileSysUtil")
public class FileSysUtilImpl implements FileSysUtil, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public UserInfo getUser() {
		return UserUtil.getUser();
	}

	@Override
	public String getUserId() {
		return getUser().getUserId();
	}

	@Override
	public String getOfficeId() {
		return getUser().getOfficeId();
	}

}
