package com.iisi.core.security;

import org.springframework.stereotype.Component;

import com.iisi.api.security.ExecutantType;
import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;


@Component("fileSysUtil")
public class FileSysUtilImpl implements FileSysUtil {

	@Override
	public UserInfo getUser() {
		return UserUtil.getUser();
	}

	@Override
	public ExecutantType getExecutant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutantType getExecutant(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSiteId() {
		// TODO Auto-generated method stub
		return null;
	}

}
