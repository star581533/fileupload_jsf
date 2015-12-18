package com.iisi.core.operationLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.api.db.DBFactory;
import com.iisi.api.model.OperationLog;
import com.iisi.api.operationLog.OperationLogComponent;
import com.iisi.core.utils.DateUtils;

@Component("operationLogComponent")
public class OperationLogComponentImpl implements OperationLogComponent{

	@Autowired
	private DBFactory dbFactory;
	
	@Override
	public void insertOperationLog(OperationLog operationLog) {
		operationLog.setLogDate(DateUtils.getNowDate());
		operationLog.setLogTime(DateUtils.getNowTime());
		dbFactory.insert(operationLog);
	}

}
