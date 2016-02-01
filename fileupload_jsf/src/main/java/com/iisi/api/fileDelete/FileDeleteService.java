package com.iisi.api.fileDelete;

import java.util.List;

import com.iisi.api.domain.FileDeleteDTO;
import com.iisi.api.model.FileData;

public interface FileDeleteService {
	
	/**
	 * 查詢檔案上傳的資料
	 * @param dto
	 * @return
	 */
	public List<FileData> doQuery(FileDeleteDTO dto);
	
	/**
	 * 刪除資料
	 * @param dto
	 */
	public void doDelete(FileDeleteDTO dto);
	
	/**
	 * 新增記錄
	 * @param str
	 */
	public void insertLog(String str);
}
