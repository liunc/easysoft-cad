package com.easysoft.cad.domain.valueObject;

public class DataCategory {

	public static final String PROVINCE = "1";

	public static final String CITY = "2";

	public static final String COUNTY = "3";

	public static final String TOWN = "4";

	public static final String VILLAGE = "5";

	public static String[] list() {
		return new String[] { PROVINCE, CITY, COUNTY, TOWN, VILLAGE };
	}
}
