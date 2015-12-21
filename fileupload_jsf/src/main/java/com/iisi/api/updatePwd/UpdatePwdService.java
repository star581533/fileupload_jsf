package com.iisi.api.updatePwd;

import com.iisi.api.domain.UpdatePwdDTO;

public interface UpdatePwdService {
	
	/**
	 * 檢查使用者密碼
	 * @param dto
	 * @return
	 */
	public boolean checkUserPassword(UpdatePwdDTO dto);
	
	/**
	 * 更新使用者密碼
	 * @param dto
	 */
	public void updatePassword(UpdatePwdDTO dto);
}
