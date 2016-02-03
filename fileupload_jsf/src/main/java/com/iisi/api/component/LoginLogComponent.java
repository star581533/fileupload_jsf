package com.iisi.api.component;

import com.iisi.api.security.UserInfo;

public interface LoginLogComponent {
	
	/**
	 * 新增登入、登出記錄
	 * @param userInfo
	 * @param inOutMark
	 */
	public void insertLoginLog(UserInfo userInfo, String inOutMark);
}
