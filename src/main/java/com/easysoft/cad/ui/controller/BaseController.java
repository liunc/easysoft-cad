package com.easysoft.cad.ui.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import com.easysoft.cad.ui.viewModel.ThemeCustomizer;

public class BaseController {

	protected void getLayoutData(Model model, HttpServletRequest request) {
		
		Cookie[] cookies =  request.getCookies();
	    ThemeCustomizer theme = new ThemeCustomizer();
	    theme.init(cookies);
	    model.addAttribute("theme", theme);
	}
}
