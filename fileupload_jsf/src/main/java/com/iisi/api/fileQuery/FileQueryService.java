package com.iisi.api.fileQuery;

import java.util.List;

import com.iisi.api.domain.FileQueryDTO;
import com.iisi.api.model.FileData;

public interface FileQueryService {
	
	/**
	 * 取得檔案上傳資料
	 * @param dto
	 * @return
	 */
	public List<FileData> getFileList(FileQueryDTO dto);
	
	/**
	 * 新增下載檔案記錄
	 * @param str
	 */
	public void insertLog(String str);
}
