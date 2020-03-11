package com.easysoft.cad.domain.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExportService {

	public String getExportDirectory();
	 
	public String generateFileName();
	 
	public void saveFile(XSSFWorkbook workbook, String fileName);
}
