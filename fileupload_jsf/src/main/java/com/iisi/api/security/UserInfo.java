package com.iisi.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 繼承UserDetails.User做使用者欄位擴展
 * @author 1104611
 *
 */
public class UserInfo extends User{

	/* */
	private static final long serialVersionUID = 1L;
	
	private String officeId;
	
	private String userId;
	
	private String userName;
	
	private String roleId;
	
	public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, 
				password, 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired,
				accountNonLocked,
				authorities);
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
