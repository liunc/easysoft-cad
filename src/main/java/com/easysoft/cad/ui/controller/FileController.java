package com.easysoft.cad.ui.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easysoft.cad.domain.service.ExportService;

@Controller
@RequestMapping("/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private ExportService exportService;

	@RequestMapping("/download/{fileName}")
	public void downloadFile(@PathVariable String fileName, HttpServletResponse response)
			throws UnsupportedEncodingException {

		File file = new File(this.exportService.getFilePath(fileName));

		// 如果文件名存在，则进行下载
		if (file.exists()) {

			// 配置文件下载
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));

			// 实现文件下载
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				// System.out.println("Download the song successfully!");
			} catch (Exception e) {
				// System.out.println("Download the song failed!");
				logger.error(e.getMessage());
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
				}
			}
		}

	}
}
