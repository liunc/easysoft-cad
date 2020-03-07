package com.easysoft.cad.ui.viewModel.clean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class CleanRuleAddRequest {
	
	@NotBlank(message = "{data_category}{validator_required}")
	@Size(max = 40, message = "{data_category}{validator_max_length}")
	private String dataCategory;
	
	@NotBlank(message = "{search_text}{validator_required}")
	@Size(max = 40, message = "{search_text}{validator_max_length}")
	private String searchText;
	
	@Size(max = 40, message = "{replace_text}{validator_max_length}")
	private String replaceText;
	
	@Range(min = 1, max = 9999, message = "{priority}{validator_range}")
	private int priority;

	public String getDataCategory() {
		return dataCategory;
	}

	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getReplaceText() {
		return replaceText;
	}

	public void setReplaceText(String replaceText) {
		this.replaceText = replaceText;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
