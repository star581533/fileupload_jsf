package com.iisi.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesContent {
	
	public String getPropertiesByCode(InputStream properName, String code){
		String rtn = "";
		Properties properties = new Properties();
		try {
			properties.load(properName);
			rtn= properties.getProperty(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rtn;
	}
}
