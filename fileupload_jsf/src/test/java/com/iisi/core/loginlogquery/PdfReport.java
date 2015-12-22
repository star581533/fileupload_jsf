package com.iisi.core.loginlogquery;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class PdfReport extends AbstractReport{

	@Override
	public void print(List<?> lists, String path) {
		String str = this.getClass().getResource("../../../").getFile();
		
		String name = "reports/LoginLog.jrxml";
		
		Class clazz = this.getClass();
		URLClassLoader loader = (URLClassLoader)clazz.getClassLoader();
		URL url = loader.getResource(name);
//		URL url = loader.findResource(name);
		System.out.println("Path = " + url.getPath());
		
		JasperPrint jasperPrint = null;
		try{
//			String path = "D:\\GitHub\\fileupload_jsf\\fileupload_jsf\\src\\main\\resources\\reports\\LoginLog.jasper";
			//Report路徑
//			JasperCompileManager.compileReportToFile(path);
			jasperPrint = JasperFillManager.fillReport(path, new HashMap(), new JRBeanCollectionDataSource(lists));
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
		}catch(JRException e){
			e.printStackTrace();
		}
		
	}

}
