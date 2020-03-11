package com.easysoft.cad.domain.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 清洗方案规则实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "clean_scheme_rule")
public class CleanSchemeRule {

	@Id
	@Column(columnDefinition = "CHAR(32)")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rule_id")
	private CleanRule rule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "scheme_id")
	private CleanScheme scheme;

	public String getId() {
		return id;
	}

	public CleanRule getRule() {
		return rule;
	}

	public CleanScheme getScheme() {
		return scheme;
	}

	public void create(CleanScheme scheme, CleanRule rule) {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.scheme = scheme;
		this.rule = rule;
	}

}
