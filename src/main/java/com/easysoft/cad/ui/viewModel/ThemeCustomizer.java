package com.easysoft.cad.ui.viewModel;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.util.StringUtils;

public class ThemeCustomizer {

	private static final String COOKIE_NAME_LAYOUT_STYLE_OPTION = "layout-style-option";
	private static final String COOKIE_NAME_THEME_COLOR = "theme-color";
	private static final String COOKIE_NAME_LAYOUT_OPTION = "layout-option";

	private Map<String, String> cookieMap = new HashMap<String, String>();

	private String layoutStyleOption;
	private String themeColor;
	private String layoutOption;

	public String getLayoutStyleOption() {
		return this.layoutStyleOption;
	}

	public String getThemeColor() {
		return this.themeColor;
	}

	public String getLayoutOption() {
		return this.layoutOption;
	}
	
	private void initCookieMap() {
		if (this.cookieMap.size() == 0) {
			cookieMap.put(COOKIE_NAME_LAYOUT_STYLE_OPTION, "square"); // rounded // square
			cookieMap.put(COOKIE_NAME_THEME_COLOR, "default");
			cookieMap.put(COOKIE_NAME_LAYOUT_OPTION, "fluid"); // fluid // boxed
		}
	}

	public void init(Cookie[] cookies) {

		this.initCookieMap();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (StringUtils.hasText(cookie.getValue())) {

					switch (cookie.getName()) {

					case COOKIE_NAME_LAYOUT_STYLE_OPTION:
						cookieMap.put(COOKIE_NAME_LAYOUT_STYLE_OPTION, cookie.getValue());
						break;
					case COOKIE_NAME_THEME_COLOR:
						cookieMap.put(COOKIE_NAME_THEME_COLOR, cookie.getValue());
						break;
					case COOKIE_NAME_LAYOUT_OPTION:
						cookieMap.put(COOKIE_NAME_LAYOUT_OPTION, cookie.getValue());
						break;
					default:
						break;

					}
				}
			}
		}

		this.layoutStyleOption = this.cookieMap.get(COOKIE_NAME_LAYOUT_STYLE_OPTION);
		this.themeColor = this.cookieMap.get(COOKIE_NAME_THEME_COLOR);
		this.layoutOption = this.cookieMap.get(COOKIE_NAME_LAYOUT_OPTION);
	}
}
