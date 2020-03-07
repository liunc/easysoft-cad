package com.easysoft.cad.ui.viewModel.clean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CleanRuleEditRequest {

	@NotBlank(message = "{id}{validator_required}")
	private String id;
	
	@NotBlank(message = "{search_text}{validator_required}")
	@Size(max = 40, message = "{search_text}{validator_max_length}")
	private String searchText;
	
	@Size(max = 40, message = "{replace_text}{validator_max_length}")
	private String replaceText;
	
	private int priority;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
