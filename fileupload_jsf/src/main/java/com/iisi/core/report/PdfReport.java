package com.iisi.core.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iisi.api.report.AbstractReport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class PdfReport extends AbstractReport{

	@Override
	public void print(List<?> lists, String path, String reportName,  Map<String, Object> map) {		
		JasperPrint jasperPrint = null;
		try{
			//Report路徑
			jasperPrint = JasperFillManager.fillReport(path, map, new JRBeanCollectionDataSource(lists));
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
		}catch(JRException e){
			e.printStackTrace();
		}
		
	}

}
