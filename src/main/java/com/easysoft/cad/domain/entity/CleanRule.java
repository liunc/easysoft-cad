package com.easysoft.cad.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.easysoft.core.data.entity.BaseEntity;

/**
 * 清洗規則实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "clean_rule")
public class CleanRule extends BaseEntity {

	private static final long serialVersionUID = 7653102067935987141L;

	@Column(name = "data_category", columnDefinition = "CHAR(1)", nullable = false)
	private String dataCategory;

	@Column(name = "search_text", columnDefinition = "VARCHAR(40)", nullable = false)
	private String searchText;

	@Column(name = "replace_text", columnDefinition = "VARCHAR(40)")
	private String replaceText;

	@Column(name = "priority")
	private int priority;

	public String getDataCategory() {
		return dataCategory;
	}

	public String getSearchText() {
		return searchText;
	}

	public String getReplaceText() {
		return replaceText;
	}

	public int getPriority() {
		return priority;
	}

	public void create(String dataCategory, String searchText, String replaceText, int priority) {
		super.create();
		this.dataCategory = dataCategory;
		this.searchText = searchText;
		this.replaceText = replaceText;
		this.priority = priority;
	}

	public void update(String searchText, String replaceText, int priority) {
		this.searchText = searchText;
		this.replaceText = replaceText;
		this.priority = priority;
	}
}
