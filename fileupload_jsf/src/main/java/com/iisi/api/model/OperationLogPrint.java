package com.iisi.api.model;

import com.iisi.api.constant.ConstantMethod;
import com.iisi.core.utils.FileSysUtils;

public class OperationLogPrint extends OperationLog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private String logDateForm;
//	
//	private String logTimeForm;
	
	private String officeName;
	
	private String typeName;

	public String getLogDateForm() {
		return FileSysUtils.formatYyymmdd(this.getLogDate());
	}

//	public void setLogDateForm(String logDateForm) {
//		this.logDateForm = logDateForm;
//	}

	public String getLogTimeForm() {
		return FileSysUtils.formatHhmmss(this.getLogTime());
	}

//	public void setLogTimeForm(String logTimeForm) {
//		this.logTimeForm = logTimeForm;
//	}

	public String getOfficeName() {
		if(!ConstantMethod.verifyColumn(this.getOfficeId())){
			officeName = FileSysUtils.getProperties("office.properties", this.getOfficeId());
		}
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getTypeName() {
		if(!ConstantMethod.verifyColumn(this.getType())){
			typeName = FileSysUtils.getProperties("operationName.properties", this.getType());
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
		
}
