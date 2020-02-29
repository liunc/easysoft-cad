package com.easysoft.cad.ui.viewModel.originalData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OriginalVillageEditRequest {

	@NotBlank(message = "{code}{validator_required}")
	private String code;

	@NotBlank(message = "{name}{validator_required}")
	@Size(max = 40, message = "{name}{validator_max_length}")
	private String name;

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
