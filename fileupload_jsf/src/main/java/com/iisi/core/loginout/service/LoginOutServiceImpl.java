package com.iisi.core.loginout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.LoginLogComponent;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.loginout.LoginOutService;
import com.iisi.api.security.UserInfo;

@Service("loginOutService")
public class LoginOutServiceImpl implements LoginOutService {

	@Autowired
	private LoginLogComponent loginLogComponent;
	
	@Override
	public void insertLoginOut(UserInfo userInfo, String inoutMark) {
		this.loginLogComponent.insertLoginLog(userInfo, inoutMark);
	}

}
