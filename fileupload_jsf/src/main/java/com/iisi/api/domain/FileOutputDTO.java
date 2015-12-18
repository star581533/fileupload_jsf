package com.iisi.api.domain;

public class FileOutputDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean checkBoxAll;

	/**
	 * 分類號
	 */
	private String classNum;	
	
	/**
	 * 公文文號
	 */
	private String disPatchNum;
	
	/**
	 * 檔案名稱
	 */
	private String fileName;
	
	/**
	 * 機關
	 */
	private String government;
	
	
	/**
	 * 單位代號
	 */
	private String officeId;
	
	
	/**
	 * 密件
	 */
	private String secret;
	
	
	/**
	 * 上傳日期
	 */
	private String uploadDate;
	
	/**
	 * 上傳時間
	 */
	private String uploadTime;
	
	/**
	 * 使用者帳號
	 */
	private String userId;
	
	/**
	 * 使用者姓名
	 */
	private String userName;
	
	private String imageId;
	
	private String list;
	
	private String subject;

	public boolean isCheckBoxAll() {
		return checkBoxAll;
	}

	public void setCheckBoxAll(boolean checkBoxAll) {
		this.checkBoxAll = checkBoxAll;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getDisPatchNum() {
		return disPatchNum;
	}

	public void setDisPatchNum(String disPatchNum) {
		this.disPatchNum = disPatchNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getGovernment() {
		return government;
	}

	public void setGovernment(String government) {
		this.government = government;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}
		
}
