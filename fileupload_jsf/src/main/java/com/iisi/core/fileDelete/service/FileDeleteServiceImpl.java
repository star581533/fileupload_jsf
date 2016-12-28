package com.iisi.core.fileDelete.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.FileDeleteDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.fileDelete.FileDeleteService;
import com.iisi.api.model.FileData;
import com.iisi.core.utils.DateUtils;
import com.iisi.core.utils.EnumOperationCode;

@Service("fileDeleteService")
public class FileDeleteServiceImpl implements FileDeleteService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient DbFactory dbFactory;
	
	@Autowired
	private OperationLogComponent operationLogComponent; 
	
	@Override
	public List<FileData> doQuery(FileDeleteDTO dto) {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		StringBuilder content = new StringBuilder();
		
		sql.append("select * from filedata ");
		sql.append("where uploaddate between ? and ? ");
		sql.append("and type = ? ");
		
		String startDate = DateUtils.adToRocDate(dto.getStartDate());
		String endDate = DateUtils.adToRocDate(dto.getEndDate()); 
		String type = dto.getType();
		
		params.add(startDate);
		params.add(endDate);
		params.add(type);
		
		content.append("sUploaddate=").append(startDate).append(",");
		content.append("eUploaddate=").append(endDate).append(",");
		content.append("type=").append(type).append(",");
		
		if(!StringUtils.isBlank(dto.getSecret())){
			sql.append("and secret = ? ");
			params.add(dto.getSecret());
			content.append("secret=").append(dto.getSecret()).append(",");
		}
		
		if(!StringUtils.isBlank(dto.getClassNum())){
			sql.append("and classnum = ? ");
			params.add(dto.getClassNum());
			content.append("classnum=").append(dto.getClassNum()).append(",");
		}
		
		if(!StringUtils.isBlank(dto.getDisPatchNum())){
			sql.append("and dispatchnum = ? ");
			params.add(dto.getDisPatchNum());
			content.append("dispatchnum=").append(dto.getDisPatchNum()).append(",");
		}
		
		if(!StringUtils.isBlank(dto.getSubject())){
			sql.append("and subject = ? ");
			params.add(dto.getSubject());
			content.append("subject=").append(dto.getSubject()).append(",");
		}
		
		if(!StringUtils.isBlank(dto.getGovernment())){
			sql.append("and government = ? ");
			params.add(dto.getGovernment());
			content.append("government=").append(dto.getGovernment());			
		}
			
		DBSMain dbsMain = this.dbFactory.getDbsMain();
		
		@SuppressWarnings("unchecked")
		List<FileData> files = (List<FileData>)dbsMain.query(params, sql.toString(), FileData.class);
		
		try {
			// FIXME 刪除完資料後，會再做一次查詢，因查詢時間太快，在寫LOG會發生資料相同問題，所以這先等1秒
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.insertLog(content.toString());
		
		return files;
	}

	@Override
	public void doDelete(FileDeleteDTO dto) {
		FileData file = dto.getFile();
		String log = "delete imageId=" + file.getImageId() + ", fileName=" + file.getFileName();
		this.insertLog(log);
		DBSMain dbsMain = this.dbFactory.getDbsMain();
		dbsMain.delete(file);
		
	}

	@Override
	public void insertLog(String str) {
		try{
			EnumOperationCode code = EnumOperationCode.getCodeName(this.getClass().getSimpleName());
			operationLogComponent.insertOperationLog(code.getValue(), str);
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
