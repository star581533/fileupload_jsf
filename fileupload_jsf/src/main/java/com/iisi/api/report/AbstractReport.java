package com.iisi.api.report;

import java.util.List;
import java.util.Map;

public abstract class AbstractReport {
	
	public abstract void print(List<?> lists, String path, String reportName,  Map<String, Object> map);

}
