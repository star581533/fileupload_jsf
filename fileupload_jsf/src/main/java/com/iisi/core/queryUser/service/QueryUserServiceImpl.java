package com.iisi.core.queryUser.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.component.UserDataComponent;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.QueryUserDTO;
import com.iisi.api.domain.UserDTO;
import com.iisi.api.model.User;
import com.iisi.api.queryUser.QueryUserService;
import com.iisi.core.utils.EnumOperationCode;

@Service("queryUserService")
public class QueryUserServiceImpl implements QueryUserService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient UserDataComponent userDataComponent;
	
	@Autowired
	private OperationLogComponent operationLogcomponent;
	
	@Override
	public List<User> getUserList(QueryUserDTO dto) {
		List<User> users = new ArrayList<User>();
		UserDTO userDto = new UserDTO();
		
		if(StringUtils.isBlank(dto.getOfficeId())){
			//不為全部時，依狀態加入查詢
			if(!dto.getState().equals(ConstantObject.UPPER_CASE_A)){
				userDto.setState(dto.getState());	
			}			
			users = userDataComponent.queryAllUser(userDto);
		}else{			
			userDto.setOfficeId(dto.getOfficeId());
			userDto.setState(dto.getState());
			users = userDataComponent.queryOfficeUsers(userDto);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("officeId=").append(dto.getOfficeId()).append(",");
		sb.append("state=").append(dto.getState()).append(",");
		
		EnumOperationCode code = EnumOperationCode.getCodeName(this.getClass().getSimpleName());
		operationLogcomponent.insertOperationLog(code.getValue(), sb.toString());
		
		return users;
	}

}
