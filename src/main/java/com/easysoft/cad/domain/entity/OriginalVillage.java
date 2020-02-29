package com.easysoft.cad.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 街道、村实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "original_village")
public class OriginalVillage {

	@Id
	@Column(name = "code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String code;

	@Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String name;

	@Column(name = "category", columnDefinition = "VARCHAR(10)", nullable = false)
	private String category;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	public void create(String code, String name, String category) {
		this.code = code;
		this.name = name;
		this.category = category;
	}

	public void updateName(String name) {
		this.name = name;
	}

}
