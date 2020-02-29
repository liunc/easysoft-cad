package com.easysoft.cad.ui.viewModel.collect;

import com.easysoft.core.web.viewModel.BootstrapTableRequest;

public class CollectUrlPageRequest extends BootstrapTableRequest {

	private String url;
	private String urlCategory;
	private String urlCode;
	private String status;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
 
	public String getUrlCategory() {
		return urlCategory;
	}

	public void setUrlCategory(String urlCategory) {
		this.urlCategory = urlCategory;
	}

	public String getUrlCode() {
		return urlCode;
	}

	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
