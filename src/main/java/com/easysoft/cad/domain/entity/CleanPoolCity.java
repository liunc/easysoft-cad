package com.easysoft.cad.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 地级市、省、自治区直辖县级行政区划实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "clean_pool_city")
public class CleanPoolCity {

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
