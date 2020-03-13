package com.easysoft.cad.domain.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.easysoft.cad.CustomProperties;
import com.easysoft.cad.domain.service.ExportService;

@Service
public class ExportServiceImpl implements ExportService {

	private static final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

	@Autowired
	private CustomProperties properties;
	
	private String exportDirectory;

	private String getExportDirectory() {

		if (StringUtils.hasText(this.exportDirectory)) {
			return this.exportDirectory;
		}
		ApplicationHome home = new ApplicationHome(getClass());
		this.exportDirectory = home.getSource().getParentFile().toString() + "\\export";
		File directory = new File(this.exportDirectory);
		if (!directory.exists()) {
			directory.mkdir();
		}
		return this.exportDirectory;
	}

	@Override
	public int getExportPageSize() {
		return this.properties.getExportPageSize();
	}
	
	@Override
	public String generateFileName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Override
	public String getFilePath(String fileName) {
		return this.getExportDirectory() + "\\" + fileName + ".xlsx";
	}

	@Override
	public SXSSFWorkbook createWorkbook() {
		return new SXSSFWorkbook(this.properties.getExportRowAccessWindowSize());
	}
	
	@Override
	public SXSSFSheet createSheet(SXSSFWorkbook workbook, String sheetName) {
		return workbook.createSheet(sheetName);
	}
	
	@Override
	public void createRow(SXSSFSheet sheet, int rowIndex, List<String> values) {
		SXSSFRow row = sheet.createRow(rowIndex);
		for (int i = 0; i < values.size(); i++) {
			SXSSFCell cell = row.createCell(i);
			cell.setCellValue(values.get(i));
		}
	}

	@Override
	public void saveFile(SXSSFWorkbook workbook, String fileName) {
		try {
			FileOutputStream stream = new FileOutputStream(this.getFilePath(fileName));
			workbook.write(stream);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
