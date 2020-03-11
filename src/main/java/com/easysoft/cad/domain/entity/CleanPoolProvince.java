package com.easysoft.cad.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 省、直辖市、自治区实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "clean_pool_province")
public class CleanPoolProvince {

	@Id
	@Column(name = "code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String code;

	@Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String name;

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

	public void create(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public void updateName(String name) {
		this.name = name;
	}
}
