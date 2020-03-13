package com.easysoft.cad;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.easysoft.cad")
public class CustomProperties {
	
	private String entryUrl;
	private int exportPageSize;
	private int exportRowAccessWindowSize;

	public String getEntryUrl() {
		return entryUrl;
	}

	public void setEntryUrl(String entryUrl) {
		this.entryUrl = entryUrl;
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
