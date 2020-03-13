package com.easysoft.cad.ui.viewModel.original;

import com.easysoft.core.web.viewModel.BootstrapTableRequest;

public class OriginalCountyPageRequest extends BootstrapTableRequest {

	private String cityCode;
	private String code;
	private String name;

	public String getCityCode() {
		return cityCode;
	}

	public void setProvinceCode(String cityCode) {
		this.cityCode = cityCode;
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
