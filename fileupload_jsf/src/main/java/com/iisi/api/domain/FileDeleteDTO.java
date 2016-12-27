package com.iisi.api.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.model.StreamedContent;

import com.iisi.api.model.FileData;
import com.iisi.api.model.User;

public class FileDeleteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;
	
	private String secret;
	
	private Date startDate;
	
	private Date endDate;
	
	private String classNum;
	
	private String disPatchNum;
	
	private String subject;
	
	private String government;
	
	private List<FileData> files;
	
	private User user;
	
	private FileData file;
	
	private StreamedContent contentFile;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getGovernment() {
		return government;
	}

	public void setGovernment(String government) {
		this.government = government;
	}

	public List<FileData> getFiles() {
		return files;
	}

	public void setFiles(List<FileData> files) {
		this.files = files;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FileData getFile() {
		return file;
	}

	public void setFile(FileData file) {
		this.file = file;
	}

	public StreamedContent getContentFile() {
		return contentFile;
	}

	public void setContentFile(StreamedContent contentFile) {
		this.contentFile = contentFile;
	}
	
	
}
