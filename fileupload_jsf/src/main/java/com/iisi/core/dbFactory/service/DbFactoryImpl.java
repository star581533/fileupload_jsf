package com.iisi.core.dbFactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.iisi.api.db.DBSMain;
import com.iisi.api.db.DbFactory;

@Component
@Qualifier("dbFactory")
public class DbFactoryImpl implements DbFactory{

	@Autowired
	private DBSMain dbsMain;
	
	@Override
	public DBSMain getDbsMain() {		
		return dbsMain;
	}
}
