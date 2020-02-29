package com.easysoft.cad.domain.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.easysoft.cad.domain.valueObject.UrlStatus;

/**
 * 采集数据Url实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "collect_url")
public class CollectUrl {
	
	@Id
	@Column(columnDefinition = "CHAR(32)")
	private String id;
	
	@Column(name = "url", columnDefinition = "VARCHAR(128)", nullable = false)
	private String url;
	
	@Column(name = "url_category", columnDefinition = "CHAR(1)")
	private String urlCategory;
	
	@Column(name = "url_code", columnDefinition = "VARCHAR(20)")
	private String urlCode;
	
	@Column(name = "status", columnDefinition = "CHAR(1)", nullable = false)
	private String status;

	@Column(columnDefinition = "VARCHAR(512)")
	private String remark;
	
	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}
	
	public String getUrlCategory() {
		return urlCategory;
	}
	
	public String getUrlCode() {
		return urlCode;
	}

	public String getStatus() {
		return status;
	}

	public String getRemark() {
		return remark;
	}

	public void create(String url, String urlCategory, String urlCode) {
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.url = url;
		this.urlCategory = urlCategory;
		this.urlCode = urlCode;
		this.status = UrlStatus.NOT_DOWNLOAD;
		this.remark = "";
	}
	
	public void success() {
		this.status = UrlStatus.SUCCESS;
		this.remark = "OK";
	}
	
	public void fail(String errorMessage) {
		this.status = UrlStatus.FAIL;
		this.remark = String.format("Error Message : %s", errorMessage);
	}
}
