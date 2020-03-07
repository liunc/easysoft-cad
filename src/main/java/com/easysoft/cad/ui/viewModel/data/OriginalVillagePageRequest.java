package com.easysoft.cad.ui.viewModel.data;

import com.easysoft.core.web.viewModel.BootstrapTableRequest;

public class OriginalVillagePageRequest extends BootstrapTableRequest {
 
	private String townCode;
	private String code;
	private String name; 

	public String getTownCode() {
		return townCode;
	}

	public void setTownCode(String townCode) {
		this.townCode = townCode;
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
