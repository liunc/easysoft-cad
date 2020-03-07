package com.easysoft.cad.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 清洗后的数据-自动完成 实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "data_auto_complete")
public class DataAutoComplete {

	@Id
	@Column(name = "code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String code;

	@Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
	private String name;
	
	
	public String getCode() {
		return code;
	}


	public String getName() {
		return name;
	}

	public void create(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
