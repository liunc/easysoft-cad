package com.easysoft.cad.domain.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.StringUtils;

import com.easysoft.cad.domain.service.ExportService;

public class ExportServiceImpl implements ExportService {

	private static final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

	private String exportDirectory;

	@Override
	public String getExportDirectory() {

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
	public String generateFileName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Override
	public void saveFile(XSSFWorkbook workbook, String fileName) {
		try {
			FileOutputStream stream = new FileOutputStream(this.getExportDirectory() + "\\" + fileName + ".xlsx");
			workbook.write(stream);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
