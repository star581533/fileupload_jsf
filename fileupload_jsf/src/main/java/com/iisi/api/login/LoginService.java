package com.iisi.api.login;

import java.util.List;

import org.springframework.stereotype.Component;

import com.iisi.api.domain.LoginDTO;
import com.iisi.api.model.Role;
import com.iisi.api.model.User;

@Component
public interface LoginService {
	public void queryUser(LoginDTO dto);
	
	public User getUser(LoginDTO dto);
	
	public Role getRole(String role);

}
