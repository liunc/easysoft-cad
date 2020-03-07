package com.easysoft.cad.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.easysoft.core.data.entity.BaseEntity;

/**
 * 清洗方案实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "clean_scheme")
public class CleanScheme extends BaseEntity {
	
	private static final long serialVersionUID = -5782877077199980299L;

	@Column(name = "name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String name;

	@Column(name = "remark", columnDefinition = "VARCHAR(512)")
	private String remark;

	public String getName() {
		return name;
	}

	public String getRemark() {
		return remark;
	}
	
	public void create(String name, String remark) {
		super.create();
		this.name = name;
		this.remark = remark;
	}

}
