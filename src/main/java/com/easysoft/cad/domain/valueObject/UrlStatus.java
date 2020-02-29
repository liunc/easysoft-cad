package com.easysoft.cad.domain.valueObject;

public class UrlStatus {

	public static final String NOT_DOWNLOAD = "0";

	public static final String SUCCESS = "1";

	public static final String FAIL = "2";

	public static String[] list() {
		return new String[] { NOT_DOWNLOAD, SUCCESS, FAIL };
	}
}
