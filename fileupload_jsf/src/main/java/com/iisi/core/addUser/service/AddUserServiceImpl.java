package com.iisi.core.addUser.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.addUser.AddUserService;
import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.component.UserDataComponent;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.AddUserDTO;
import com.iisi.api.domain.UserDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.model.User;
import com.iisi.core.security.SecurityUtils;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.EnumOperationCode;

@Service("addUserService")
public class AddUserServiceImpl implements AddUserService{

	private static Logger LOG = LoggerFactory.getLogger(AddUserServiceImpl.class);
	
	@Autowired
	private transient UserDataComponent userDataComponent;
	
	@Autowired
	private transient DbFactory dbFactory;
	
	@Autowired
	private OperationLogComponent operationLogComponent;
	
	@Override
	public void checkUser(AddUserDTO dto) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(dto.getUserId());
		userDto.setOfficeId(dto.getOfficeId());
		//利用使用者代碼與科別查詢此使用者代碼是否被使用
		dto.setUserCount(userDataComponent.countSingleUser(userDto));
	}

	@Override
	public void doSave(AddUserDTO dto) {
		try{
			LOG.debug("============================AddUserServiceImpl doSave start!===============================");
			User user = new User();
			user.setUserId(dto.getUserId());
			user.setUserPwd(SecurityUtils.getMD5(dto.getUserId()));
			user.setUserName(dto.getUserName());
			user.setLoginFail("0");
			user.setOfficeId(dto.getOfficeId());
			user.setState("Y");
			user.setRoleId(dto.getRoleId());
			user.setCreateDate(DateUtils.getNowDate());
			user.setCreateTime(DateUtils.getNowTime());			
			
			EnumOperationCode code = EnumOperationCode.getCodeName(this.getClass().getSimpleName());
			operationLogComponent.insertOperationLog(code.getValue(), user.toString());
						
			DBSMain dbsMain = this.dbFactory.getDbsMain();
			dbsMain.insert(user);
			LOG.debug("============================AddUserServiceImpl doSave end!===============================");
		}catch(FileSysException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new FileSysException(ConstantObject.UPPER_CASE_E, "新增使用者失敗");
		}	
	}

}
