package com.iisi.core.utils;

public enum EnumOperationCode {
	
	LoginLogQueryServiceImpl("LQ"), 
	OperationLogServiceImpl("OQ"),
	AddUserServiceImpl("NU"),
	FileDeleteServiceImpl("FD"),
	FileQueryServiceImpl("FQ"),
	FileUploadServiceImpl("FU"),
	QueryUserServiceImpl("AQ"),
	UpdatePwdServiceImpl("PM");
	
	
	private String value;
	
	EnumOperationCode(String value){
		this.value = value;		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 取得作業代碼
	 * @param operationName
	 * @return EnumOperationCode
	 */
	public static EnumOperationCode getCodeName(String operationName){
		EnumOperationCode rtnCode = null;
		for(EnumOperationCode code : EnumOperationCode.values()){
			if(operationName.equals(code.toString())){
				System.out.println("code value = " + code.toString());
				rtnCode = code;
			}
		}
		return rtnCode;
	}
	
}
