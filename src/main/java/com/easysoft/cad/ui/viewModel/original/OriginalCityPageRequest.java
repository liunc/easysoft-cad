package com.easysoft.cad.ui.viewModel.original;

import com.easysoft.core.web.viewModel.BootstrapTableRequest;

public class OriginalCityPageRequest extends BootstrapTableRequest {

	private String provinceCode;
	private String code;
	private String name;

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
