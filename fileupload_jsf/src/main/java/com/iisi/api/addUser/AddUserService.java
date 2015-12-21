package com.iisi.api.addUser;

import com.iisi.api.domain.AddUserDTO;

public interface AddUserService {
	/**
	 * 檢查使用者資料
	 * @param dto
	 */
	public void checkUser(AddUserDTO dto);

	/**
	 * 新增使用者資料
	 * @param dto
	 */
	public void doSave(AddUserDTO dto);
}
