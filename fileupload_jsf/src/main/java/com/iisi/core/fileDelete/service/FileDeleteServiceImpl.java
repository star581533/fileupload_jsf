package com.iisi.core.fileDelete.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iisi.api.component.OperationLogComponent;
import com.iisi.api.constant.ConstantMethod;
import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;
import com.iisi.api.domain.FileDeleteDTO;
import com.iisi.api.execption.FileSysException;
import com.iisi.api.fileDelete.FileDeleteService;
import com.iisi.api.model.FileData;
import com.iisi.core.utils.DateUtils;

@Service("fileDeleteService")
public class FileDeleteServiceImpl implements FileDeleteService, Serializable {
	
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
		
		if(!ConstantMethod.verifyColumn(dto.getSecret())){
			sql.append("and secret = ? ");
			params.add(dto.getSecret());
			content.append("secret=").append(dto.getSecret()).append(",");
		}
		
		if(!ConstantMethod.verifyColumn(dto.getClassNum())){
			sql.append("and classnum = ? ");
			params.add(dto.getClassNum());
			content.append("classnum=").append(dto.getClassNum()).append(",");
		}
		
		if(!ConstantMethod.verifyColumn(dto.getDisPatchNum())){
			sql.append("and dispatchnum = ? ");
			params.add(dto.getDisPatchNum());
			content.append("dispatchnum=").append(dto.getDisPatchNum()).append(",");
		}
		
		if(!ConstantMethod.verifyColumn(dto.getSubject())){
			sql.append("and subject = ? ");
			params.add(dto.getSubject());
			content.append("subject=").append(dto.getSubject()).append(",");
		}
		
		if(!ConstantMethod.verifyColumn(dto.getGovernment())){
			sql.append("and government = ? ");
			params.add(dto.getGovernment());
			content.append("government=").append(dto.getGovernment());			
		}
			
		DBSMain dbsMain = this.dbFactory.getDbsMain();
		List<FileData> files = (List<FileData>)dbsMain.query(params, sql.toString(), FileData.class);
		
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
			operationLogComponent.insertOperationLog(OperationLogComponent.FILE_DELETE, str);
		}catch(FileSysException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
