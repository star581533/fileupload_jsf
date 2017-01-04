package com.iisi.api.component;

import com.iisi.api.model.OperationLog;

public interface OperationLogComponent {
	
	public void insertOperationLog(OperationLog operationLog);
	
	public void insertOperationLog(String type, String operationContent);
}
