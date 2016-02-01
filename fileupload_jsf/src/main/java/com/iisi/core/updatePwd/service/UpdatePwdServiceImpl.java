package com.iisi.core.updatePwd.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.component.UserDataComponent;
import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.UpdatePwdDTO;
import com.iisi.api.domain.UserDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.model.OperationLog;
import com.iisi.api.model.User;
import com.iisi.api.updatePwd.UpdatePwdService;
import com.iisi.core.security.SecurityUtils;

@Service("updatePwdService")
public class UpdatePwdServiceImpl implements UpdatePwdService{
	
	private static Logger LOG = LoggerFactory.getLogger(UpdatePwdServiceImpl.class);

	@Autowired
	private transient UserDataComponent userDataComponent;
	
	@Autowired
	private transient DbFactory dbFactory;
	
	@Autowired
	private OperationLogComponent operationLogComponent;
	
	
	@Override
	public void updatePassword(UpdatePwdDTO dto) {		
		try{
			LOG.debug("============================UpdatePwdServiceImpl updatePassword start!===============================");
			UserDTO userDto = new UserDTO();
			userDto.setUserId(dto.getUserId());
			//取得使用者資料
			List<User> users = userDataComponent.queryOneUser(userDto);
			if(users.size() != 0){
				User user = users.get(0);
				user.setUserPwd(SecurityUtils.getMD5(dto.getNewPassWord()));
				
				operationLogComponent.insertOperationLog(OperationLogComponent.PASSWORD_MODIFY, "update passwrd");
				
				DBSMain dbsMain = dbFactory.getDbsMain();
				dbsMain.update(user);
			}
			LOG.debug("============================UpdatePwdServiceImpl updatePassword end!===============================");
		}catch(FileSysException e){
			LOG.error(e.getMessage(), e);
			throw e;
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw new FileSysException(ConstantObject.UPPER_CASE_E, ConstantObject.ERROR_MSG_UPDATE_PASSWORD);
		}
	}
	
	@Override
	public boolean checkUserPassword(UpdatePwdDTO dto) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(dto.getUserId());
		userDto.setOfficeId(dto.getOfficeId());
		
		List<User> users = userDataComponent.queryOneUser(userDto);
		User user = users.get(0);
		//比對使用者資料庫中密碼與畫面上舊密碼是否相同
		return ConstantMethod.compareTwoColumn(user.getUserPwd(), SecurityUtils.getMD5(dto.getOldPassWord()));
	}

}
