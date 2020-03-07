package com.easysoft.cad.ui.viewModel.clean;

import com.easysoft.core.web.viewModel.BootstrapTableRequest;

public class CleanRulePageRequest extends BootstrapTableRequest {

	private String dataCategory;

	public String getDataCategory() {
		return dataCategory;
	}

	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}
}
