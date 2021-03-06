package com.iisi.core.updateUser;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.UserDataComponent;
import com.iisi.api.constant.ConstantObject;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.UpdateUserDTO;
import com.iisi.api.domain.UserDTO;
import com.iisi.api.model.User;
import com.iisi.api.updateUser.UpdateUserService;
import com.iisi.core.security.SecurityUtils;

@Service("updateUserService")
public class UpdateUserServiceImpl implements UpdateUserService {

	@Autowired
	private transient UserDataComponent userDataComponent;
			
	@Autowired
	private transient DbFactory dbFactory;
	
	@Override
	public void doQuery(UpdateUserDTO dto) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(dto.getUserId());
		userDto.setOfficeId(dto.getOfficeId());
		User user = getUser(userDto);
		dto.setUser(user);
	}
	
	/**
	 * 取得使用者資料
	 * @param userDto
	 * @return User
	 */
	private User getUser(UserDTO userDto){
		List<User> users = userDataComponent.queryOneUser(userDto);
		User user = new User();
		System.out.println("users.size() = " + users.size());
		
		if(users.size() > 0){
			user = users.get(0);
		}
		
		return user;
	}

	@Override
	public void doUpdate(UpdateUserDTO dto) {
		User user = dto.getUser();
		user.setUserName(dto.getUser().getUserName());
		if(StringUtils.equals(dto.getResetFlag(), ConstantObject.UPPER_CASE_Y)){
			user.setUserPwd(SecurityUtils.getMD5(dto.getUser().getUserId()));
		}	
		user.setOfficeId(dto.getUser().getOfficeId());
		user.setState(dto.getUser().getState());
				
		DBSMain dbsMain = this.dbFactory.getDbsMain();
		dbsMain.update(user);
		
	}
	
}
