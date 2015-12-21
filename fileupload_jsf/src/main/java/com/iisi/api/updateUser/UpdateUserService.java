package com.iisi.api.updateUser;

import com.iisi.api.domain.UpdateUserDTO;

public interface UpdateUserService {
	
	/**
	 * 查詢使用者資料
	 * @param dto
	 */
	public void doQuery(UpdateUserDTO dto);
	
	/**
	 * 更新使用者資料
	 * @param dto
	 */
	public void doUpdate(UpdateUserDTO dto);
	
//	public void doPwdReset(UpdateUserDTO dto);
}
