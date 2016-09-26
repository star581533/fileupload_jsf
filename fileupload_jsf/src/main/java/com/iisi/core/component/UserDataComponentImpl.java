package com.iisi.core.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iisi.api.component.UserDataComponent;
import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.UserDTO;
import com.iisi.api.model.Role;
import com.iisi.api.model.User;

@Component("userDataComponent")
public class UserDataComponentImpl implements UserDataComponent{
	
	@Autowired
	private transient DbFactory dbFactory;
	
	private DBSMain dbsMain;
	
	@Override
	public List<User> queryOneUser(UserDTO dto){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from user where userid = ?");

		List<String> params = new ArrayList<String>();
		params.add(dto.getUserId());
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getDbsMain().query(params, sql.toString(), User.class);
	
		return users;
	}
	

	@Override
	public int countSingleUser(UserDTO dto) {
//		int count = this.queryOfficeUser(dto).size();
		int count = this.queryOneUser(dto).size();
		return count;
	}

	@Override
	public List<User> queryOfficeUsers(UserDTO dto) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from user where officeid = ? ");		
		params.add(dto.getOfficeId());
		
		if(!ConstantMethod.verifyColumn(dto.getState())){
			sql.append("and state = ?");
			params.add(dto.getState());
		}
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getDbsMain().query(params, sql.toString(), User.class);
		return users;
	}


	@Override
	public List<User> queryAllUser(UserDTO dto) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from user ");
		if(!ConstantMethod.verifyColumn(dto.getState())){
			sql.append("where state = ? ");
			params.add(dto.getState());
		}	
		System.out.println("query All User sql = " + sql.toString());
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getDbsMain().query(params, sql.toString(), User.class);
		
		return users;

	}

	@Override
	public List<Role> getAllRoles() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from role");
		
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>)getDbsMain().query(new ArrayList<String>(), sql.toString(), Role.class);
		return roles;
	}


	@Override
	public Role getRole(String role) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from role where roleid = ? ");
		List<String> params = new ArrayList<String>();
		params.add(role);
		
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>)getDbsMain().query(params, sql.toString(), Role.class);
		return roles.get(0);
	}


	@Override
	public void updateUserData(User user) {
		getDbsMain().update(user);
	}


	@Override
	public List<User> queryOfficeUser(UserDTO dto) {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from user where userid = ? and officeid = ? ");		
		params.add(dto.getUserId());
		params.add(dto.getOfficeId());
		
		System.out.println("userId = " + dto.getUserId());
		System.out.println("officeId = " + dto.getOfficeId());
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getDbsMain().query(params, sql.toString(), User.class);
		return users;
	}
	
	private DBSMain getDbsMain(){
		if(null == dbsMain){
			dbsMain = this.dbFactory.getDbsMain();	
		}
		return dbsMain;		
	}

}
