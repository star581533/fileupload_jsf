package com.iisi.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iisi.api.component.UserDataComponent;
import com.iisi.api.domain.UserDTO;
import com.iisi.api.model.Role;
import com.iisi.api.security.UserInfo;

/**
 * 實作UserDetailsService
 * @author 1104611
 *
 */
@Service("customUserDetailsService")
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
	private transient UserDataComponent userDataComponent;
    
//    private Map<String, UserInfo> userMap = null;

    
    //OK
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//       
//    	UserDTO dto = new UserDTO();
//    	dto.setUserId(login);
//    	
//    	com.iisi.api.model.User domainUser = userDataComponent.queryOneUser(dto).get(0);
//       
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        
////        FacesContext context = FacesContext.getCurrentInstance();
////		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();		
////		request.getSession().setAttribute("user", domainUser);
//        
//        return new User(
//        	domainUser.getUserId(),
//        	domainUser.getUserPwd(),
//            enabled,
//            accountNonExpired,
//            credentialsNonExpired,
//            accountNonLocked,
//            getAuthorities(domainUser.getRoleId())
//        );
//    }
    
    /**
     * 
     */
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		//取Session中的值和修改userDetails
		//http://blog.csdn.net/a1015088819/article/details/49893073
//        if(userMap == null){
//        	fillMap(login);
//        }
//    	return userMap.get(login);
    	
    	return loadUserInfoByUserName(login);
    }
    
    public UserInfo loadUserInfoByUserName(String userId){
    	UserDTO dto = new UserDTO();
    	dto.setUserId(userId);
    	
    	com.iisi.api.model.User domainUser = userDataComponent.queryOneUser(dto).get(0);
    	boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
    
        UserInfo userInfo = new UserInfo(
    			domainUser.getUserId(), 
    			domainUser.getUserPwd(), 
    			enabled, 
    			accountNonExpired, 
    			credentialsNonExpired, 
    			accountNonLocked, 
    			getAuthorities(domainUser.getRoleId()));
    	
    	userInfo.setOfficeId(domainUser.getOfficeId());
    	userInfo.setUserId(domainUser.getUserId());
    	userInfo.setUserName(domainUser.getUserName());
    	userInfo.setRoleId(domainUser.getRoleId());
    	
    	return userInfo;
    }
   
//    public void fillMap(String userId){
//    	userMap = new HashMap<String, UserInfo>();
//    	UserDTO dto = new UserDTO();
//    	dto.setUserId(userId);
//    	
//    	com.iisi.api.model.User domainUser = userDataComponent.queryOneUser(dto).get(0);
//    	
//    	boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//    
//        UserInfo userInfo = new UserInfo(
//    			domainUser.getUserId(), 
//    			domainUser.getUserPwd(), 
//    			enabled, 
//    			accountNonExpired, 
//    			credentialsNonExpired, 
//    			accountNonLocked, 
//    			getAuthorities(domainUser.getRoleId()));
//    	
//    	userInfo.setOfficeId(domainUser.getOfficeId());
//    	userMap.put(userInfo.getUsername(), userInfo); 
//    	
//    }
    
    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }
   
    public List<String> getRoles(String role) {
        List<String> roles = new ArrayList<String>();
        Role userRole = userDataComponent.getRole(role);
        roles.add(userRole.getRoleName().toUpperCase());
        return roles;
    }
    
    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<String>();

        if (role.intValue() == 1) {
            roles.add("ROLE_MODERATOR");
            roles.add("ROLE_ADMIN");
        } else if (role.intValue() == 2) {
            roles.add("ROLE_MODERATOR");
        }
        return roles;
    }
   
    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}