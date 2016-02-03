package com.iisi.api.loginout;

import com.iisi.api.security.UserInfo;

public interface LoginOutService {
	/**
	 * 新增登入、登出記錄
	 * @param userInfo
	 * @param inoutMark
	 */
	public void insertLoginOut(UserInfo userInfo, String inoutMark );
}
