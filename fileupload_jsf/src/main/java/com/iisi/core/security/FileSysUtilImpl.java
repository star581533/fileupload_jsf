package com.iisi.core.security;

import org.springframework.stereotype.Component;

import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;


@Component("fileSysUtil")
public class FileSysUtilImpl implements FileSysUtil {

	@Override
	public UserInfo getUser() {
		return UserUtil.getUser();
	}

	@Override
	public String getUserId() {
		return getUser().getUsername();
	}

	@Override
	public String getOfficeId() {
		return getUser().getOfficeId();
	}

}
