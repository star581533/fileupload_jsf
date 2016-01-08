package com.iisi.core.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.api.db.DBFactory;
import com.iisi.api.model.OperationLog;
import com.iisi.api.security.FileSysUtil;
import com.iisi.api.security.UserInfo;
import com.iisi.api.component.OperationLogComponent;
import com.iisi.core.utils.DateUtils;

@Component("operationLogComponent")
public class OperationLogComponentImpl implements OperationLogComponent{

	@Autowired
	private DBFactory dbFactory;
	
	@Autowired
	private FileSysUtil fileSysUtil;
	
	@Override
	public void insertOperationLog(OperationLog operationLog) {
		operationLog.setLogDate(DateUtils.getNowDate());
		operationLog.setLogTime(DateUtils.getNowTime());
		dbFactory.insert(operationLog);
	}

	@Override
	public void insertOperationLog(String type, String operationContent) {
		UserInfo userInfo = this.fileSysUtil.getUser();		
		
		OperationLog operationLog = new OperationLog();
		operationLog.setOfficeId(userInfo.getOfficeId());
		operationLog.setOperationContent(operationContent);
		operationLog.setType(type);
		operationLog.setUserId(userInfo.getUserId());
		operationLog.setUserName(userInfo.getUserName());
		
		this.insertOperationLog(operationLog);
	}

}