package com.easysoft.cad.ui.viewModel.data;

import com.easysoft.core.web.viewModel.BootstrapTableRequest;

public class OriginalTownPageRequest extends BootstrapTableRequest {
 
	private String countyCode;
	private String code;
	private String name; 

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
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
