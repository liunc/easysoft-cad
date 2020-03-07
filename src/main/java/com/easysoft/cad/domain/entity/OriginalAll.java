package com.easysoft.cad.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 五级行政区划实体类
 * 
 * @author 刘年超
 */
@Entity
@Table(name = "original_all")
public class OriginalAll {

	@Column(name = "province_code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String provinceCode;

	@Column(name = "province_name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String provinceName;

	@Column(name = "city_code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String cityCode;

	@Column(name = "city_name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String cityName;

	@Column(name = "county_code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String countyCode;

	@Column(name = "county_name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String countyName;

	@Column(name = "town_code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String townCode;

	@Column(name = "town_name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String townName;

	@Id
	@Column(name = "village_code", columnDefinition = "VARCHAR(12)", nullable = false)
	private String villageCode;

	@Column(name = "village_name", columnDefinition = "VARCHAR(40)", nullable = false)
	private String villageName;

	@Column(name = "village_category", columnDefinition = "VARCHAR(10)", nullable = false)
	private String villageCategory;

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public String getTownCode() {
		return townCode;
	}

	public String getTownName() {
		return townName;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public String getVillageName() {
		return villageName;
	}

	public String getVillageCategory() {
		return villageCategory;
	}

	public OriginalAll() {
	}

	public void create(String villageCode, String villageCategory, String villageName) {
		this.villageCode = villageCode;
		this.villageCategory = villageCategory;
		this.villageName = villageName;
		this.provinceCode = this.villageCode.substring(0, 2);
		this.provinceName = "";
		this.cityCode = this.villageCode.substring(0, 4) + "00000000";
		this.cityName = "";
		this.countyCode = this.villageCode.substring(0, 6) + "000000";
		this.countyName = "";
		this.townCode = this.villageCode.substring(0, 9) + "000";
		this.townName = "";
	}
}
