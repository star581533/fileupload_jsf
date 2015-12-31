package com.iisi.api.model;

import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.constant.ConstantObject;
import com.iisi.core.utils.FileSysUtils;

public class LoginLogPrint {
	/**
	 * 工作註記
	 */
	private String inOutMark;
	
	/**
	 * 作業日期
	 */
	private String loginDate;
	
	/**
	 * 作業時間
	 */
	private String loginTime;
	
	/**
	 * 單位代碼
	 */
	private String officeId;
	
	/**
	 * 權限代碼
	 */
	private String roleId;
	
	/**
	 * 使用者帳號
	 */
	private String userId;
	
	/**
	 * 使用者姓名
	 */
	private String userName;
	
	private String inOutMarkName;
	
	private String loginDateForm;
	
	private String loginTimeForm;
	
	private String officeName;

	public String getInOutMark() {
		return inOutMark;
	}

	public void setInOutMark(String inOutMark) {
		this.inOutMark = inOutMark;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInOutMarkName() {
		if(inOutMark.equals(ConstantObject.UPPER_CASE_I)){
			inOutMarkName = "簽到";
		}else if(inOutMark.equals(ConstantObject.UPPER_CASE_O)){
			inOutMarkName = "簽退";
		}else{
			inOutMarkName = "";
		}
		return inOutMarkName;
	}

	public void setInOutMarkName(String inOutMarkName) {
		this.inOutMarkName = inOutMarkName;
	}

	public String getLoginDateForm() {
		return FileSysUtils.formatYyymmdd(loginDate);
	}

	public void setLoginDateForm(String loginDateForm) {
		this.loginDateForm = loginDateForm;
	}

	public String getLoginTimeForm() {
		return FileSysUtils.formatHhmmss(loginTime);
	}

	public void setLoginTimeForm(String loginTimeForm) {
		this.loginTimeForm = loginTimeForm;
	}

	public String getOfficeName() {
		if(!ConstantMethod.verifyColumn(officeId)){
			officeName = FileSysUtils.getProperties("office.properties", officeId);
		}else{
			officeName = "";
		}
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
		
}
