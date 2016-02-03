package com.iisi.core.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.api.component.LoginLogComponent;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.model.LoginLog;
import com.iisi.api.security.UserInfo;
import com.iisi.core.utils.DateUtils;

@Component("loginLogComponent")
public class LoginLogComponentImpl implements LoginLogComponent{
	
	@Autowired
	private transient DbFactory dbFactory;

	@Override
	public void insertLoginLog(UserInfo userInfo, String inOutMark) {
		try{
			System.out.println("------------------------insertLoginLog");
			DBSMain dbsMain = dbFactory.getDbsMain();
			
			LoginLog loginLog = new LoginLog();
			loginLog.setInOutMark(inOutMark);
			loginLog.setOfficeId(userInfo.getOfficeId());
			loginLog.setRoleId(userInfo.getRoleId());
			loginLog.setUserId(userInfo.getUserId());
			loginLog.setUserName(userInfo.getUserName());
			loginLog.setLoginDate(DateUtils.getNowDate());
			loginLog.setLoginTime(DateUtils.getNowTime());
			
			System.out.println("dbsMain = " + dbsMain);
			
			dbsMain.insert(loginLog);
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
