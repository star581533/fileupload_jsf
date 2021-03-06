//http://www.mkyong.com/jsf2/jsf-2-dropdown-box-example/
//http://haohaoxuexi.iteye.com/blog/1190526
//http://blog.xuite.net/snowtech/blog/203139963-Spring+Annotation%E7%AD%86%E8%A8%98
//http://zhou137520.iteye.com/blog/1672305
package com.iisi.web.queryuser;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.iisi.api.constant.ConstantObject;
import com.iisi.api.domain.QueryUserDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.menu.MenuService;
import com.iisi.api.model.User;
import com.iisi.api.queryUser.QueryUserService;

@ManagedBean
@ViewScoped
public class QueryUserController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private QueryUserDTO dto = new QueryUserDTO();
						
	@ManagedProperty(value="#{queryUserService}")
	private QueryUserService queryUserService;	
	
	@PostConstruct
	public void init(){
		System.out.println("QueryUserController init");
		dto = new QueryUserDTO();
	}
	
	
	/**
	 * 查詢資料
	 */
	public void doQuery(){
		try{		
			this.verifyData();
			dto.setUsers(queryUserService.getUserList(dto));
			System.out.println("dto.getUsers().size() = " + dto.getUsers().size());	
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 資料驗證
	 */
	private void verifyData(){	
		//在職狀態
		if(StringUtils.isBlank(dto.getState())){
			throw new FileSysException(ConstantObject.UPPER_CASE_W, ConstantObject.WARN_MSG_INPUT_STATE);
		}	
	}	

	/**
	 * 導向修改使用者資料畫面
	 * @param user
	 * @return String
	 */
	public String userForward(User user){		
		final String updateUserUrl = MenuService.lookupMenuPage(MenuService.UPDATE_USER) + "&id=" + user.getUserId() +"&officeid=" + user.getOfficeId();
		return updateUserUrl;
	}

	public QueryUserDTO getDto() {
		return dto;
	}


	public void setDto(QueryUserDTO dto) {
		this.dto = dto;
	}

	public QueryUserService getQueryUserService() {
		return queryUserService;
	}


	public void setQueryUserService(QueryUserService queryUserService) {
		this.queryUserService = queryUserService;
	}

}
