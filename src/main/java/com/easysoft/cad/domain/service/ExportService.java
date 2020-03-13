package com.easysoft.cad.domain.service;

import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public interface ExportService {

	public int getExportPageSize();
	 
	public String generateFileName();
	
	public SXSSFWorkbook createWorkbook();
	
	public SXSSFSheet createSheet(SXSSFWorkbook workbook, String sheetName);
	
	public void createRow(SXSSFSheet sheet, int rowIndex, List<String> values);
	 
	public void saveFile(SXSSFWorkbook workbook, String fileName);

	public String getFilePath(String fileName);
}
