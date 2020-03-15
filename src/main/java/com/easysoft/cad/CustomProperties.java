package com.easysoft.cad;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.easysoft.cad")
public class CustomProperties {
	
	private String entryUrl;
	private int urlConnectTimeout;
	private int connectReadTimeout;
	private int insertInBatchPageSize;
	private int importPageSize;
	private int exportPageSize;
	private int exportRowAccessWindowSize;

	public String getEntryUrl() {
		return entryUrl;
	}

	public void setEntryUrl(String entryUrl) {
		this.entryUrl = entryUrl;
	}

	public int getUrlConnectTimeout() {
		return urlConnectTimeout;
	}

	public void setUrlConnectTimeout(int urlConnectTimeout) {
		this.urlConnectTimeout = urlConnectTimeout;
	}

	public int getConnectReadTimeout() {
		return connectReadTimeout;
	}

	public void setConnectReadTimeout(int connectReadTimeout) {
		this.connectReadTimeout = connectReadTimeout;
	}

	public int getInsertInBatchPageSize() {
		return insertInBatchPageSize;
	}

	public void setInsertInBatchPageSize(int insertInBatchPageSize) {
		this.insertInBatchPageSize = insertInBatchPageSize;
	}

	public int getImportPageSize() {
		return importPageSize;
	}

	public void setImportPageSize(int importPageSize) {
		this.importPageSize = importPageSize;
	}

	public int getExportPageSize() {
		return exportPageSize;
	}

	public void setExportPageSize(int exportPageSize) {
		this.exportPageSize = exportPageSize;
	}

	public int getExportRowAccessWindowSize() {
		return exportRowAccessWindowSize;
	}

	public void setExportRowAccessWindowSize(int exportRowAccessWindowSize) {
		this.exportRowAccessWindowSize = exportRowAccessWindowSize;
	}

	
}
