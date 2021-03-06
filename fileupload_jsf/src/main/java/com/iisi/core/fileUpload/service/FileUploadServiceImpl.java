package com.iisi.core.fileUpload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.FileUploadDTO;
import com.iisi.api.fileUpload.FileUploadService;
import com.iisi.api.model.FileData;
import com.iisi.api.security.UserInfo;
import com.iisi.core.security.UserUtil;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.EnumOperationCode;

@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService{

	@Autowired
	private OperationLogComponent operationLogComponent;
			
	@Autowired
	private transient DbFactory dbFactory;
	
	@Override
	public void doSave(FileUploadDTO dto) {
		UserInfo user = UserUtil.getUser();		
		
		FileData fileData = new FileData();
		fileData.setClassNum(dto.getClassNum());
		fileData.setDisPatchDate(DateUtils.adToRocDate(dto.getDisPatchDate()));
		fileData.setDisPatchNum(dto.getDisPatchNum());
		fileData.setFileName(dto.getUploadFile().getFileName());
		fileData.setGovernment(dto.getGovernment());
		fileData.setImageId(dto.getImageId());
		fileData.setList(dto.getFilePath());
		fileData.setOfficeId(user.getOfficeId());
		fileData.setRoleId(user.getRoleId());
		fileData.setSecret(dto.getSecret());
		fileData.setSubject(dto.getSubject());
		fileData.setType(dto.getType());
		fileData.setUploadDate(DateUtils.getNowDate());
		fileData.setUploadTime(DateUtils.getNowTime());
		fileData.setUserId(user.getUserId());
		fileData.setUserName(user.getUserName());
		
		EnumOperationCode code = EnumOperationCode.getCodeName(this.getClass().getSimpleName());
		operationLogComponent.insertOperationLog(code.getValue(), fileData.toString());
		
		DBSMain dbsMain = this.dbFactory.getDbsMain();
		dbsMain.insert(fileData);
	}
}
